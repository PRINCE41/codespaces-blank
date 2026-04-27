package main.java.designpatterns;

/**
 * COMPREHENSIVE DESIGN PATTERNS REFERENCE
 * 
 * This package contains implementations of all 23 Gang of Four (GoF) design patterns:
 * 
 * CREATIONAL PATTERNS (5):
 * 1. Singleton - Ensure a class has only one instance
 * 2. Factory Method - Create objects without specifying exact classes
 * 3. Abstract Factory - Create families of related objects
 * 4. Builder - Construct complex objects step by step
 * 5. Prototype - Clone objects instead of creating new ones
 * 
 * STRUCTURAL PATTERNS (7):
 * 6. Adapter - Convert interface of a class to another clients expect
 * 7. Bridge - Decouple abstraction from implementation
 * 8. Composite - Compose objects into tree structures
 * 9. Decorator - Add responsibilities to objects dynamically
 * 10. Facade - Provide unified interface to subsystem
 * 11. Flyweight - Share objects to support large numbers efficiently
 * 12. Proxy - Provide surrogate for another object
 * 
 * BEHAVIORAL PATTERNS (11):
 * 13. Observer - Notify multiple objects about state changes
 * 14. Strategy - Define family of algorithms and make them interchangeable
 * 15. Command - Encapsulate request as object
 * 16. Chain of Responsibility - Pass request along chain of handlers
 * 17. State - Allow object to alter behavior when state changes
 * 18. Template Method - Define algorithm skeleton in base class
 * 19. Visitor - Represent operation on object structure elements
 * 20. Iterator - Access elements sequentially without exposing structure
 * 21. Mediator - Encapsulate how objects interact
 * 22. Memento - Capture and restore object's internal state
 * 23. Interpreter - Represent and interpret language grammar
 */

public class DesignPatternsReference {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║          GANG OF FOUR DESIGN PATTERNS IMPLEMENTATION           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        printSection("CREATIONAL PATTERNS (Object Creation)");
        System.out.println("  1. Singleton          - One instance, global access");
        System.out.println("  2. Factory Method     - Create without specifying classes");
        System.out.println("  3. Abstract Factory   - Create related object families");
        System.out.println("  4. Builder            - Complex object construction");
        System.out.println("  5. Prototype          - Clone objects");
        System.out.println();
        
        printSection("STRUCTURAL PATTERNS (Object Composition)");
        System.out.println("  6. Adapter            - Interface conversion");
        System.out.println("  7. Bridge             - Separate abstraction from implementation");
        System.out.println("  8. Composite          - Tree structures");
        System.out.println("  9. Decorator          - Add responsibilities dynamically");
        System.out.println(" 10. Facade            - Unified interface to subsystem");
        System.out.println(" 11. Flyweight         - Share fine-grained objects");
        System.out.println(" 12. Proxy             - Control access to another object");
        System.out.println();
        
        printSection("BEHAVIORAL PATTERNS (Object Interaction)");
        System.out.println(" 13. Observer          - Notify about state changes");
        System.out.println(" 14. Strategy          - Interchangeable algorithms");
        System.out.println(" 15. Command           - Encapsulate requests as objects");
        System.out.println(" 16. Chain of Resp.    - Pass request down handler chain");
        System.out.println(" 17. State             - Alter behavior on state change");
        System.out.println(" 18. Template Method   - Algorithm skeleton");
        System.out.println(" 19. Visitor           - Operations on object structures");
        System.out.println(" 20. Iterator          - Sequential access without exposure");
        System.out.println(" 21. Mediator          - Encapsulate interaction");
        System.out.println(" 22. Memento           - Capture/restore state");
        System.out.println(" 23. Interpreter       - Define and interpret language");
        System.out.println();
        
        printSection("HOW TO RUN THE EXAMPLES");
        System.out.println("Each pattern has its own demo class:");
        System.out.println();
        System.out.println("  CREATIONAL:");
        System.out.println("    javac -d . src/designpatterns/creational/singleton/Singleton.java");
        System.out.println("    java designpatterns.creational.singleton.SingletonDemo");
        System.out.println();
        System.out.println("  STRUCTURAL:");
        System.out.println("    javac -d . src/designpatterns/structural/adapter/AdapterPattern.java");
        System.out.println("    java designpatterns.structural.adapter.AdapterDemo");
        System.out.println();
        System.out.println("  BEHAVIORAL:");
        System.out.println("    javac -d . src/designpatterns/behavioral/observer/ObserverPattern.java");
        System.out.println("    java designpatterns.behavioral.observer.ObserverDemo");
        System.out.println();
        
        printSection("KEY CONCEPTS");
        System.out.println("Pattern Selection Guide:");
        System.out.println("  • Use CREATIONAL when object creation is complex");
        System.out.println("  • Use STRUCTURAL when composing objects into structures");
        System.out.println("  • Use BEHAVIORAL for communication between objects");
        System.out.println();
        
        printSection("QUICK REFERENCE");
        System.out.println("When to use each pattern:");
        System.out.println();
        System.out.println("Singleton         → Need exactly one instance (loggers, configs)");
        System.out.println("Factory Method    → Avoid hardcoding object creation");
        System.out.println("Builder           → Many optional parameters (houses, URLs)");
        System.out.println("Adapter           → Use incompatible interfaces together");
        System.out.println("Decorator         → Add features without subclassing");
        System.out.println("Observer          → Many objects react to one object");
        System.out.println("Strategy          → Different algorithm implementations");
        System.out.println("Command           → Undo/redo, queuing operations");
        System.out.println("Chain of Resp.    → Multiple handlers in sequence");
        System.out.println("State             → Behavior depends on state");
        System.out.println("Template Method   → Share algorithm skeleton");
        System.out.println("Visitor           → Operations on complex structures");
        System.out.println("Mediator          → Complex object communication");
        System.out.println("Memento           → Undo/save checkpoints");
        System.out.println();
        
        printSection("DIRECTORY STRUCTURE");
        System.out.println("src/");
        System.out.println("├── designpatterns/");
        System.out.println("│   ├── creational/");
        System.out.println("│   │   ├── singleton/");
        System.out.println("│   │   ├── factory/");
        System.out.println("│   │   ├── builder/");
        System.out.println("│   │   └── prototype/");
        System.out.println("│   ├── structural/");
        System.out.println("│   │   ├── adapter/");
        System.out.println("│   │   ├── bridge/");
        System.out.println("│   │   ├── composite/");
        System.out.println("│   │   ├── decorator/");
        System.out.println("│   │   ├── facade/");
        System.out.println("│   │   ├── flyweight/");
        System.out.println("│   │   └── proxy/");
        System.out.println("│   └── behavioral/");
        System.out.println("│       ├── observer/");
        System.out.println("│       ├── strategy/");
        System.out.println("│       ├── command/");
        System.out.println("│       ├── chain_of_responsibility/");
        System.out.println("│       ├── state/");
        System.out.println("│       ├── template_method/");
        System.out.println("│       ├── visitor/");
        System.out.println("│       ├── iterator/");
        System.out.println("│       ├── mediator/");
        System.out.println("│       ├── memento/");
        System.out.println("│       └── interpreter/");
        System.out.println();
    }
    
    private static void printSection(String title) {
        System.out.println("┌" + "─".repeat(title.length() + 2) + "┐");
        System.out.println("│ " + title + " │");
        System.out.println("└" + "─".repeat(title.length() + 2) + "┘");
    }
}
