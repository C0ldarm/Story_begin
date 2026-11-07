import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * V3: версія, оптимізована за швидкодією.
 *
 * Результат ідентичний V1 (Lab2/Main.java) і V2: слова — максимальні послідовності A-Za-z
 * у нижньому регістрі; порядок: частота спадно, далі за алфавітом.
 *
 * Що зроблено порівняно з V2 (головний виграш — на «холодній» JVM, коли код ще виконується
 * інтерпретатором або ранніми рівнями JIT):
 *
 *  1. Класифікація байта однією таблицею LOWER[256] (літера -> маленька літера, решта -> 0)
 *     замість кількох порівнянь і OR на кожен байт.
 *  2. Слово не копіюється в проміжний буфер: хеш рахується на ходу, а порівняння з уже
 *     збереженим словом робиться прямо з вхідного масиву (c | 0x20 -> нижній регістр).
 *  3. Файл читається через java.io.FileInputStream (клас уже завантажений JVM), а не через
 *     java.nio.file.Files / Path (їхня ініціалізація при першому виклику коштує кілька мс).
 *     Без lambda, Stream, regex, String.format: жодного invokedynamic і зайвого завантаження класів.
 *  4. Великі тексти діляться на шматки по межах слів і скануються паралельно звичайними
 *     потоками (кожен зі своєю таблицею лічильників, без блокувань); таблиці зливаються в кінці.
 *     Для малих файлів (як harry.txt) потоки не створюються: заміри показали, що там вони шкодять.
 *  5. Файл обробляється сегментами до 32 МБ, тож пам'ять обмежена розміром сегмента, а не файлу.
 */
public final class Main {

    private static final String DEFAULT_FILE = "harry.txt";
    private static final int DEFAULT_TOP_N = 30;

    private static final int SEGMENT_BYTES = 32 << 20;
    /**
     * Потік вмикається лише для шматка від 8 МБ: заміри показали, що на малих текстах (до ~7 МБ)
     * запуск потоків, злиття таблиць і конкуренція з потоками JIT за ядра коштують дорожче за виграш.
     */
    private static final int MIN_PIECE_BYTES = 8 << 20;
    private static final int MAX_THREADS = Math.max(1, Math.min(8, Runtime.getRuntime().availableProcessors()));

    /** A-Z, a-z -> a-z; усе інше -> 0. */
    private static final byte[] LOWER = new byte[256];
    static {
        for (int c = 'a'; c <= 'z'; c++) {
            LOWER[c] = (byte) c;
            LOWER[c - 32] = (byte) c;
        }
    }

    // ---------------------------------------------------------------- таблиця лічильників

    /** Хеш-таблиця «слово (a-z) -> кількість», відкрита адресація, слова в одному byte[]. */
    static final class WordCounter {
        private int[] slots = new int[1 << 12];     // 0 = порожньо, інакше індекс запису + 1
        private int mask = slots.length - 1;
        private int size;

        private byte[] arena = new byte[1 << 14];
        private int[] starts = new int[1 << 11];
        private int[] hashes = new int[1 << 11];
        private long[] counts = new long[1 << 11];

        int size() { return size; }
        long count(int i) { return counts[i]; }

        /** Додає слово src[off..off+len) (літери будь-якого регістру) з лічильником cnt. */
        void add(byte[] src, int off, int len, int hash, long cnt) {
            int idx = spread(hash) & mask;
            int slot;
            while ((slot = slots[idx]) != 0) {
                int e = slot - 1;
                if (hashes[e] == hash && starts[e + 1] - starts[e] == len) {
                    int a = starts[e];
                    int k = 0;
                    while (k < len && (src[off + k] | 0x20) == arena[a + k]) k++;
                    if (k == len) {
                        counts[e] += cnt;
                        return;
                    }
                }
                idx = (idx + 1) & mask;
            }
            insert(src, off, len, hash, cnt, idx);
        }

