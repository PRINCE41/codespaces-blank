package main.java.designpatterns.behavioral.template_method;

/**
 * TEMPLATE METHOD PATTERN
 * 
 * Intent: Define the skeleton of an algorithm in a method, deferring some steps to subclasses. 
 * Template Method lets subclasses redefine certain steps of an algorithm without changing the 
 * algorithm's structure.
 * 
 * Use when:
 * - You have an algorithm with common and variant parts
 * - You want to avoid code duplication among similar classes
 * - You want to control subclass extensions
 * - You want to promote code reuse through inheritance
 * 
 * Example: Data processing pipelines, game AI, recipe preparation, document rendering
 */

// Abstract class with template method
abstract class CaffeineBeverage {
    // Template method
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    
    private void boilWater() {
        System.out.println("Boiling water");
    }
    
    private void pourInCup() {
        System.out.println("Pouring into cup");
    }
    
    // Abstract methods to be implemented by subclasses
    abstract void brew();
    abstract void addCondiments();
}

// Concrete classes
class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Brewing coffee");
    }
    
    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Steeping tea");
    }
    
    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}

// Another example: Data processing
abstract class DataProcessor {
    // Template method defining the algorithm structure
    public final void processData(String filename) {
        readFile(filename);
        parseData();
        validateData();
        transformData();
        saveData();
    }
    
    protected abstract void readFile(String filename);
    protected abstract void parseData();
    
    // Common steps with default implementation (can be overridden)
    protected void validateData() {
        System.out.println("Validating data");
    }
    
    protected abstract void transformData();
    protected abstract void saveData();
}

class CSVProcessor extends DataProcessor {
    @Override
    protected void readFile(String filename) {
        System.out.println("Reading CSV file: " + filename);
    }
    
    @Override
    protected void parseData() {
        System.out.println("Parsing CSV data");
    }
    
    @Override
    protected void transformData() {
        System.out.println("Transforming CSV to objects");
    }
    
    @Override
    protected void saveData() {
        System.out.println("Saving to database");
    }
}

class JSONProcessor extends DataProcessor {
    @Override
    protected void readFile(String filename) {
        System.out.println("Reading JSON file: " + filename);
    }
    
    @Override
    protected void parseData() {
        System.out.println("Parsing JSON data");
    }
    
    @Override
    protected void transformData() {
        System.out.println("Transforming JSON to objects");
    }
    
    @Override
    protected void saveData() {
        System.out.println("Saving to NoSQL database");
    }
}

class XMLProcessor extends DataProcessor {
    @Override
    protected void readFile(String filename) {
        System.out.println("Reading XML file: " + filename);
    }
    
    @Override
    protected void parseData() {
        System.out.println("Parsing XML data");
    }
    
    @Override
    protected void validateData() {
        System.out.println("Validating against XSD schema");
    }
    
    @Override
    protected void transformData() {
        System.out.println("Transforming XML to objects");
    }
    
    @Override
    protected void saveData() {
        System.out.println("Saving to XML repository");
    }
}

// Another example: Game character
abstract class GameCharacter {
    public final void play() {
        intro();
        action();
        summary();
    }
    
    abstract void intro();
    abstract void action();
    abstract void summary();
}

class Warrior extends GameCharacter {
    @Override
    void intro() {
        System.out.println("Warrior enters the scene");
    }
    
    @Override
    void action() {
        System.out.println("Warrior attacks with sword");
    }
    
    @Override
    void summary() {
        System.out.println("Warrior wins the battle");
    }
}

class Mage extends GameCharacter {
    @Override
    void intro() {
        System.out.println("Mage enters the scene");
    }
    
    @Override
    void action() {
        System.out.println("Mage casts a spell");
    }
    
    @Override
    void summary() {
        System.out.println("Mage defeats enemies with magic");
    }
}

/**
 * Example usage:
 */
class TemplateMethodDemo {
    public static void main(String[] args) {
        // Beverage example
        System.out.println("=== Preparing Coffee ===");
        CaffeineBeverage coffee = new Coffee();
        coffee.prepareBeverage();
        
        System.out.println("\n=== Preparing Tea ===");
        CaffeineBeverage tea = new Tea();
        tea.prepareBeverage();
        
        System.out.println("\n=== Data Processing ===");
        DataProcessor csvProcessor = new CSVProcessor();
        csvProcessor.processData("data.csv");
        
        System.out.println("\n");
        DataProcessor jsonProcessor = new JSONProcessor();
        jsonProcessor.processData("data.json");
        
        System.out.println("\n");
        DataProcessor xmlProcessor = new XMLProcessor();
        xmlProcessor.processData("data.xml");
        
        System.out.println("\n=== Game Characters ===");
        GameCharacter warrior = new Warrior();
        warrior.play();
        
        System.out.println();
        GameCharacter mage = new Mage();
        mage.play();
    }
}
