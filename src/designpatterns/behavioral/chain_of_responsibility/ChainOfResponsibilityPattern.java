package designpatterns.behavioral.chain_of_responsibility;

/**
 * CHAIN OF RESPONSIBILITY PATTERN
 * 
 * Intent: Avoid coupling the sender of a request to its receiver by giving more than one object 
 * a chance to handle the request. Chain the receiving objects and pass the request along the chain.
 * 
 * Use when:
 * - More than one object may handle a request and the handler isn't known in advance
 * - You want to issue a request to one of several objects without specifying the receiver explicitly
 * - You want to dynamically specify the set of objects that will handle a request
 * 
 * Example: Logging systems, exception handling, approval workflows, event handling
 */

// Handler interface
abstract class Logger {
    protected Logger nextLogger;
    protected int level;
    
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
    
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }
    
    abstract void write(String message);
}

// Concrete Handlers
class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }
    
    @Override
    void write(String message) {
        System.out.println("Console Logger: " + message);
    }
}

class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }
    
    @Override
    void write(String message) {
        System.out.println("File Logger: " + message);
    }
}

class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }
    
    @Override
    void write(String message) {
        System.out.println("Error Logger: " + message);
    }
}

// Log levels
class LogLevel {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;
}

// Another example: Approval chain
class ApprovalRequest {
    private double amount;
    private String description;
    
    public ApprovalRequest(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
}

abstract class Approver {
    protected Approver nextApprover;
    protected double approvalLimit;
    
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }
    
    public void approve(ApprovalRequest request) {
        if (request.getAmount() <= approvalLimit) {
            processApproval(request);
        } else if (nextApprover != null) {
            System.out.println(getClass().getSimpleName() + " cannot approve, passing to next");
            nextApprover.approve(request);
        } else {
            System.out.println("Request cannot be approved");
        }
    }
    
    protected abstract void processApproval(ApprovalRequest request);
}

class Manager extends Approver {
    public Manager() {
        this.approvalLimit = 1000;
    }
    
    @Override
    protected void processApproval(ApprovalRequest request) {
        System.out.println("Manager approved request: " + request.getDescription() + " ($" + request.getAmount() + ")");
    }
}

class Director extends Approver {
    public Director() {
        this.approvalLimit = 5000;
    }
    
    @Override
    protected void processApproval(ApprovalRequest request) {
        System.out.println("Director approved request: " + request.getDescription() + " ($" + request.getAmount() + ")");
    }
}

class President extends Approver {
    public President() {
        this.approvalLimit = 100000;
    }
    
    @Override
    protected void processApproval(ApprovalRequest request) {
        System.out.println("President approved request: " + request.getDescription() + " ($" + request.getAmount() + ")");
    }
}

/**
 * Example usage:
 */
class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Logger chain example
        Logger consoleLogger = new ConsoleLogger(LogLevel.INFO);
        Logger fileLogger = new FileLogger(LogLevel.DEBUG);
        Logger errorLogger = new ErrorLogger(LogLevel.ERROR);
        
        consoleLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(errorLogger);
        
        consoleLogger.logMessage(LogLevel.INFO, "Info message");
        consoleLogger.logMessage(LogLevel.DEBUG, "Debug message");
        consoleLogger.logMessage(LogLevel.ERROR, "Error message");
        
        System.out.println();
        
        // Approval chain example
        Approver manager = new Manager();
        Approver director = new Director();
        Approver president = new President();
        
        manager.setNextApprover(director);
        director.setNextApprover(president);
        
        manager.approve(new ApprovalRequest(500, "Office supplies"));
        manager.approve(new ApprovalRequest(3000, "Travel expenses"));
        manager.approve(new ApprovalRequest(50000, "Equipment purchase"));
    }
}
