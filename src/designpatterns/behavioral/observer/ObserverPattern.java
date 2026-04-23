package designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVER PATTERN (Publish-Subscribe)
 * 
 * Intent: Define a one-to-many dependency between objects so that when one object changes state,
 * all its dependents are notified automatically.
 * 
 * Use when:
 * - A change to one object requires changing others, but you don't know how many
 * - An object should notify other objects without assumptions about those objects
 * - You need loose coupling between communicating objects
 * 
 * Example: Event systems, MVC architectures, real-time data feeds
 */

// Observer interface
interface Observer {
    void update(String eventData);
}

// Concrete Observers
class EmailListener implements Observer {
    @Override
    public void update(String eventData) {
        System.out.println("EmailListener: Received event - " + eventData);
    }
}

class SMSListener implements Observer {
    @Override
    public void update(String eventData) {
        System.out.println("SMSListener: Received event - " + eventData);
    }
}

class LoggerListener implements Observer {
    @Override
    public void update(String eventData) {
        System.out.println("LoggerListener: Logging event - " + eventData);
    }
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class EventManager implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String eventData;
    
    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(eventData);
        }
    }
    
    public void setEventData(String eventData) {
        this.eventData = eventData;
        notifyObservers();
    }
}

// Another example: Temperature sensor
interface TemperatureObserver {
    void update(double temperature);
}

class TemperatureDisplay implements TemperatureObserver {
    @Override
    public void update(double temperature) {
        System.out.println("Display: Current temperature = " + temperature + "°C");
    }
}

class TemperatureAlarm implements TemperatureObserver {
    @Override
    public void update(double temperature) {
        if (temperature > 100) {
            System.out.println("ALARM: Temperature is too high!");
        }
    }
}

class TemperatureSensor {
    private List<TemperatureObserver> observers = new ArrayList<>();
    private double temperature;
    
    public void attach(TemperatureObserver observer) {
        observers.add(observer);
    }
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (TemperatureObserver observer : observers) {
            observer.update(temperature);
        }
    }
}

/**
 * Example usage:
 */
class ObserverDemo {
    public static void main(String[] args) {
        // Event manager example
        EventManager eventManager = new EventManager();
        
        Observer emailListener = new EmailListener();
        Observer smsListener = new SMSListener();
        Observer loggerListener = new LoggerListener();
        
        eventManager.attach(emailListener);
        eventManager.attach(smsListener);
        eventManager.attach(loggerListener);
        
        eventManager.setEventData("User registered successfully");
        
        System.out.println();
        
        // Temperature sensor example
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(new TemperatureDisplay());
        sensor.attach(new TemperatureAlarm());
        
        sensor.setTemperature(25);
        sensor.setTemperature(105);
    }
}
