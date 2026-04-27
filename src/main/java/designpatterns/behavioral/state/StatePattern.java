package main.java.designpatterns.behavioral.state;

/**
 * STATE PATTERN
 * 
 * Intent: Allow an object to alter its behavior when its internal state changes. The object will 
 * appear to change its class.
 * 
 * Use when:
 * - An object's behavior depends on its state and it must change behavior at runtime
 * - Operations have large, multipart conditional statements based on state
 * - You have multiple classes that differ only by their behavior
 * 
 * Example: TCP connections, vending machines, order processing, media players
 */

// State interface
interface State {
    void handle(Context context);
}

// Concrete States
class StartState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("In Start state");
        context.setState(new EndState());
    }
}

class EndState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("In End state");
        context.setState(new StartState());
    }
}

// Context
class Context {
    private State state;
    
    public Context(State state) {
        this.state = state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void request() {
        state.handle(this);
    }
}

// Another example: Traffic light
interface TrafficLightState {
    void next(TrafficLight light);
    void previous(TrafficLight light);
    void display();
}

class RedLight implements TrafficLightState {
    @Override
    public void next(TrafficLight light) {
        System.out.println("Red light -> Green light");
        light.setState(new GreenLight());
    }
    
    @Override
    public void previous(TrafficLight light) {
        System.out.println("Already at first state (Red)");
    }
    
    @Override
    public void display() {
        System.out.println("Stop! Red light");
    }
}

class YellowLight implements TrafficLightState {
    @Override
    public void next(TrafficLight light) {
        System.out.println("Yellow light -> Red light");
        light.setState(new RedLight());
    }
    
    @Override
    public void previous(TrafficLight light) {
        System.out.println("Yellow light -> Green light");
        light.setState(new GreenLight());
    }
    
    @Override
    public void display() {
        System.out.println("Caution! Yellow light");
    }
}

class GreenLight implements TrafficLightState {
    @Override
    public void next(TrafficLight light) {
        System.out.println("Green light -> Yellow light");
        light.setState(new YellowLight());
    }
    
    @Override
    public void previous(TrafficLight light) {
        System.out.println("Green light -> Red light");
        light.setState(new RedLight());
    }
    
    @Override
    public void display() {
        System.out.println("Go! Green light");
    }
}

class TrafficLight {
    private TrafficLightState state;
    
    public TrafficLight() {
        this.state = new RedLight();
    }
    
    public void setState(TrafficLightState state) {
        this.state = state;
    }
    
    public void next() {
        state.next(this);
    }
    
    public void previous() {
        state.previous(this);
    }
    
    public void display() {
        state.display();
    }
}

// Another example: Order state
interface OrderState {
    void process(Order order);
    void cancel(Order order);
    void ship(Order order);
}

class NewOrderState implements OrderState {
    @Override
    public void process(Order order) {
        System.out.println("Processing new order...");
        order.setState(new ProcessedOrderState());
    }
    
    @Override
    public void cancel(Order order) {
        System.out.println("Canceling new order");
        order.setState(new CancelledOrderState());
    }
    
    @Override
    public void ship(Order order) {
        System.out.println("Cannot ship unprocessed order");
    }
}

class ProcessedOrderState implements OrderState {
    @Override
    public void process(Order order) {
        System.out.println("Order already processed");
    }
    
    @Override
    public void cancel(Order order) {
        System.out.println("Canceling processed order");
        order.setState(new CancelledOrderState());
    }
    
    @Override
    public void ship(Order order) {
        System.out.println("Shipping processed order...");
        order.setState(new ShippedOrderState());
    }
}

class ShippedOrderState implements OrderState {
    @Override
    public void process(Order order) {
        System.out.println("Cannot process shipped order");
    }
    
    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel shipped order");
    }
    
    @Override
    public void ship(Order order) {
        System.out.println("Order already shipped");
    }
}

class CancelledOrderState implements OrderState {
    @Override
    public void process(Order order) {
        System.out.println("Cannot process cancelled order");
    }
    
    @Override
    public void cancel(Order order) {
        System.out.println("Order already cancelled");
    }
    
    @Override
    public void ship(Order order) {
        System.out.println("Cannot ship cancelled order");
    }
}

class Order {
    private OrderState state;
    
    public Order() {
        this.state = new NewOrderState();
    }
    
    public void setState(OrderState state) {
        this.state = state;
    }
    
    public void process() {
        state.process(this);
    }
    
    public void cancel() {
        state.cancel(this);
    }
    
    public void ship() {
        state.ship(this);
    }
}

/**
 * Example usage:
 */
class StateDemo {
    public static void main(String[] args) {
        // Traffic light example
        System.out.println("=== Traffic Light ===");
        TrafficLight light = new TrafficLight();
        light.display();
        light.next();
        light.display();
        light.next();
        light.display();
        
        System.out.println();
        
        // Order example
        System.out.println("=== Order Processing ===");
        Order order = new Order();
        order.process();
        order.ship();
        order.cancel();
        
        System.out.println();
        
        Order order2 = new Order();
        order2.cancel();
        order2.process();
    }
}
