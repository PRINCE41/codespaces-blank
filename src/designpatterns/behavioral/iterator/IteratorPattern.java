package designpatterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * ITERATOR PATTERN
 * 
 * Intent: Provide a way to access the elements of a collection sequentially without exposing 
 * its underlying representation.
 * 
 * Use when:
 * - You need to access elements of a collection without exposing its structure
 * - You want to support multiple traversals of a collection
 * - You want to provide a unified interface for traversing different collections
 * 
 * Example: Java Iterator, traversing different data structures, pagination
 */

// Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
    void remove();
}

// Collection interface
interface Collection<T> {
    Iterator<T> createIterator();
}

// Concrete Iterator
class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int position = 0;
    
    public ArrayIterator(T[] array) {
        this.array = array;
    }
    
    @Override
    public boolean hasNext() {
        return position < array.length;
    }
    
    @Override
    public T next() {
        return array[position++];
    }
    
    @Override
    public void remove() {
        // For arrays, we can't remove, so this is a no-op
    }
}

// Concrete Collection
class NumberCollection implements Collection<Integer> {
    private Integer[] numbers;
    
    public NumberCollection(Integer[] numbers) {
        this.numbers = numbers;
    }
    
    @Override
    public Iterator<Integer> createIterator() {
        return new ArrayIterator<>(numbers);
    }
}

// Another example: List Iterator
class ListIterator<T> implements Iterator<T> {
    private List<T> list;
    private int index = 0;
    
    public ListIterator(List<T> list) {
        this.list = list;
    }
    
    @Override
    public boolean hasNext() {
        return index < list.size();
    }
    
    @Override
    public T next() {
        return list.get(index++);
    }
    
    @Override
    public void remove() {
        if (index > 0) {
            list.remove(index - 1);
            index--;
        }
    }
}

class GenericCollection<T> implements Collection<T> {
    private List<T> items = new ArrayList<>();
    
    public void add(T item) {
        items.add(item);
    }
    
    @Override
    public Iterator<T> createIterator() {
        return new ListIterator<>(items);
    }
}

// Another example: Reverse Iterator
class ReverseIterator<T> implements Iterator<T> {
    private List<T> list;
    private int index;
    
    public ReverseIterator(List<T> list) {
        this.list = list;
        this.index = list.size();
    }
    
    @Override
    public boolean hasNext() {
        return index > 0;
    }
    
    @Override
    public T next() {
        return list.get(--index);
    }
    
    @Override
    public void remove() {
        list.remove(index);
    }
}

// Menu item example
class MenuItem {
    private String name;
    private double price;
    
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

// Menu
class Menu {
    private List<MenuItem> items = new ArrayList<>();
    
    public void addItem(MenuItem item) {
        items.add(item);
    }
    
    public Iterator<MenuItem> createIterator() {
        return new ListIterator<>(items);
    }
    
    public Iterator<MenuItem> createReverseIterator() {
        return new ReverseIterator<>(items);
    }
}

/**
 * Example usage:
 */
class IteratorDemo {
    public static void main(String[] args) {
        // Array collection example
        System.out.println("=== Array Iterator ===");
        Integer[] numbers = {1, 2, 3, 4, 5};
        Collection<Integer> numberCollection = new NumberCollection(numbers);
        Iterator<Integer> iterator = numberCollection.createIterator();
        
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        // Generic collection example
        System.out.println("\n=== Generic Collection Iterator ===");
        GenericCollection<String> strings = new GenericCollection<>();
        strings.add("Apple");
        strings.add("Banana");
        strings.add("Cherry");
        strings.add("Date");
        
        Iterator<String> stringIterator = strings.createIterator();
        while (stringIterator.hasNext()) {
            System.out.println(stringIterator.next());
        }
        
        // Menu example with forward and reverse
        System.out.println("\n=== Menu Forward ===");
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Burger", 8.99));
        menu.addItem(new MenuItem("Pizza", 12.99));
        menu.addItem(new MenuItem("Pasta", 10.99));
        menu.addItem(new MenuItem("Salad", 7.99));
        
        Iterator<MenuItem> menuIterator = menu.createIterator();
        while (menuIterator.hasNext()) {
            System.out.println(menuIterator.next());
        }
        
        System.out.println("\n=== Menu Reverse ===");
        Iterator<MenuItem> reverseIterator = menu.createReverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }
}
