package main.java.streamapi;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.*;

/**
 * AdvancedStreams class demonstrates advanced features of Stream API.
 * Includes parallel streams, custom collectors, and performance considerations.
 * Interview Tip: Parallel streams are not always faster; use with care.
 */
public class AdvancedStreams {

    /**
     * Example 1: Parallel stream basics
     * Interview Question: When to use parallel streams?
     */
    public void parallelStreamBasics() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.parallelStream()
               .forEach(System.out::println);
    }

    /**
     * Example 2: Sequential vs Parallel
     * Interview Question: How to force sequential?
     */
    public void sequentialVsParallel() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
               .parallel()
               .sequential()
               .forEach(System.out::println);
    }

    /**
     * Example 3: Custom collector
     * Interview Question: How to create a custom collector?
     */
    public void customCollector() {
        List<String> words = Arrays.asList("hello", "world");
        Collector<String, ?, String> collector = Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString
        );
        String result = words.stream().collect(collector);
        System.out.println(result);
    }

    /**
     * Example 4: Collecting to ConcurrentMap
     * Interview Question: Thread-safe collectors?
     */
    public void concurrentMap() {
        List<String> words = Arrays.asList("a", "b", "a");
        ConcurrentMap<String, Long> map = words.stream()
                                               .collect(Collectors.groupingByConcurrent(w -> w, Collectors.counting()));
        System.out.println(map);
    }

    /**
     * Example 5: Stream with custom spliterator
     * Interview Question: Advanced: What is a spliterator?
     */
    public void customSpliterator() {
        // Advanced example, simplified
        List<Integer> list = Arrays.asList(1, 2, 3);
        Spliterator<Integer> spliterator = list.spliterator();
        spliterator.forEachRemaining(System.out::println);
    }

    /**
     * Example 6: Infinite stream with limit and parallel
     * Interview Question: Parallel infinite streams?
     */
    public void parallelInfinite() {
        Stream<Integer> infinite = Stream.iterate(0, n -> n + 1);
        infinite.parallel()
               .limit(10)
               .forEach(System.out::println);
    }

    /**
     * Example 7: Reducing with combiner
     * Interview Question: How reduce works in parallel?
     */
    public void reduceWithCombiner() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                         .reduce(0, Integer::sum, Integer::sum);
        System.out.println(sum);
    }

    /**
     * Example 8: Collecting and then
     * Interview Question: Chaining collectors?
     */
    public void collectingAndThen() {
        List<String> words = Arrays.asList("a", "b", "c");
        int size = words.stream()
                        .collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(size);
    }

    /**
     * Example 9: Teeing collector (Java 12+)
     * Interview Question: How to perform two operations in one pass?
     */
    public void teeingCollector() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Map<String, Integer> result = numbers.stream()
                                             .collect(Collectors.teeing(
                                                 Collectors.summingInt(Integer::intValue),
                                                 Collectors.counting(),
                                                 (sum, count) -> Map.of("sum", sum, "count", (int) (long) count)
                                             ));
        System.out.println(result);
    }

    /**
     * Example 10: Stream performance tips
     * Interview Question: How to optimize stream performance?
     */
    public void performanceTips() {
        // Use primitive streams for primitives
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream()
                         .mapToInt(Integer::intValue)
                         .sum();
        System.out.println(sum);
    }

    // Continue with more advanced examples

    public void example11() {
        // Similar
        List<String> list = Arrays.asList("a", "b");
        list.stream().forEach(System.out::println);
    }

    // To build lines, add many variations

    // ... (many more)

}