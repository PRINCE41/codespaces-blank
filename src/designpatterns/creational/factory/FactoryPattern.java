package designpatterns.creational.factory;

/**
 * FACTORY METHOD PATTERN
 * 
 * Intent: Create an interface for creating an object, but let subclasses decide which class to instantiate.
 * 
 * Use when:
 * - A class cannot anticipate the type of objects it needs to create
 * - You want to delegate object creation to subclasses
 * - You want to centralize object creation logic
 * 
 * Example: Database drivers, UI components, document types
 */

// Product interface
interface Transport {
    void deliver();
}

// Concrete products
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by truck on road");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by ship on sea");
    }
}

class Plane implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by plane in air");
    }
}

// Abstract creator
abstract class Logistics {
    abstract Transport createTransport();
    
    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

// Concrete creators
class RoadLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Ship();
    }
}

class AirLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Plane();
    }
}

/**
 * SIMPLE FACTORY (Alternative approach - not a GoF pattern but practical)
 */
class TransportFactory {
    public static Transport createTransport(String type) {
        switch (type.toLowerCase()) {
            case "truck":
                return new Truck();
            case "ship":
                return new Ship();
            case "plane":
                return new Plane();
            default:
                throw new IllegalArgumentException("Unknown transport type: " + type);
        }
    }
}

/**
 * Example usage:
 */
class FactoryDemo {
    public static void main(String[] args) {
        // Using factory method
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDelivery();
        
        Logistics seaLogistics = new SeaLogistics();
        seaLogistics.planDelivery();
        
        // Using simple factory
        Transport plane = TransportFactory.createTransport("plane");
        plane.deliver();
    }
}
