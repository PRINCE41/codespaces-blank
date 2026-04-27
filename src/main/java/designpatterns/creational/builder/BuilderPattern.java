package main.java.designpatterns.creational.builder;

/**
 * BUILDER PATTERN
 * 
 * Intent: Separate the construction of a complex object from its representation so that the same 
 * construction process can create different representations.
 * 
 * Use when:
 * - An object has many optional parameters
 * - Object construction requires multiple steps
 * - You want to create immutable objects
 * - You need different representations of an object
 * 
 * Example: Pizza builder, HTTP request builder, StringBuilder in Java
 */

public class BuilderPattern {
    
    /**
     * Complex object to be built
     */
    public static class Pizza {
        private String dough;
        private String sauce;
        private String cheese;
        private String topping1;
        private String topping2;
        private String topping3;
        private boolean pepperoni;
        private boolean ham;
        
        public Pizza(PizzaBuilder builder) {
            this.dough = builder.dough;
            this.sauce = builder.sauce;
            this.cheese = builder.cheese;
            this.topping1 = builder.topping1;
            this.topping2 = builder.topping2;
            this.topping3 = builder.topping3;
            this.pepperoni = builder.pepperoni;
            this.ham = builder.ham;
        }
        
        @Override
        public String toString() {
            return "Pizza{" +
                    "dough='" + dough + '\'' +
                    ", sauce='" + sauce + '\'' +
                    ", cheese='" + cheese + '\'' +
                    ", topping1='" + topping1 + '\'' +
                    ", topping2='" + topping2 + '\'' +
                    ", topping3='" + topping3 + '\'' +
                    ", pepperoni=" + pepperoni +
                    ", ham=" + ham +
                    '}';
        }
    }
    
    /**
     * Builder class
     */
    public static class PizzaBuilder {
        private String dough;
        private String sauce;
        private String cheese;
        private String topping1;
        private String topping2;
        private String topping3;
        private boolean pepperoni;
        private boolean ham;
        
        public PizzaBuilder(String dough) {
            this.dough = dough;
        }
        
        public PizzaBuilder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }
        
        public PizzaBuilder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }
        
        public PizzaBuilder topping(String topping) {
            if (this.topping1 == null) {
                this.topping1 = topping;
            } else if (this.topping2 == null) {
                this.topping2 = topping;
            } else {
                this.topping3 = topping;
            }
            return this;
        }
        
        public PizzaBuilder pepperoni(boolean pepperoni) {
            this.pepperoni = pepperoni;
            return this;
        }
        
        public PizzaBuilder ham(boolean ham) {
            this.ham = ham;
            return this;
        }
        
        public Pizza build() {
            return new Pizza(this);
        }
    }
}

/**
 * Example usage:
 */
class BuilderDemo {
    public static void main(String[] args) {
        BuilderPattern.Pizza pizza = new BuilderPattern.PizzaBuilder("Thin Crust")
                .sauce("Tomato")
                .cheese("Mozzarella")
                .topping("Mushrooms")
                .topping("Olives")
                .pepperoni(true)
                .build();
        
        System.out.println(pizza);
        
        // Different pizza
        BuilderPattern.Pizza hawaiian = new BuilderPattern.PizzaBuilder("Thick Crust")
                .sauce("BBQ")
                .cheese("Cheddar")
                .topping("Pineapple")
                .topping("Ham")
                .ham(true)
                .build();
        
        System.out.println(hawaiian);
    }
}
