package main.java.codingPrac;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GPT30062026 {
    public static void main(String[] args) {

        p30062026_4();
    }


    // find second largest number in an array using rank in sql query
    public static void p30062026_12(){
        String secondLargest = "SELECT num FROM (SELECT num, DENSE_RANK() OVER (ORDER BY num DESC) AS rnk FROM arr) ranked WHERE rnk = 2;";
        // Note: This is a placeholder for the SQL query. In practice, you would execute this query against a database containing the array data.
        System.out.println("Second largest number in the array: " + secondLargest);
    }


    // find second largest number in an array using sql query
    public static void p30062026_11(){
        String secondLargest = "SELECT MAX(num) FROM arr WHERE num < (SELECT MAX(num) FROM arr)";
        // Note: This is a placeholder for the SQL query. In practice, you would execute this query against a database containing the array data.
        System.out.println("Second largest number in the array: " + secondLargest);
    }


    // find second largest number in an array using streams
    public static void p30062026_10(){
        int[] arr = {3, 5, 7, 2, 8, 1, 4};
        int secondLargest = Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> b - a)
                .distinct()
                .skip(1)
                .findFirst()
                .orElse(Integer.MIN_VALUE);
        System.out.println("Second largest number in the array: " + secondLargest);
    }


    // find second largest number in an array
    public static void p30062026_9(){
        int[] arr = {3, 5, 7, 2, 8, 1, 4};
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        System.out.println("Second largest number in the array: " + secondLargest);
    }

    

    // find the 1st non-repeating character in a string
    public static void p30062026_8(){
        String input = "swiss";
        Map<Character, Long> charCountMap = input.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        @SuppressWarnings("null")
        Character firstNonRepeatingChar = charCountMap.entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);
        System.out.println("First non-repeating character in '" + input + "': " + firstNonRepeatingChar);
    }


    // frequency of each character in a string
    public static void p30062026_7(){
        String input = "hello world";
        Map<Character, Long> map = input.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Character frequencies in '" + input + "': " + map);
    }

    // anagram check without sorting
    public static void p30062026_6(){
        String str1 = "listen";
        String str2 = "silent";
        if (str1.length() != str2.length()) {
            System.out.println("Are anagrams: false");
            return;
        }
        int[] count = new int[256]; // Assuming ASCII character set
        for (char c : str1.toCharArray()) {
            count[c]++;
        }
        for (char c : str2.toCharArray()) { 
            count[c]--;
            if (count[c] < 0) {
                System.out.println("Are anagrams: false");
                return;
            }
        }
        System.out.println("Are anagrams: true");
    }



    // anagram check
    public static void p30062026_5(){
        String str1 = "listen";
        String str2 = "silent";
        boolean isAnagram = str1.length() == str2.length() && str1.chars().sorted().toArray().equals(str2.chars().sorted().toArray());
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("Are anagrams: " + isAnagram);
    }

    // palindrome check
    public static void p30062026_4(){
        String input = "A man, a plan, a canal: Panama";
        String cleanedInput = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleanedInput).reverse().toString();
        boolean isPalindrome = cleanedInput.equals(reversed);
        System.out.println("Input: " + input);
        System.out.println("Is palindrome: " + isPalindrome);
    }


    // reverse a given string
    public static void p30062026_3(){
        String input = "Hello, World!";
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }


    // reverse a given string using StringBuilder
    public static void p30062026_2(){
        String input = "Hello, World!";
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }



    // Example of using a lambda expression & a stream to process a list
    public static void p30062026_1(){
        Runnable task = () -> System.out.println("Task executed using a lambda expression");
        new Thread(task).start();

        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);
    }




    
}
