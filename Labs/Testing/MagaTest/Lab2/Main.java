import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


public class WordFrequencyAnalyzer {

    private static final Path DEFAULT_FILE = Path.of("src/txt/harry.txt");
    private static final int TOP_N = 30;

    public static void main(String[] args) throws IOException {
        Path file = args.length > 0 ? Path.of(args[0]) : DEFAULT_FILE;
        Instant start = Instant.now();

        List<Map.Entry<String, Long>> topWords = analyze(file, TOP_N);

        topWords.forEach(entry ->
                System.out.printf("%s %d%n", entry.getKey(), entry.getValue()));

        Duration elapsed = Duration.between(start, Instant.now());
        System.out.println("------");
        System.out.println(elapsed.toMillis() + " ms");
    }

    
    public static List<Map.Entry<String, Long>> analyze(Path file, int topN) throws IOException {
        String content = Files.readString(file)          // Java 11+
                .replaceAll("[^A-Za-z ]", " ")
                .toLowerCase(Locale.ROOT);

        return content.lines()                           // handles large texts better if needed
                .flatMap(line -> java.util.Arrays.stream(line.split(" +")))
                .filter(word -> !word.isBlank())
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(topN)
                .toList();
    }
}
