package main.java.learning.corejava;

import java.util.ArrayList;
import java.util.List;

/**
 * Core Java concepts demo covering OOP, collections, and exception handling.
 */
public class CoreJavaConceptsDemo {

    public static void main(String[] args) {
        System.out.println("=== Core Java Concepts ===");

        Employee employee = new Employee("Asha", 28);
        employee.display();

        List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
        names.forEach(System.out::println);

        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException exception) {
            System.out.println("Handled arithmetic exception: " + exception.getMessage());
        }
    }

    static class Employee {
        private final String name;
        private final int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void display() {
            System.out.println("Employee name: " + name + ", age: " + age);
        }
    }
}
