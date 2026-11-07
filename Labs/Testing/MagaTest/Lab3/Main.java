import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * V2: версія, оптимізована за споживанням пам'яті.

 * Результат ідентичний версії з репозиторію (Lab2/Main.java): слова — це максимальні
 * послідовності символів A-Za-z у нижньому регістрі; сортування за частотою (спадання),
 * далі за алфавітом. Відмінності у способі роботи:

 *  1. Файл НЕ читається цілком у String і не копіюється replaceAll/toLowerCase:
 *     він обробляється потоково буфером 64 КБ, тому пам'ять не залежить від розміру файлу.
 *  2. Токенізація вручну (без regex, split, lines(), Stream): на кожне слово не створюється
 *     ні String, ні проміжний масив.
 *  3. Лічильник — власна хеш-таблиця з відкритою адресацією: усі унікальні слова лежать
 *     в одному byte[] (1 байт на літеру, бо лише a-z), частоти в long[]. Без HashMap.Node,
 *     String і Long на кожне унікальне слово.
 *  4. Топ-N вибирається мінімальною купою розміру N (O(U log N)), а не сортуванням
 *     усіх унікальних слів.

 * Примітка: UTF-8 байти ≥ 0x80 не є літерами, тому вони так само розділяють слова,
 * як і у версії з replaceAll("[^A-Za-z ]", " "). Некоректний UTF-8 тут не спричиняє
 * MalformedInputException (як у Files.readString), а просто вважається роздільником.
 */
public final class Main {

    private static final String DEFAULT_FILE = "harry.txt";
    private static final int DEFAULT_TOP_N = 30;
    private static final int READ_BUFFER_SIZE = 64 * 1024;

    // ---------------------------------------------------------------- таблиця лічильників

    /** Хеш-таблиця «слово (a-z) -> кількість» з відкритою адресацією та лінійним зондуванням. */
    static final class WordCounter {
        private int[] slots = new int[1 << 12];     // 0 = порожньо, інакше індекс запису + 1
        private int mask = slots.length - 1;
        private int size;                           // кількість унікальних слів

        private byte[] arena = new byte[1 << 14];   // усі унікальні слова підряд
        private int[] starts = new int[1 << 11];    // starts[i] .. starts[i+1] — слово i в arena
        private int[] hashes = new int[1 << 11];
        private long[] counts = new long[1 << 11];

        int size() { return size; }
        long count(int i) { return counts[i]; }

        void add(byte[] buf, int len, int hash) {
            int h = spread(hash);
            int idx = h & mask;
            int slot;
            while ((slot = slots[idx]) != 0) {
                int e = slot - 1;
                if (hashes[e] == hash
                        && starts[e + 1] - starts[e] == len
                        && Arrays.equals(arena, starts[e], starts[e + 1], buf, 0, len)) {
                    counts[e]++;
                    return;
                }
                idx = (idx + 1) & mask;
            }
            insert(buf, len, hash, idx);
        }

        private void insert(byte[] buf, int len, int hash, int idx) {
            int e = size;
            if (e + 1 >= starts.length) {
                int cap = starts.length << 1;
                starts = Arrays.copyOf(starts, cap);
                hashes = Arrays.copyOf(hashes, cap);
                counts = Arrays.copyOf(counts, cap);
            }
            int off = starts[e];
            if (off + len > arena.length) {
                arena = Arrays.copyOf(arena, Math.max(arena.length << 1, off + len));
            }
            System.arraycopy(buf, 0, arena, off, len);
            starts[e + 1] = off + len;
            hashes[e] = hash;
            counts[e] = 1;
            slots[idx] = e + 1;
            size++;
            if (size > (slots.length >> 1) + (slots.length >> 2)) { // завантаження > 0.75
                rehash();
            }
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

        /** true, якщо запис a гірший за запис b (менша частота або та сама, але пізніше за алфавітом). */
        boolean worse(int a, int b) {
            if (counts[a] != counts[b]) return counts[a] < counts[b];
            return Arrays.compare(arena, starts[a], starts[a + 1], arena, starts[b], starts[b + 1]) > 0;
        }

        String word(int i) {
            return new String(arena, starts[i], starts[i + 1] - starts[i],
                    java.nio.charset.StandardCharsets.ISO_8859_1);
        }
    }

    // ---------------------------------------------------------------- підрахунок

    /** Потокова токенізація і підрахунок. Пам'ять: буфер + таблиця унікальних слів. */
    static WordCounter count(Path file) throws IOException {
        WordCounter counter = new WordCounter();
        byte[] in = new byte[READ_BUFFER_SIZE];
        byte[] word = new byte[64];
        int len = 0;
        int hash = 0;

        try (InputStream is = Files.newInputStream(file)) {
            int n;
            while ((n = is.read(in)) > 0) {
                for (int i = 0; i < n; i++) {
                    int c = in[i] | 0x20;                        // A-Z -> a-z
                    if (c >= 'a' && c <= 'z' && in[i] > 0) {      // лише ASCII-літери
                        if (len == word.length) word = Arrays.copyOf(word, len << 1);
                        word[len++] = (byte) c;
                        hash = 31 * hash + c;
                    } else if (len > 0) {
                        counter.add(word, len, hash);
                        len = 0;
                        hash = 0;
                    }
                }
            }
        }
        if (len > 0) counter.add(word, len, hash);
        return counter;
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
        // найкращі першими: простим вставленням (cap малий)
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
        Path file = Path.of(args.length > 0 ? args[0] : DEFAULT_FILE);
        int topN = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_TOP_N;
        long start = System.nanoTime();

        WordCounter counter = count(file);
        int[] top = topN(counter, topN);

        StringBuilder out = new StringBuilder(top.length * 16);
        String nl = System.lineSeparator();
        for (int e : top) {
            out.append(counter.word(e)).append(' ').append(counter.count(e)).append(nl);
        }
        System.out.print(out);

        System.out.println("------");
        System.out.println((System.nanoTime() - start) / 1_000_000 + " ms");
    }
}
