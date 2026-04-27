package main.java.designpatterns.structural.bridge;

/**
 * BRIDGE PATTERN (Handle/Body)
 * 
 * Intent: Decouple an abstraction from its implementation so the two can vary independently.
 * 
 * Use when:
 * - You want to avoid permanent binding between abstraction and implementation
 * - Changes in implementation shouldn't affect clients
 * - You want to share implementation among multiple objects
 * - You have an explosion of subclasses from combining abstractions
 * 
 * Example: Device-specific rendering, database implementations, shape drawing with colors
 */

// Implementor interface (Implementation side)
interface Renderer {
    void renderCircle(double radius);
    void renderSquare(double side);
}

// Concrete Implementors
class RasterRenderer implements Renderer {
    @Override
    public void renderCircle(double radius) {
        System.out.println("Rasterizing circle with radius " + radius);
    }
    
    @Override
    public void renderSquare(double side) {
        System.out.println("Rasterizing square with side " + side);
    }
}

class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(double radius) {
        System.out.println("Drawing vector circle with radius " + radius);
    }
    
    @Override
    public void renderSquare(double side) {
        System.out.println("Drawing vector square with side " + side);
    }
}

// Abstraction (Abstract side)
abstract class Shape {
    protected Renderer renderer;
    
    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    
    abstract void draw();
}

// Refined Abstractions
class Circle extends Shape {
    private double radius;
    
    public Circle(Renderer renderer, double radius) {
        super(renderer);
        this.radius = radius;
    }
    
    @Override
    void draw() {
        renderer.renderCircle(radius);
    }
}

class Square extends Shape {
    private double side;
    
    public Square(Renderer renderer, double side) {
        super(renderer);
        this.side = side;
    }
    
    @Override
    void draw() {
        renderer.renderSquare(side);
    }
}

// Another example: Remote control and device
interface Device {
    void turnOn();
    void turnOff();
    void setChannel(int channel);
}

class Television implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV is on");
    }
    
    @Override
    public void turnOff() {
        System.out.println("TV is off");
    }
    
    @Override
    public void setChannel(int channel) {
        System.out.println("TV channel set to " + channel);
    }
}

abstract class RemoteControl {
    protected Device device;
    
    public RemoteControl(Device device) {
        this.device = device;
    }
    
    abstract void togglePower();
    abstract void nextChannel();
}

class BasicRemoteControl extends RemoteControl {
    public BasicRemoteControl(Device device) {
        super(device);
    }
    
    @Override
    void togglePower() {
        device.turnOn();
    }
    
    @Override
    void nextChannel() {
        device.setChannel(1);
    }
}

/**
 * Example usage:
 */
class BridgeDemo {
    public static void main(String[] args) {
        // Shape and renderer bridge
        Renderer rasterRenderer = new RasterRenderer();
        Shape rasterCircle = new Circle(rasterRenderer, 5.0);
        rasterCircle.draw();
        
        Renderer vectorRenderer = new VectorRenderer();
        Shape vectorSquare = new Square(vectorRenderer, 10.0);
        vectorSquare.draw();
        
        System.out.println();
        
        // Remote and device bridge
        Device tv = new Television();
        RemoteControl remote = new BasicRemoteControl(tv);
        remote.togglePower();
        remote.nextChannel();
    }
}
