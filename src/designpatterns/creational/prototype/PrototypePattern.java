package designpatterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * PROTOTYPE PATTERN
 * 
 * Intent: Specify the kinds of objects to create using a prototypical instance, and create 
 * new objects by copying this prototype.
 * 
 * Use when:
 * - Object creation is expensive
 * - You need to avoid subclassing
 * - Object creation requires complex initialization
 * - You need to create independent copies of objects
 * 
 * Example: Cloning documents, copying game objects, copying configurations
 */

// Prototype interface
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

// Concrete prototypes
class Circle implements Shape {
    private int x;
    private int y;
    private int radius;
    
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    // Copy constructor for cloning
    public Circle(Circle circle) {
        this.x = circle.x;
        this.y = circle.y;
        this.radius = circle.radius;
    }
    
    @Override
    public Shape clone() {
        return new Circle(this);
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle at (" + x + ", " + y + ") with radius " + radius);
    }
}

class Rectangle implements Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle(Rectangle rectangle) {
        this.x = rectangle.x;
        this.y = rectangle.y;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }
    
    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing rectangle at (" + x + ", " + y + ") with dimensions " + width + "x" + height);
    }
}

/**
 * ShapeCache using prototype pattern
 */
class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();
    
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return cachedShape != null ? cachedShape.clone() : null;
    }
    
    public static void loadCache() {
        Circle circle = new Circle(0, 0, 10);
        shapeMap.put("circle", circle);
        
        Rectangle rectangle = new Rectangle(0, 0, 20, 30);
        shapeMap.put("rectangle", rectangle);
    }
}

/**
 * Example usage:
 */
class PrototypeDemo {
    public static void main(String[] args) {
        // Load prototypes into cache
        ShapeCache.loadCache();
        
        // Clone from cache
        Shape clonedCircle = ShapeCache.getShape("circle");
        clonedCircle.draw();
        
        Shape clonedRectangle = ShapeCache.getShape("rectangle");
        clonedRectangle.draw();
        
        // Clone again - different objects but same data
        Shape anotherCircle = ShapeCache.getShape("circle");
        anotherCircle.draw();
        
        System.out.println("Are they the same object? " + (clonedCircle == anotherCircle));
    }
}
