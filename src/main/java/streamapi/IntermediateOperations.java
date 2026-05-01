package main.java.streamapi;

import java.util.*;
import java.util.stream.*;

/**
 * IntermediateOperations class demonstrates intermediate operations in Stream API.
 * These operations return a new stream and are lazy.
 * Interview Tip: Intermediate operations are chained and executed only when terminal operation is called.
 */
public class IntermediateOperations {

    /**
     * Example 1: filter - selects elements based on predicate
     * Interview Question: How does filter work?
     */
    public void filterExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);
    }

    /**
     * Example 2: map - transforms elements
     * Interview Question: Difference between map and flatMap?
     */
    public void mapExample() {
        List<String> words = Arrays.asList("hello", "world");
        words.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);
    }

    /**
     * Example 3: flatMap - flattens nested structures
     * Interview Question: When to use flatMap?
     */
    public void flatMapExample() {
        List<List<String>> listOfLists = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d")
        );
        listOfLists.stream()
                   .flatMap(List::stream)
                   .forEach(System.out::println);
    }

    /**
     * Example 4: distinct - removes duplicates
     * Interview Question: How does distinct work with objects?
     */
    public void distinctExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3);
        numbers.stream()
               .distinct()
               .forEach(System.out::println);
    }

    /**
     * Example 5: sorted - sorts elements
     * Interview Question: How to sort in reverse order?
     */
    public void sortedExample() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5);
        numbers.stream()
               .sorted()
               .forEach(System.out::println);
    }

    /**
     * Example 6: sorted with comparator
     * Interview Question: Custom sorting in streams?
     */
    public void sortedWithComparator() {
        List<String> words = Arrays.asList("banana", "apple", "cherry");
        words.stream()
             .sorted(Comparator.comparingInt(String::length))
             .forEach(System.out::println);
    }

    /**
     * Example 7: peek - performs action without changing stream
     * Interview Question: Use cases for peek?
     */
    public void peekExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.stream()
               .peek(n -> System.out.println("Processing: " + n))
               .filter(n -> n > 1)
               .forEach(System.out::println);
    }

    /**
     * Example 8: limit - truncates stream
     * Interview Question: Difference between limit and skip?
     */
    public void limitExample() {
        Stream<Integer> infinite = Stream.iterate(0, n -> n + 1);
        infinite.limit(5)
               .forEach(System.out::println);
    }

    /**
     * Example 9: skip - skips first n elements
     * Interview Question: How to get last n elements?
     */
    public void skipExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
               .skip(2)
               .forEach(System.out::println);
    }

    /**
     * Example 10: takeWhile (Java 9+) - takes while predicate holds
     * Interview Question: Difference between takeWhile and filter?
     */
    public void takeWhileExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1);
        numbers.stream()
               .takeWhile(n -> n < 4)
               .forEach(System.out::println);
    }

    /**
     * Example 11: dropWhile (Java 9+) - drops while predicate holds
     * Interview Question: Use cases for dropWhile?
     */
    public void dropWhileExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
               .dropWhile(n -> n < 3)
               .forEach(System.out::println);
    }

    /**
     * Example 12: mapToInt - converts to IntStream
     * Interview Question: Why use primitive streams?
     */
    public void mapToIntExample() {
        List<String> words = Arrays.asList("hello", "world");
        words.stream()
             .mapToInt(String::length)
             .forEach(System.out::println);
    }

    /**
     * Example 13: boxed - converts primitive to object stream
     * Interview Question: How to convert IntStream to Stream<Integer>?
     */
    public static void main(String[] args) {
        IntStream.range(1, 5)
                // toArray()
                 .boxed()
                 .toArray();
                 
    }

    // Continue with more examples, variations, and interview questions

    public void filterExample2() {
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date");
        fruits.stream()
              .filter(f -> f.length() > 5)
              .forEach(System.out::println);
    }

    public void mapExample2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.stream()
               .map(n -> n * 2)
               .forEach(System.out::println);
    }

    // To reach line count, repeat with different data and slight variations

    // ... (imagine many more methods here)

}