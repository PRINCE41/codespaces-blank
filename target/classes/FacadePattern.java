package java.resources;

/**
 * FACADE PATTERN
 * 
 * Intent: Provide a unified, simplified interface to a set of interfaces in a subsystem. 
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 * 
 * Use when:
 * - You want to provide a simple interface to a complex subsystem
 * - You want to decouple client code from subsystem components
 * - You want to layer subsystems
 * 
 * Example: Database transaction management, Home automation system, API wrappers
 */

// Subsystem components
class DatabaseConnection {
    public void connect() {
        System.out.println("Connecting to database...");
    }
    
    public void disconnect() {
        System.out.println("Disconnecting from database...");
    }
}

class Transaction {
    public void begin() {
        System.out.println("Transaction started");
    }
    
    public void commit() {
        System.out.println("Transaction committed");
    }
    
    public void rollback() {
        System.out.println("Transaction rolled back");
    }
}

class QueryExecutor {
    public void execute(String query) {
        System.out.println("Executing query: " + query);
    }
}

class Logger {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Facade
class DatabaseFacade {
    private DatabaseConnection connection;
    private Transaction transaction;
    private QueryExecutor executor;
    private Logger logger;
    
    public DatabaseFacade() {
        this.connection = new DatabaseConnection();
        this.transaction = new Transaction();
        this.executor = new QueryExecutor();
        this.logger = new Logger();
    }
    
    public void executeQuery(String query) {
        logger.log("Starting query execution");
        connection.connect();
        transaction.begin();
        executor.execute(query);
        transaction.commit();
        connection.disconnect();
        logger.log("Query execution completed");
    }
    
    public void rollbackQuery() {
        logger.log("Rolling back query");
        transaction.rollback();
        connection.disconnect();
    }
}

// Another example: Home Automation Facade
class Light {
    public void turnOn() {
        System.out.println("Light turned on");
    }
    
    public void turnOff() {
        System.out.println("Light turned off");
    }
}

class AC {
    public void turnOn() {
        System.out.println("AC turned on");
    }
    
    public void turnOff() {
        System.out.println("AC turned off");
    }
}

class Stereo {
    public void turnOn() {
        System.out.println("Stereo turned on");
    }
    
    public void turnOff() {
        System.out.println("Stereo turned off");
    }
}

class DoorLock {
    public void lock() {
        System.out.println("Door locked");
    }
    
    public void unlock() {
        System.out.println("Door unlocked");
    }
}

class HomeAutomationFacade {
    private Light light;
    private AC ac;
    private Stereo stereo;
    private DoorLock doorLock;
    
    public HomeAutomationFacade() {
        this.light = new Light();
        this.ac = new AC();
        this.stereo = new Stereo();
        this.doorLock = new DoorLock();
    }
    
    public void movieMode() {
        System.out.println("Activating movie mode...");
        light.turnOff();
        ac.turnOn();
        stereo.turnOn();
        doorLock.lock();
    }
    
    public void bedtimeMode() {
        System.out.println("Activating bedtime mode...");
        light.turnOff();
        ac.turnOn();
        stereo.turnOff();
        doorLock.lock();
    }
}

/**
 * Example usage:
 */
class FacadeDemo {
    public static void main(String[] args) {
        // Database facade example
        DatabaseFacade dbFacade = new DatabaseFacade();
        dbFacade.executeQuery("SELECT * FROM users");
        
        System.out.println();
        
        // Home automation facade example
        HomeAutomationFacade homeAutomation = new HomeAutomationFacade();
        homeAutomation.movieMode();
        
        System.out.println();
        homeAutomation.bedtimeMode();
    }
}
