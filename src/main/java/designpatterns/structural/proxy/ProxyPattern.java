package main.java.designpatterns.structural.proxy;

/**
 * PROXY PATTERN
 * 
 * Intent: Provide a surrogate or placeholder for another object to control access to it.
 * 
 * Use when:
 * - You need to control access to another object
 * - You want to defer object creation until needed (lazy initialization)
 * - You want to add functionality before or after accessing an object
 * - You want to implement access logging, caching, or permission checking
 * 
 * Example: Database connection pooling, remote object proxies, lazy-loaded images
 */

// Subject interface
interface DatabaseConnection {
    void executeQuery(String query);
}

// Real subject
class RealDatabaseConnection implements DatabaseConnection {
    private String connectionString;
    
    public RealDatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        System.out.println("Expensive database connection created: " + connectionString);
    }
    
    @Override
    public void executeQuery(String query) {
        System.out.println("Executing query on database: " + query);
    }
}

// Proxy
class DatabaseConnectionProxy implements DatabaseConnection {
    private String connectionString;
    private RealDatabaseConnection realConnection;
    
    public DatabaseConnectionProxy(String connectionString) {
        this.connectionString = connectionString;
    }
    
    @Override
    public void executeQuery(String query) {
        // Lazy initialization
        if (realConnection == null) {
            realConnection = new RealDatabaseConnection(connectionString);
        }
        
        // Add additional functionality
        System.out.println("[PROXY] Logging: Query being executed");
        realConnection.executeQuery(query);
        System.out.println("[PROXY] Logging: Query complete");
    }
}

// Another example: Protected proxy for access control
class User {
    private String name;
    private String role;
    
    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}

interface Document {
    void display();
    void edit(String content);
}

class RealDocument implements Document {
    private String content;
    private String title;
    
    public RealDocument(String title) {
        this.title = title;
        System.out.println("Loading document: " + title);
    }
    
    @Override
    public void display() {
        System.out.println("Document [" + title + "]: " + content);
    }
    
    @Override
    public void edit(String content) {
        this.content = content;
        System.out.println("Document [" + title + "] edited");
    }
}

class DocumentProxy implements Document {
    private RealDocument realDocument;
    private String title;
    private User user;
    
    public DocumentProxy(String title, User user) {
        this.title = title;
        this.user = user;
    }
    
    private RealDocument getRealDocument() {
        if (realDocument == null) {
            realDocument = new RealDocument(title);
        }
        return realDocument;
    }
    
    @Override
    public void display() {
        System.out.println("[PROXY] " + user.getRole() + " is viewing document: " + title);
        getRealDocument().display();
    }
    
    @Override
    public void edit(String content) {
        if ("ADMIN".equals(user.getRole())) {
            System.out.println("[PROXY] Allowing edit for ADMIN");
            getRealDocument().edit(content);
        } else {
            System.out.println("[PROXY] Access denied: Only ADMINs can edit");
        }
    }
}

/**
 * Example usage:
 */
class ProxyDemo {
    public static void main(String[] args) {
        // Virtual proxy example (lazy initialization)
        System.out.println("=== Virtual Proxy Example ===");
        DatabaseConnection proxy = new DatabaseConnectionProxy("jdbc:mysql://localhost:3306/mydb");
        System.out.println("Proxy created, no real connection yet");
        proxy.executeQuery("SELECT * FROM users");
        proxy.executeQuery("SELECT * FROM products");
        
        System.out.println();
        
        // Protection proxy example (access control)
        System.out.println("=== Protection Proxy Example ===");
        User admin = new User("John", "ADMIN");
        User viewer = new User("Jane", "VIEWER");
        
        Document adminDocument = new DocumentProxy("secret.txt", admin);
        adminDocument.display();
        adminDocument.edit("New secret content");
        
        System.out.println();
        
        Document viewerDocument = new DocumentProxy("secret.txt", viewer);
        viewerDocument.display();
        viewerDocument.edit("Try to edit");
    }
}
