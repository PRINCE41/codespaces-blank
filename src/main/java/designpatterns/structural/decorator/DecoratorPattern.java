package main.java.designpatterns.structural.decorator;

/**
 * DECORATOR PATTERN
 * 
 * Intent: Attach additional responsibilities to an object dynamically. Decorators provide a flexible 
 * alternative to subclassing for extending functionality.
 * 
 * Use when:
 * - You want to add responsibilities to individual objects without affecting others
 * - Responsibilities can be removed dynamically
 * - You want to avoid combinatorial explosion from subclassing
 * - You want to wrap objects with new functionality
 * 
 * Example: Streams in Java (BufferedOutputStream, DataOutputStream), UI components, coffee shop
 */

// Component interface
interface Pizza {
    double cost();
    String getDescription();
}

// Concrete Component
class BasicPizza implements Pizza {
    @Override
    public double cost() {
        return 200.0;
    }
    
    @Override
    public String getDescription() {
        return "Basic Pizza";
    }
}

// Decorator abstract class
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;
    
    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

// Concrete Decorators
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }
    
    @Override
    public double cost() {
        return pizza.cost() + 50.0;
    }
    
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Cheese";
    }
}

class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }
    
    @Override
    public double cost() {
        return pizza.cost() + 45.0;
    }
    
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Pepperoni";
    }
}

class MushroomDecorator extends PizzaDecorator {
    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }
    
    @Override
    public double cost() {
        return pizza.cost() + 30.0;
    }
    
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Mushrooms";
    }
}

// Another example: Text component with decorators
interface Text {
    String render();
}

class PlainText implements Text {
    private String content;
    
    public PlainText(String content) {
        this.content = content;
    }
    
    @Override
    public String render() {
        return content;
    }
}

abstract class TextDecorator implements Text {
    protected Text text;
    
    public TextDecorator(Text text) {
        this.text = text;
    }
}

class BoldDecorator extends TextDecorator {
    public BoldDecorator(Text text) {
        super(text);
    }
    
    @Override
    public String render() {
        return "<b>" + text.render() + "</b>";
    }
}

class ItalicDecorator extends TextDecorator {
    public ItalicDecorator(Text text) {
        super(text);
    }
    
    @Override
    public String render() {
        return "<i>" + text.render() + "</i>";
    }
}

class UnderlineDecorator extends TextDecorator {
    public UnderlineDecorator(Text text) {
        super(text);
    }
    
    @Override
    public String render() {
        return "<u>" + text.render() + "</u>";
    }
}

/**
 * Example usage:
 */
class DecoratorDemo {
    public static void main(String[] args) {
        // Pizza decorator example
        Pizza pizza = new BasicPizza();
        System.out.println(pizza.getDescription() + " = " + pizza.cost());
        
        pizza = new CheeseDecorator(pizza);
        System.out.println(pizza.getDescription() + " = " + pizza.cost());
        
        pizza = new PepperoniDecorator(pizza);
        System.out.println(pizza.getDescription() + " = " + pizza.cost());
        
        pizza = new MushroomDecorator(pizza);
        System.out.println(pizza.getDescription() + " = " + pizza.cost());
        
        System.out.println();
        
        // Text decorator example
        Text text = new PlainText("Hello World");
        System.out.println("Plain: " + text.render());
        
        text = new BoldDecorator(text);
        System.out.println("Bold: " + text.render());
        
        text = new ItalicDecorator(text);
        System.out.println("Bold + Italic: " + text.render());
        
        text = new UnderlineDecorator(text);
        System.out.println("Bold + Italic + Underline: " + text.render());
    }
}
