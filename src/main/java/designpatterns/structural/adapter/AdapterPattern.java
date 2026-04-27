package main.java.designpatterns.structural.adapter;

/**
 * ADAPTER PATTERN (Wrapper)
 * 
 * Intent: Convert the interface of a class into another interface clients expect. Adapter lets 
 * classes work together that couldn't otherwise because of incompatible interfaces.
 * 
 * Use when:
 * - You have an existing class that doesn't match the interface you need
 * - You want to create reusable classes that cooperate with unrelated classes
 * - You need to use third-party libraries with incompatible interfaces
 * 
 * Example: Database adapters, payment gateways, media players
 */

// Target interface (What client expects)
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee (Existing legacy code)
class LegacyPaymentSystem {
    public void makePayment(double amountInCents) {
        System.out.println("Processing payment of " + (amountInCents / 100.0) + " via legacy system");
    }
}

// Adapter - Class Adapter (using inheritance)
class PaymentAdapter extends LegacyPaymentSystem implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        // Convert from dollars to cents and call legacy method
        makePayment(amount * 100);
    }
}

// Alternative: Object Adapter (using composition)
class PaymentAdapterComposition implements PaymentProcessor {
    private LegacyPaymentSystem legacySystem;
    
    public PaymentAdapterComposition(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }
    
    @Override
    public void processPayment(double amount) {
        legacySystem.makePayment(amount * 100);
    }
}

// Another example: Adapter for different media players
interface MediaPlayer {
    void play(String filename);
}

interface AdvancedMediaPlayer {
    void playVlc(String filename);
    void playMp4(String filename);
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String filename) {
        System.out.println("Playing VLC: " + filename);
    }
    
    @Override
    public void playMp4(String filename) {
        // VLC doesn't support MP4 in this example
    }
}

class MediaPlayerAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;
    
    public MediaPlayerAdapter(AdvancedMediaPlayer advancedMediaPlayer) {
        this.advancedMediaPlayer = advancedMediaPlayer;
    }
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".vlc")) {
            advancedMediaPlayer.playVlc(filename);
        } else if (filename.endsWith(".mp4")) {
            advancedMediaPlayer.playMp4(filename);
        }
    }
}

/**
 * Example usage:
 */
class AdapterDemo {
    public static void main(String[] args) {
        // Payment adapter example
        PaymentProcessor processor = new PaymentAdapter();
        processor.processPayment(19.99);
        
        // Media player adapter example
        AdvancedMediaPlayer vlcPlayer = new VlcPlayer();
        MediaPlayer adapter = new MediaPlayerAdapter(vlcPlayer);
        adapter.play("video.vlc");
    }
}
