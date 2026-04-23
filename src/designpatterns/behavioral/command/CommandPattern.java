package designpatterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * COMMAND PATTERN
 * 
 * Intent: Encapsulate a request as an object, thereby letting you parameterize clients with 
 * different requests, queue requests, and log requests.
 * 
 * Use when:
 * - You need to parameterize objects with operations
 * - You need to queue operations, schedule their execution, or support undo/redo
 * - You need to support transactional systems
 * - You want to logging and auditing of requests
 * 
 * Example: UI button actions, menu items, undo/redo systems, task queues
 */

// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class Document {
    private String name;
    private String content;
    
    public Document(String name) {
        this.name = name;
        this.content = "";
    }
    
    public void open() {
        System.out.println("Opening document: " + name);
    }
    
    public void close() {
        System.out.println("Closing document: " + name);
    }
    
    public void save() {
        System.out.println("Saving document: " + name);
    }
    
    public void write(String text) {
        content += text;
        System.out.println("Writing to document: " + text);
    }
}

// Concrete Commands
class OpenCommand implements Command {
    private Document document;
    
    public OpenCommand(Document document) {
        this.document = document;
    }
    
    @Override
    public void execute() {
        document.open();
    }
    
    @Override
    public void undo() {
        document.close();
    }
}

class SaveCommand implements Command {
    private Document document;
    
    public SaveCommand(Document document) {
        this.document = document;
    }
    
    @Override
    public void execute() {
        document.save();
    }
    
    @Override
    public void undo() {
        System.out.println("Undo save - reverting changes");
    }
}

class WriteCommand implements Command {
    private Document document;
    private String text;
    
    public WriteCommand(Document document, String text) {
        this.document = document;
        this.text = text;
    }
    
    @Override
    public void execute() {
        document.write(text);
    }
    
    @Override
    public void undo() {
        System.out.println("Undoing write: removing '" + text + "'");
    }
}

// Invoker
class TextEditor {
    private List<Command> commandHistory = new ArrayList<>();
    private int currentIndex = -1;
    
    public void executeCommand(Command command) {
        command.execute();
        currentIndex++;
        // Remove any commands after current index (for redo after undo)
        while (currentIndex < commandHistory.size() - 1) {
            commandHistory.remove(commandHistory.size() - 1);
        }
        commandHistory.add(command);
    }
    
    public void undo() {
        if (currentIndex >= 0) {
            Command command = commandHistory.get(currentIndex);
            command.undo();
            currentIndex--;
        }
    }
    
    public void redo() {
        if (currentIndex < commandHistory.size() - 1) {
            currentIndex++;
            Command command = commandHistory.get(currentIndex);
            command.execute();
        }
    }
}

// Another example: Remote Control
class Light {
    public void turnOn() {
        System.out.println("Light turned on");
    }
    
    public void turnOff() {
        System.out.println("Light turned off");
    }
}

class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn();
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
}

class RemoteControl {
    private Command[] commands = new Command[10];
    private Command lastCommand;
    
    public void setCommand(int slot, Command command) {
        commands[slot] = command;
    }
    
    public void pressButton(int slot) {
        if (commands[slot] != null) {
            commands[slot].execute();
            lastCommand = commands[slot];
        }
    }
    
    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
        }
    }
}

/**
 * Example usage:
 */
class CommandDemo {
    public static void main(String[] args) {
        // Text editor example with undo/redo
        TextEditor editor = new TextEditor();
        Document doc = new Document("test.txt");
        
        editor.executeCommand(new OpenCommand(doc));
        editor.executeCommand(new WriteCommand(doc, "Hello "));
        editor.executeCommand(new WriteCommand(doc, "World"));
        
        editor.undo();
        editor.undo();
        
        editor.redo();
        editor.executeCommand(new SaveCommand(doc));
        
        System.out.println();
        
        // Remote control example
        Light light = new Light();
        RemoteControl remote = new RemoteControl();
        
        remote.setCommand(0, new LightOnCommand(light));
        remote.setCommand(1, new LightOffCommand(light));
        
        remote.pressButton(0);
        remote.pressButton(1);
        remote.pressUndo();
        remote.pressUndo();
    }
}