        private void insert(byte[] src, int off, int len, int hash, long cnt, int idx) {
            int e = size;
            if (e + 1 >= starts.length) {
                int cap = starts.length << 1;
                starts = Arrays.copyOf(starts, cap);
                hashes = Arrays.copyOf(hashes, cap);
                counts = Arrays.copyOf(counts, cap);
            }
            int a = starts[e];
            if (a + len > arena.length) {
                arena = Arrays.copyOf(arena, Math.max(arena.length << 1, a + len));
            }
            for (int k = 0; k < len; k++) arena[a + k] = (byte) (src[off + k] | 0x20);
            starts[e + 1] = a + len;
            hashes[e] = hash;
            counts[e] = cnt;
            slots[idx] = e + 1;
            size++;
            if (size > (slots.length >> 1) + (slots.length >> 2)) rehash();
        }

        private void rehash() {
            int[] bigger = new int[slots.length << 1];
            int newMask = bigger.length - 1;
            for (int e = 0; e < size; e++) {
                int idx = spread(hashes[e]) & newMask;
                while (bigger[idx] != 0) idx = (idx + 1) & newMask;
                bigger[idx] = e + 1;
            }
            slots = bigger;
            mask = newMask;
        }

        private static int spread(int h) {
            h ^= (h >>> 16);
            h *= 0x85ebca6b;
            h ^= (h >>> 13);
            return h;
        }

        /** Переносить усі слова іншої таблиці в цю. */
        void merge(WordCounter other) {
            for (int e = 0; e < other.size; e++) {
                int a = other.starts[e];
                add(other.arena, a, other.starts[e + 1] - a, other.hashes[e], other.counts[e]);
            }
        }

        /** true, якщо запис a гірший за запис b (менша частота або та сама, але пізніше за алфавітом). */
        boolean worse(int a, int b) {
            if (counts[a] != counts[b]) return counts[a] < counts[b];
            return Arrays.compare(arena, starts[a], starts[a + 1], arena, starts[b], starts[b + 1]) > 0;
        }

        String word(int i) {
            return new String(arena, starts[i], starts[i + 1] - starts[i], StandardCharsets.ISO_8859_1);
        }
    }

    // ---------------------------------------------------------------- сканування

    /** Рахує слова в a[from..to). Межі шматка мають припадати на нелітери (або кінець даних). */
    static void scan(byte[] a, int from, int to, WordCounter counter) {
        final byte[] lower = LOWER;
        int i = from;
        while (i < to) {
            int c = lower[a[i] & 0xFF];
            if (c == 0) {
                i++;
                continue;
            }
            int start = i;
            int h = 0;
            do {
                h = 31 * h + c;
                i++;
            } while (i < to && (c = lower[a[i] & 0xFF]) != 0);
            counter.add(a, start, i - start, h, 1);
        }
    }

    private static final class Worker extends Thread {
        private final byte[] a;
        private final int from, to;
        private final WordCounter counter;

        Worker(byte[] a, int from, int to, WordCounter counter) {
            this.a = a;
            this.from = from;
            this.to = to;
            this.counter = counter;
        }

        @Override
        public void run() {
            scan(a, from, to, counter);
        }
    }

    /** Обробляє buf[0..end) (end — на межі слова): ділить на шматки й скануює паралельно. */
    private static void processSegment(byte[] buf, int end, WordCounter[] tables) {
        int threads = Math.max(1, Math.min(tables.length, end / MIN_PIECE_BYTES));
        if (threads == 1) {
            scan(buf, 0, end, tables[0]);
            return;
        }
        int[] bounds = new int[threads + 1];
        bounds[threads] = end;
        for (int k = 1; k < threads; k++) {
            int p = Math.max((int) ((long) end * k / threads), bounds[k - 1]);
            while (p < end && LOWER[buf[p] & 0xFF] != 0) p++;   // не розрізати слово
            bounds[k] = p;
        }
        Worker[] workers = new Worker[threads];
        for (int k = 1; k < threads; k++) {
            workers[k] = new Worker(buf, bounds[k], bounds[k + 1], tables[k]);
            workers[k].start();
        }
        scan(buf, bounds[0], bounds[1], tables[0]);
        for (int k = 1; k < threads; k++) {
            try {
                workers[k].join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Перервано під час підрахунку", ex);
            }
        }
    }

