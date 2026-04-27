package main.java.designpatterns.behavioral.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * MEDIATOR PATTERN
 * 
 * Intent: Define an object that encapsulates how a set of objects interact. Mediator promotes 
 * loose coupling by keeping objects from referring to each other explicitly.
 * 
 * Use when:
 * - Objects communicate in complex but well-defined ways
 * - Reusing objects is difficult because they refer to many other objects
 * - Behavior distributed between multiple classes should be customizable
 * - You want to avoid many-to-many communication dependencies
 * 
 * Example: Air traffic control, chat rooms, UI event handlers, game object coordination
 */

// Mediator interface
interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private Map<String, User> users = new HashMap<>();
    
    @Override
    public void addUser(User user) {
        users.put(user.getName(), user);
        user.setMediator(this);
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        System.out.println("[" + sender.getName() + "]: " + message);
        for (User user : users.values()) {
            if (!user.equals(sender)) {
                user.receive(message, sender.getName());
            }
        }
    }
}

// Colleague interface
abstract class User {
    protected ChatMediator mediator;
    protected String name;
    
    public User(String name) {
        this.name = name;
    }
    
    public void setMediator(ChatMediator mediator) {
        this.mediator = mediator;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract void send(String message);
    public abstract void receive(String message, String from);
}

// Concrete Colleague
class ChatUser extends User {
    public ChatUser(String name) {
        super(name);
    }
    
    @Override
    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void receive(String message, String from) {
        System.out.println(name + " received from " + from + ": " + message);
    }
}

// Another example: Air Traffic Control
interface AirTrafficControl {
    void registerAircraft(Aircraft aircraft);
    void requestLanding(Aircraft aircraft);
    void requestTakeoff(Aircraft aircraft);
}

class Tower implements AirTrafficControl {
    private boolean runway = true; // true = available
    
    @Override
    public void registerAircraft(Aircraft aircraft) {
        aircraft.setMediator(this);
    }
    
    @Override
    public void requestLanding(Aircraft aircraft) {
        synchronized (this) {
            if (runway) {
                runway = false;
                System.out.println("Tower: Cleared for landing - " + aircraft.getName());
                aircraft.land();
                runway = true;
            } else {
                System.out.println("Tower: Runway busy, circle and wait - " + aircraft.getName());
            }
        }
    }
    
    @Override
    public void requestTakeoff(Aircraft aircraft) {
        synchronized (this) {
            if (runway) {
                runway = false;
                System.out.println("Tower: Cleared for takeoff - " + aircraft.getName());
                aircraft.takeoff();
                runway = true;
            } else {
                System.out.println("Tower: Runway busy, wait at gate - " + aircraft.getName());
            }
        }
    }
}

class Aircraft {
    private String name;
    private AirTrafficControl control;
    
    public Aircraft(String name) {
        this.name = name;
    }
    
    public void setMediator(AirTrafficControl control) {
        this.control = control;
    }
    
    public String getName() {
        return name;
    }
    
    public void requestLanding() {
        control.requestLanding(this);
    }
    
    public void requestTakeoff() {
        control.requestTakeoff(this);
    }
    
    public void land() {
        System.out.println(name + " is landing...");
    }
    
    public void takeoff() {
        System.out.println(name + " is taking off...");
    }
}

// Another example: GUI component mediator
interface GUIMediator {
    void updateButton();
    void updateTextField();
}

class LoginPanel implements GUIMediator {
    private Button loginButton;
    private TextField usernameField;
    private TextField passwordField;
    
    public LoginPanel() {
        this.loginButton = new Button("Login", this);
        this.usernameField = new TextField(this);
        this.passwordField = new TextField(this);
    }
    
    @Override
    public void updateButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        loginButton.setEnabled(!username.isEmpty() && !password.isEmpty());
    }
    
    @Override
    public void updateTextField() {
        updateButton();
    }
}

class Button {
    private String label;
    private GUIMediator mediator;
    private boolean enabled;
    
    public Button(String label, GUIMediator mediator) {
        this.label = label;
        this.mediator = mediator;
        this.enabled = false;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        System.out.println("Button " + label + " is " + (enabled ? "enabled" : "disabled"));
    }
}

class TextField {
    private GUIMediator mediator;
    private String text;
    
    public TextField(GUIMediator mediator) {
        this.mediator = mediator;
        this.text = "";
    }
    
    public void setText(String text) {
        this.text = text;
        mediator.updateTextField();
    }
    
    public String getText() {
        return text;
    }
}

/**
 * Example usage:
 */
class MediatorDemo {
    public static void main(String[] args) {
        // Chat room example
        System.out.println("=== Chat Room ===");
        ChatMediator chatRoom = new ChatRoom();
        
        User user1 = new ChatUser("Alice");
        User user2 = new ChatUser("Bob");
        User user3 = new ChatUser("Charlie");
        
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        
        user1.send("Hello everyone!");
        user2.send("Hi Alice!");
        
        System.out.println("\n=== Air Traffic Control ===");
        AirTrafficControl tower = new Tower();
        
        Aircraft plane1 = new Aircraft("AA100");
        Aircraft plane2 = new Aircraft("UA200");
        
        tower.registerAircraft(plane1);
        tower.registerAircraft(plane2);
        
        plane1.requestTakeoff();
        plane2.requestTakeoff();
        plane1.requestLanding();
        plane2.requestLanding();
    }
}
