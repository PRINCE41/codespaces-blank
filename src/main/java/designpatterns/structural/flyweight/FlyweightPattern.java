package main.java.designpatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * FLYWEIGHT PATTERN
 * 
 * Intent: Use sharing to support large numbers of fine-grained objects efficiently by sharing 
 * common state between objects.
 * 
 * Use when:
 * - Application uses many similar objects
 * - Memory optimization is a concern
 * - Objects can be divided into intrinsic and extrinsic state
 * 
 * Example: String pooling, game particles, font rendering
 */

// Flyweight interface
interface Particle {
    void draw(int x, int y);
}

// Concrete Flyweight
class SnowFlake implements Particle {
    private String type;
    
    public SnowFlake(String type) {
        this.type = type;
    }
    
    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing snowflake (" + type + ") at position (" + x + ", " + y + ")");
    }
}

// Flyweight Factory
class ParticleFactory {
    private static Map<String, Particle> particles = new HashMap<>();
    
    public static Particle getParticle(String type) {
        Particle particle = particles.get(type);
        if (particle == null) {
            particle = new SnowFlake(type);
            particles.put(type, particle);
            System.out.println("Created new snowflake of type: " + type);
        }
        return particle;
    }
}

// Context with extrinsic state
class ParticleInstance {
    private Particle particle;
    private int x;
    private int y;
    
    public ParticleInstance(Particle particle, int x, int y) {
        this.particle = particle;
        this.x = x;
        this.y = y;
    }
    
    public void draw() {
        particle.draw(x, y);
    }
}

// Another example: Tree in forest
interface TreeType {
    void draw();
}

class ConcreteTreeType implements TreeType {
    private String name;
    private String color;
    private String texture;
    
    public ConcreteTreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing " + name + " tree (Color: " + color + ", Texture: " + texture + ")");
    }
}

class TreeTypeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();
    
    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        TreeType treeType = treeTypes.get(key);
        if (treeType == null) {
            treeType = new ConcreteTreeType(name, color, texture);
            treeTypes.put(key, treeType);
            System.out.println("Created new tree type: " + key);
        }
        return treeType;
    }
}

class Tree {
    private int x;
    private int y;
    private TreeType type;
    
    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public void draw() {
        System.out.println("Drawing tree at (" + x + ", " + y + "): ");
        type.draw();
    }
}

/**
 * Example usage:
 */
class FlyweightDemo {
    public static void main(String[] args) {
        // Particle example
        System.out.println("=== Particle Example ===");
        Particle snowType1 = ParticleFactory.getParticle("hexagon");
        Particle snowType2 = ParticleFactory.getParticle("triangle");
        Particle snowType1Again = ParticleFactory.getParticle("hexagon");
        
        System.out.println("Same object? " + (snowType1 == snowType1Again));
        
        ParticleInstance p1 = new ParticleInstance(snowType1, 10, 20);
        ParticleInstance p2 = new ParticleInstance(snowType1, 30, 40);
        ParticleInstance p3 = new ParticleInstance(snowType2, 50, 60);
        
        p1.draw();
        p2.draw();
        p3.draw();
        
        System.out.println();
        
        // Tree example
        System.out.println("=== Tree Example ===");
        TreeType oakType = TreeTypeFactory.getTreeType("Oak", "Green", "Rough");
        TreeType birchType = TreeTypeFactory.getTreeType("Birch", "White", "Smooth");
        TreeType oakType2 = TreeTypeFactory.getTreeType("Oak", "Green", "Rough");
        
        System.out.println("Same oak type? " + (oakType == oakType2));
        
        Tree tree1 = new Tree(100, 200, oakType);
        Tree tree2 = new Tree(150, 250, oakType);
        Tree tree3 = new Tree(200, 300, birchType);
        
        tree1.draw();
        tree2.draw();
        tree3.draw();
    }
}
