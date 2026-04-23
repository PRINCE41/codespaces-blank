package designpatterns.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * MEMENTO PATTERN
 * 
 * Intent: Without violating encapsulation, capture and externalize an object's internal state 
 * so that the object can be restored to this state later.
 * 
 * Use when:
 * - You need to save and restore an object's state to a previous state
 * - You want to implement undo/redo functionality
 * - You want to create checkpoints or snapshots
 * - You need audit trails of state changes
 * 
 * Example: Text editor undo/redo, transaction rollback, save points in games, version control
 */

// Memento - stores the state
class Memento {
    private String state;
    
    public Memento(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
}

// Originator - creates and restores mementos
class Document {
    private String content;
    
    public Document(String initialContent) {
        this.content = initialContent;
    }
    
    public void setContent(String content) {
        this.content = content;
        System.out.println("Document content changed to: " + content);
    }
    
    public String getContent() {
        return content;
    }
    
    // Create a memento
    public Memento createMemento() {
        return new Memento(content);
    }
    
    // Restore from memento
    public void restoreMemento(Memento memento) {
        this.content = memento.getState();
        System.out.println("Document restored to: " + content);
    }
}

// Caretaker - manages mementos
class DocumentHistory {
    private List<Memento> history = new ArrayList<>();
    private int currentIndex = -1;
    
    public void saveState(Memento memento) {
        // Remove any mementos after current index (for redo after undo)
        while (currentIndex < history.size() - 1) {
            history.remove(history.size() - 1);
        }
        history.add(memento);
        currentIndex++;
    }
    
    public Memento undo() {
        if (currentIndex > 0) {
            currentIndex--;
            return history.get(currentIndex);
        }
        return null;
    }
    
    public Memento redo() {
        if (currentIndex < history.size() - 1) {
            currentIndex++;
            return history.get(currentIndex);
        }
        return null;
    }
    
    public void displayHistory() {
        System.out.println("History:");
        for (int i = 0; i < history.size(); i++) {
            String marker = (i == currentIndex) ? " <-- Current" : "";
            System.out.println(i + ": " + history.get(i).getState() + marker);
        }
    }
}

// Another example: Game save system
class GameState {
    private int level;
    private int score;
    private int health;
    
    public GameState(int level, int score, int health) {
        this.level = level;
        this.score = score;
        this.health = health;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getHealth() {
        return health;
    }
    
    @Override
    public String toString() {
        return "Level: " + level + ", Score: " + score + ", Health: " + health;
    }
}

class GameMemento {
    private GameState state;
    
    public GameMemento(GameState state) {
        this.state = state;
    }
    
    public GameState getState() {
        return state;
    }
}

class Game {
    private GameState state;
    
    public Game(GameState initialState) {
        this.state = initialState;
    }
    
    public void play() {
        int newScore = state.getScore() + 10;
        int newHealth = Math.max(0, state.getHealth() - 5);
        this.state = new GameState(state.getLevel(), newScore, newHealth);
        System.out.println("Playing... " + state);
    }
    
    public GameMemento saveCheckpoint() {
        System.out.println("Checkpoint saved!");
        return new GameMemento(state);
    }
    
    public void loadCheckpoint(GameMemento memento) {
        this.state = memento.getState();
        System.out.println("Checkpoint loaded! " + state);
    }
    
    public GameState getState() {
        return state;
    }
}

class GameCheckpoints {
    private List<GameMemento> checkpoints = new ArrayList<>();
    
    public void save(GameMemento memento) {
        checkpoints.add(memento);
        System.out.println("Total checkpoints: " + checkpoints.size());
    }
    
    public GameMemento load(int index) {
        if (index >= 0 && index < checkpoints.size()) {
            return checkpoints.get(index);
        }
        return null;
    }
    
    public void listCheckpoints() {
        System.out.println("Available checkpoints:");
        for (int i = 0; i < checkpoints.size(); i++) {
            System.out.println(i + ": " + checkpoints.get(i).getState());
        }
    }
}

/**
 * Example usage:
 */
class MementoDemo {
    public static void main(String[] args) {
        // Document undo/redo example
        System.out.println("=== Document with Undo/Redo ===");
        Document doc = new Document("Initial content");
        DocumentHistory history = new DocumentHistory();
        
        history.saveState(doc.createMemento());
        
        doc.setContent("Version 1");
        history.saveState(doc.createMemento());
        
        doc.setContent("Version 2");
        history.saveState(doc.createMemento());
        
        doc.setContent("Version 3");
        history.saveState(doc.createMemento());
        
        history.displayHistory();
        
        System.out.println("\nUndoing...");
        Memento memento = history.undo();
        if (memento != null) {
            doc.restoreMemento(memento);
        }
        
        memento = history.undo();
        if (memento != null) {
            doc.restoreMemento(memento);
        }
        
        System.out.println("\nRedoing...");
        memento = history.redo();
        if (memento != null) {
            doc.restoreMemento(memento);
        }
        
        System.out.println();
        
        // Game checkpoint example
        System.out.println("=== Game Checkpoints ===");
        Game game = new Game(new GameState(1, 0, 100));
        GameCheckpoints checkpoints = new GameCheckpoints();
        
        System.out.println("Initial " + game.getState());
        checkpoints.save(game.saveCheckpoint());
        
        game.play();
        game.play();
        System.out.println("Current: " + game.getState());
        checkpoints.save(game.saveCheckpoint());
        
        game.play();
        game.play();
        System.out.println("Current: " + game.getState());
        
        checkpoints.listCheckpoints();
        
        System.out.println("\nLoading checkpoint 0...");
        game.loadCheckpoint(checkpoints.load(0));
    }
}
