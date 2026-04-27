package main.java.designpatterns.behavioral.strategy;

/**
 * STRATEGY PATTERN
 * 
 * Intent: Define a family of algorithms, encapsulate each one, and make them interchangeable.
 * Strategy lets the algorithm vary independently from clients that use it.
 * 
 * Use when:
 * - You have a family of algorithms and want to make them interchangeable
 * - You want to avoid conditional statements to select algorithms
 * - Different variants of an algorithm are needed
 * 
 * Example: Sorting algorithms, payment methods, compression formats, travel routes
 */

// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using credit card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using PayPal account: " + email);
    }
}

class CryptoCurrencyPayment implements PaymentStrategy {
    private String walletAddress;
    
    public CryptoCurrencyPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using cryptocurrency wallet: " + walletAddress);
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    private double total;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout() {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method");
            return;
        }
        paymentStrategy.pay(total);
    }
    
    public void addItem(double price) {
        total += price;
    }
}

// Another example: Sorting strategies
interface SortingStrategy {
    void sort(int[] array);
}

class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Bubble Sort");
        // Bubble sort implementation
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

class QuickSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Quick Sort");
        quickSort(array, 0, array.length - 1);
    }
    
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}

class MergeSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Merge Sort");
        mergeSort(array, 0, array.length - 1);
    }
    
    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }
    
    private void merge(int[] array, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        
        int[] left = new int[n1];
        int[] right = new int[n2];
        
        System.arraycopy(array, low, left, 0, n1);
        System.arraycopy(array, mid + 1, right, 0, n2);
        
        int i = 0, j = 0, k = low;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        
        while (i < n1) {
            array[k++] = left[i++];
        }
        
        while (j < n2) {
            array[k++] = right[j++];
        }
    }
}

class Sorter {
    private SortingStrategy strategy;
    
    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void execute(int[] array) {
        strategy.sort(array);
    }
}

/**
 * Example usage:
 */
class StrategyDemo {
    public static void main(String[] args) {
        // Payment strategy example
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(50);
        cart.addItem(30);
        cart.addItem(20);
        
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout();
        
        cart.setPaymentStrategy(new PayPalPayment("user@paypal.com"));
        cart.checkout();
        
        cart.setPaymentStrategy(new CryptoCurrencyPayment("1A1z7agoat2YLZW51Ydwsfz3c6YNt33mxe"));
        cart.checkout();
        
        System.out.println();
        
        // Sorting strategy example
        Sorter sorter = new Sorter();
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        
        sorter.setStrategy(new BubbleSort());
        sorter.execute(array);
        
        sorter.setStrategy(new QuickSort());
        sorter.execute(array);
        
        sorter.setStrategy(new MergeSort());
        sorter.execute(array);
    }
}