    private static int lastNonLetter(byte[] buf, int end) {
        for (int i = end - 1; i >= 0; i--) {
            if (LOWER[buf[i] & 0xFF] == 0) return i;
        }
        return -1;
    }

    /** Читає файл сегментами й повертає об'єднану таблицю лічильників. */
    static WordCounter count(String path) throws IOException {
        File file = new File(path);
        long length = file.length();
        int cap = length > 0 ? (int) Math.min(length + 1, SEGMENT_BYTES) : 1 << 20;
        byte[] buf = new byte[cap];

        WordCounter[] tables = new WordCounter[MAX_THREADS];
        for (int k = 0; k < tables.length; k++) tables[k] = new WordCounter();

        try (InputStream in = new FileInputStream(file)) {
            int filled = 0;
            while (true) {
                int want = buf.length - filled;
                int got = 0;
                while (got < want) {
                    int n = in.read(buf, filled + got, want - got);
                    if (n < 0) break;
                    got += n;
                }
                int end = filled + got;
                boolean eof = got < want;
                int cut = eof ? end : lastNonLetter(buf, end) + 1;
                if (!eof && cut == 0) {                       // одне «слово» на весь буфер
                    buf = Arrays.copyOf(buf, buf.length << 1);
                    filled = end;
                    continue;
                }
                processSegment(buf, cut, tables);
                if (eof) break;
                System.arraycopy(buf, cut, buf, 0, end - cut);
                filled = end - cut;
            }
        }
        for (int k = 1; k < tables.length; k++) tables[0].merge(tables[k]);
        return tables[0];
    }

    // ---------------------------------------------------------------- топ-N

    /** Індекси топ-N слів: частота спадає, при рівності — за алфавітом. */
    static int[] topN(WordCounter counter, int topN) {
        int cap = Math.min(topN, counter.size());
        if (cap <= 0) return new int[0];

        int[] heap = new int[cap];     // корінь — найгірший з поточних кандидатів
        int hs = 0;
        for (int e = 0; e < counter.size(); e++) {
            if (hs < cap) {
                heap[hs] = e;
                siftUp(counter, heap, hs++);
            } else if (counter.worse(heap[0], e)) {
                heap[0] = e;
                siftDown(counter, heap, hs, 0);
            }
        }
        int[] result = Arrays.copyOf(heap, hs);
        for (int i = 1; i < result.length; i++) {
            int x = result[i];
            int j = i - 1;
            while (j >= 0 && counter.worse(result[j], x)) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = x;
        }
        return result;
    }

    private static void siftUp(WordCounter c, int[] heap, int i) {
        int x = heap[i];
        while (i > 0) {
            int p = (i - 1) >>> 1;
            if (!c.worse(x, heap[p])) break;
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = x;
    }

    private static void siftDown(WordCounter c, int[] heap, int size, int i) {
        int x = heap[i];
        while (true) {
            int l = 2 * i + 1;
            if (l >= size) break;
            int r = l + 1;
            int child = (r < size && c.worse(heap[r], heap[l])) ? r : l;
            if (!c.worse(heap[child], x)) break;
            heap[i] = heap[child];
            i = child;
        }
        heap[i] = x;
    }

    // ---------------------------------------------------------------- main

    public static void main(String[] args) throws IOException {
        String file = args.length > 0 ? args[0] : DEFAULT_FILE;
        int topN = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_TOP_N;
        long start = System.nanoTime();

        WordCounter counter = count(file);
        int[] top = topN(counter, topN);

        StringBuilder out = new StringBuilder(top.length * 16 + 32);
        String nl = System.lineSeparator();
        for (int e : top) {
            out.append(counter.word(e)).append(' ').append(counter.count(e)).append(nl);
        }
        System.out.print(out);

        System.out.println("------");
        System.out.println((System.nanoTime() - start) / 1_000_000 + " ms");
    }
}
