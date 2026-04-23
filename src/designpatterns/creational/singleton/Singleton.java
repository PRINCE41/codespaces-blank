package designpatterns.creational.singleton;

/**
 * SINGLETON PATTERN
 * 
 * Intent: Ensure a class has only one instance and provide a global point of access to it.
 * 
 * Use when:
 * - There must be exactly one instance of a class
 * - It must be accessible from anywhere in the application
 * - Lazy initialization might be required
 * 
 * Example: Database connection pool, Logger, Configuration manager
 */

public class Singleton {
    // Private static instance
    private static Singleton instance;
    
    // Private constructor to prevent instantiation
    private Singleton() {
    }
    
    // Public method to get instance (thread-safe version using synchronized)
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

/**
 * EAGER INITIALIZATION VERSION
 * Thread-safe by default, but instance is created even if not used
 */
class SingletonEager {
    private static final SingletonEager instance = new SingletonEager();
    
    private SingletonEager() {
    }
    
    public static SingletonEager getInstance() {
        return instance;
    }
}

/**
 * BILL PUGH SINGLETON (Best Practice)
 * Thread-safe and lazy initialization using class loader
 */
class SingletonBillPugh {
    private SingletonBillPugh() {
    }
    
    private static class SingletonHelper {
        static final SingletonBillPugh INSTANCE = new SingletonBillPugh();
    }
    
    public static SingletonBillPugh getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

/**
 * ENUM SINGLETON (Best Practice in Java)
 * Serialization safe, reflection proof, thread-safe
 */
enum SingletonEnum {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Doing something in enum singleton");
    }
}

/**
 * Example usage:
 */
class SingletonDemo {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        
        System.out.println("Singleton1 and Singleton2 are the same: " + (singleton1 == singleton2));
        
        SingletonEnum.INSTANCE.doSomething();
    }
}
