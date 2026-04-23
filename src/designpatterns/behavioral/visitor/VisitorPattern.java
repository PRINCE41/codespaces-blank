package designpatterns.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * VISITOR PATTERN
 * 
 * Intent: Represent an operation to be performed on the elements of an object structure. 
 * Visitor lets you define a new operation without changing the classes of the elements on which it operates.
 * 
 * Use when:
 * - Object structure contains many objects of different types
 * - Many distinct operations need to be performed on objects in the structure
 * - Object classes rarely change but operations change often
 * - You want to avoid "pollution" of object classes with operations
 * 
 * Example: Document rendering, file system traversal, data export, report generation
 */

// Element interface
interface Element {
    void accept(Visitor visitor);
}

// Concrete Elements
class Book implements Element {
    private String title;
    private double price;
    
    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
    
    public String getTitle() {
        return title;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class DVD implements Element {
    private String title;
    private double price;
    
    public DVD(String title, double price) {
        this.title = title;
        this.price = price;
    }
    
    public String getTitle() {
        return title;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Magazine implements Element {
    private String title;
    private double price;
    
    public Magazine(String title, double price) {
        this.title = title;
        this.price = price;
    }
    
    public String getTitle() {
        return title;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Visitor interface
interface Visitor {
    void visit(Book element);
    void visit(DVD element);
    void visit(Magazine element);
}

// Concrete Visitors
class PriceCalculator implements Visitor {
    private double totalPrice = 0;
    
    @Override
    public void visit(Book element) {
        totalPrice += element.getPrice();
        System.out.println("Book: " + element.getTitle() + " - $" + element.getPrice());
    }
    
    @Override
    public void visit(DVD element) {
        totalPrice += element.getPrice();
        System.out.println("DVD: " + element.getTitle() + " - $" + element.getPrice());
    }
    
    @Override
    public void visit(Magazine element) {
        totalPrice += element.getPrice();
        System.out.println("Magazine: " + element.getTitle() + " - $" + element.getPrice());
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
}

class InventoryVisitor implements Visitor {
    private int bookCount = 0;
    private int dvdCount = 0;
    private int magazineCount = 0;
    
    @Override
    public void visit(Book element) {
        bookCount++;
    }
    
    @Override
    public void visit(DVD element) {
        dvdCount++;
    }
    
    @Override
    public void visit(Magazine element) {
        magazineCount++;
    }
    
    public void displayInventory() {
        System.out.println("Inventory: Books=" + bookCount + ", DVDs=" + dvdCount + ", Magazines=" + magazineCount);
    }
}

// Object structure
class Store {
    private List<Element> elements = new ArrayList<>();
    
    public void addItem(Element element) {
        elements.add(element);
    }
    
    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}

// Another example: File system visitor
interface FileSystemElement {
    void accept(FileVisitor visitor);
}

class TextFile implements FileSystemElement {
    private String name;
    private int size;
    
    public TextFile(String name, int size) {
        this.name = name;
        this.size = size;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

class Directory implements FileSystemElement {
    private String name;
    private List<FileSystemElement> contents = new ArrayList<>();
    
    public Directory(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void add(FileSystemElement element) {
        contents.add(element);
    }
    
    public List<FileSystemElement> getContents() {
        return contents;
    }
    
    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

interface FileVisitor {
    void visit(TextFile file);
    void visit(Directory directory);
}

class FileSizeCalculator implements FileVisitor {
    private long totalSize = 0;
    
    @Override
    public void visit(TextFile file) {
        totalSize += file.getSize();
        System.out.println("File: " + file.getName() + " - " + file.getSize() + " bytes");
    }
    
    @Override
    public void visit(Directory directory) {
        System.out.println("Directory: " + directory.getName());
        for (FileSystemElement element : directory.getContents()) {
            element.accept(this);
        }
    }
    
    public long getTotalSize() {
        return totalSize;
    }
}

/**
 * Example usage:
 */
class VisitorDemo {
    public static void main(String[] args) {
        // Store example
        Store store = new Store();
        store.addItem(new Book("Java Design Patterns", 45.99));
        store.addItem(new DVD("The Matrix", 19.99));
        store.addItem(new Magazine("Tech Weekly", 9.99));
        store.addItem(new Book("Clean Code", 39.99));
        
        System.out.println("=== Price Calculation ===");
        PriceCalculator priceCalculator = new PriceCalculator();
        store.accept(priceCalculator);
        System.out.println("Total Price: $" + priceCalculator.getTotalPrice());
        
        System.out.println("\n=== Inventory Count ===");
        InventoryVisitor inventory = new InventoryVisitor();
        store.accept(inventory);
        inventory.displayInventory();
        
        System.out.println("\n=== File System ===");
        Directory root = new Directory("root");
        root.add(new TextFile("file1.txt", 1024));
        root.add(new TextFile("file2.txt", 2048));
        
        Directory subdir = new Directory("documents");
        subdir.add(new TextFile("doc1.txt", 512));
        subdir.add(new TextFile("doc2.txt", 768));
        root.add(subdir);
        
        FileSizeCalculator sizeCalculator = new FileSizeCalculator();
        root.accept(sizeCalculator);
        System.out.println("Total Size: " + sizeCalculator.getTotalSize() + " bytes");
    }
}
