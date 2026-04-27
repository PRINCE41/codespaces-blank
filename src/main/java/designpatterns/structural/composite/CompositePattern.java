package main.java.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * COMPOSITE PATTERN
 * 
 * Intent: Compose objects into tree structures to represent part-whole hierarchies. Composite 
 * lets clients treat individual objects and compositions of objects uniformly.
 * 
 * Use when:
 * - You have a tree structure that needs to be manipulated uniformly
 * - Objects can be composed recursively
 * - You want clients to ignore the difference between leaf and composite objects
 * 
 * Example: File systems, GUI component hierarchies, organization hierarchies
 */

// Component interface
interface FileSystemComponent {
    void display(String indent);
    int getSize();
}

// Leaf - File
class File implements FileSystemComponent {
    private String name;
    private int size;
    
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public void display(String indent) {
        System.out.println(indent + "File: " + name + " (" + size + " KB)");
    }
    
    @Override
    public int getSize() {
        return size;
    }
}

// Composite - Directory
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();
    
    public Directory(String name) {
        this.name = name;
    }
    
    public void add(FileSystemComponent component) {
        components.add(component);
    }
    
    public void remove(FileSystemComponent component) {
        components.remove(component);
    }
    
    @Override
    public void display(String indent) {
        System.out.println(indent + "Directory: " + name);
        for (FileSystemComponent component : components) {
            component.display(indent + "  ");
        }
    }
    
    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

// Another example: GUI Components
interface UIComponent {
    void render();
    void add(UIComponent component);
    void remove(UIComponent component);
}

class Button implements UIComponent {
    private String label;
    
    public Button(String label) {
        this.label = label;
    }
    
    @Override
    public void render() {
        System.out.println("Rendering button: " + label);
    }
    
    @Override
    public void add(UIComponent component) {
        // Leaf can't have children
    }
    
    @Override
    public void remove(UIComponent component) {
        // Leaf can't have children
    }
}

class Panel implements UIComponent {
    private String name;
    private List<UIComponent> children = new ArrayList<>();
    
    public Panel(String name) {
        this.name = name;
    }
    
    @Override
    public void render() {
        System.out.println("Rendering panel: " + name);
        for (UIComponent child : children) {
            child.render();
        }
    }
    
    @Override
    public void add(UIComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(UIComponent component) {
        children.remove(component);
    }
}

/**
 * Example usage:
 */
class CompositeDemo {
    public static void main(String[] args) {
        // File system example
        Directory root = new Directory("root");
        
        File file1 = new File("document.txt", 100);
        File file2 = new File("image.jpg", 500);
        root.add(file1);
        root.add(file2);
        
        Directory documents = new Directory("documents");
        File file3 = new File("report.pdf", 200);
        documents.add(file3);
        root.add(documents);
        
        root.display("");
        System.out.println("Total size: " + root.getSize() + " KB");
        
        System.out.println();
        
        // GUI example
        Panel mainPanel = new Panel("Main");
        mainPanel.add(new Button("OK"));
        mainPanel.add(new Button("Cancel"));
        
        Panel subPanel = new Panel("Sub");
        subPanel.add(new Button("Save"));
        subPanel.add(new Button("Load"));
        mainPanel.add(subPanel);
        
        mainPanel.render();
    }
}
