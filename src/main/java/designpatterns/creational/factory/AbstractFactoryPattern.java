package main.java.designpatterns.creational.factory;

/**
 * ABSTRACT FACTORY PATTERN
 * 
 * Intent: Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
 * 
 * Use when:
 * - System needs to work with multiple families of related products
 * - System should be independent of how its products are created
 * - You want to provide a library of products revealing only interfaces
 * 
 * Example: UI themes (light/dark), database dialects, furniture styles
 */

// Abstract product families
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

interface TextBox {
    void render();
}

// Windows UI concrete products
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows button");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows checkbox");
    }
}

class WindowsTextBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering Windows text box");
    }
}

// Mac UI concrete products
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Mac button");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Mac checkbox");
    }
}

class MacTextBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering Mac text box");
    }
}

// Abstract factory
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
    TextBox createTextBox();
}

// Concrete factories
class WindowsUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
    
    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}

class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
    
    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}

// Application using abstract factory
class Application {
    private UIFactory factory;
    private Button button;
    private Checkbox checkbox;
    private TextBox textBox;
    
    public Application(UIFactory factory) {
        this.factory = factory;
    }
    
    public void createUI() {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
        textBox = factory.createTextBox();
    }
    
    public void render() {
        button.render();
        checkbox.render();
        textBox.render();
    }
}

/**
 * Example usage:
 */
class AbstractFactoryDemo {
    public static void main(String[] args) {
        // Create Windows UI
        UIFactory windowsFactory = new WindowsUIFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.createUI();
        windowsApp.render();
        
        System.out.println();
        
        // Create Mac UI
        UIFactory macFactory = new MacUIFactory();
        Application macApp = new Application(macFactory);
        macApp.createUI();
        macApp.render();
    }
}
