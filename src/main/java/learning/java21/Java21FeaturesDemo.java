package main.java.learning.java21;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 21 feature examples: record patterns, sequenced collections, and virtual threads.
 */
public class Java21FeaturesDemo {

    public static void main(String[] args) {
        System.out.println("=== Java 21 Features ===");

        Object figure = new Rectangle(new Point(2, 3), new Point(8, 9));
        if (figure instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("Rectangle from point: (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
        }

        List<String> items = new ArrayList<>(List.of("one", "two", "three"));
        items.addFirst("zero");
        items.addLast("four");
        System.out.println(items);

        Thread virtualThread = Thread.ofVirtual().start(() -> System.out.println("Virtual thread executed successfully"));
        try {
            virtualThread.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    record Point(int x, int y) {
    }

    record Rectangle(Point start, Point end) {
    }
}
