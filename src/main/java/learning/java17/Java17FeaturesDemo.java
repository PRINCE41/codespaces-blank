package main.java.learning.java17;

/**
 * Java 17 feature examples: sealed classes, records, pattern matching and text blocks.
 */
public class Java17FeaturesDemo {

    public static void main(String[] args) {
        System.out.println("=== Java 17 Features ===");

        Shape shape = new Circle(5.0);
        if (shape instanceof Circle circle) {
            System.out.println("Circle radius: " + circle.radius());
        }

        String result = switch (shape) {
            case Circle circle -> "Circle area: " + Math.PI * circle.radius() * circle.radius();
            case Rectangle rectangle -> "Rectangle area: " + rectangle.width() * rectangle.height();
        };
        System.out.println(result);

        String message = """
                Java 17 text blocks make
                multi-line strings easier.
                """;
        System.out.println(message);
    }

    sealed interface Shape permits Circle, Rectangle {
    }

    record Circle(double radius) implements Shape {
    }

    record Rectangle(double width, double height) implements Shape {
    }
}
