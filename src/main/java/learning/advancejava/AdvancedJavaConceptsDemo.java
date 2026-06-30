package main.java.learning.advancejava;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Advanced Java concepts: streams, lambdas, concurrency, and CompletableFuture.
 */
public class AdvancedJavaConceptsDemo {

    public static void main(String[] args) {
        System.out.println("=== Advanced Java Concepts ===");

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int evenSum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of even numbers: " + evenSum);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("Task executed by a thread pool"));
        executorService.shutdown();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Advanced Java completed");
        future.thenAccept(System.out::println);
    }
}
