package designpatterns.behavioral.interpreter;

/**
 * INTERPRETER PATTERN
 * 
 * Intent: Given a language, define a representation for its grammar as well as an interpreter 
 * to interpret sentences in the language.
 * 
 * Use when:
 * - You have a simple language to interpret
 * - You want to create a domain-specific language (DSL)
 * - You need to build expression evaluators
 * - Performance is not critical
 * 
 * Example: SQL parsers, mathematical expression evaluators, rule engines, query builders
 */

// Abstract Expression
abstract class Expression {
    abstract double interpret();
}

// Terminal Expressions
class Number extends Expression {
    private double value;
    
    public Number(double value) {
        this.value = value;
    }
    
    @Override
    double interpret() {
        return value;
    }
}

// Non-terminal Expressions
class Add extends Expression {
    private Expression left;
    private Expression right;
    
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    double interpret() {
        return left.interpret() + right.interpret();
    }
}

class Subtract extends Expression {
    private Expression left;
    private Expression right;
    
    public Subtract(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    double interpret() {
        return left.interpret() - right.interpret();
    }
}

class Multiply extends Expression {
    private Expression left;
    private Expression right;
    
    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    double interpret() {
        return left.interpret() * right.interpret();
    }
}

class Divide extends Expression {
    private Expression left;
    private Expression right;
    
    public Divide(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    double interpret() {
        double rightValue = right.interpret();
        if (rightValue == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        return left.interpret() / rightValue;
    }
}

// Another example: Boolean expressions
interface BooleanExpression {
    boolean interpret();
}

class And implements BooleanExpression {
    private BooleanExpression left;
    private BooleanExpression right;
    
    public And(BooleanExpression left, BooleanExpression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean interpret() {
        return left.interpret() && right.interpret();
    }
}

class Or implements BooleanExpression {
    private BooleanExpression left;
    private BooleanExpression right;
    
    public Or(BooleanExpression left, BooleanExpression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean interpret() {
        return left.interpret() || right.interpret();
    }
}

class Not implements BooleanExpression {
    private BooleanExpression expression;
    
    public Not(BooleanExpression expression) {
        this.expression = expression;
    }
    
    @Override
    public boolean interpret() {
        return !expression.interpret();
    }
}

class Constant implements BooleanExpression {
    private boolean value;
    
    public Constant(boolean value) {
        this.value = value;
    }
    
    @Override
    public boolean interpret() {
        return value;
    }
}

// Another example: Role-based access control
class Context {
    private String userRole;
    private int userLevel;
    
    public Context(String userRole, int userLevel) {
        this.userRole = userRole;
        this.userLevel = userLevel;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public int getUserLevel() {
        return userLevel;
    }
}

interface Rule {
    boolean evaluate(Context context);
}

class RoleRule implements Rule {
    private String requiredRole;
    
    public RoleRule(String requiredRole) {
        this.requiredRole = requiredRole;
    }
    
    @Override
    public boolean evaluate(Context context) {
        return context.getUserRole().equals(requiredRole);
    }
}

class LevelRule implements Rule {
    private int minimumLevel;
    
    public LevelRule(int minimumLevel) {
        this.minimumLevel = minimumLevel;
    }
    
    @Override
    public boolean evaluate(Context context) {
        return context.getUserLevel() >= minimumLevel;
    }
}

class AndRule implements Rule {
    private Rule left;
    private Rule right;
    
    public AndRule(Rule left, Rule right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean evaluate(Context context) {
        return left.evaluate(context) && right.evaluate(context);
    }
}

class OrRule implements Rule {
    private Rule left;
    private Rule right;
    
    public OrRule(Rule left, Rule right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean evaluate(Context context) {
        return left.evaluate(context) || right.evaluate(context);
    }
}

/**
 * Example usage:
 */
class InterpreterDemo {
    public static void main(String[] args) {
        // Mathematical expression example
        // (5 + 3) * 2 = 16
        System.out.println("=== Mathematical Expression Interpreter ===");
        Expression expression = new Multiply(
                new Add(new Number(5), new Number(3)),
                new Number(2)
        );
        System.out.println("(5 + 3) * 2 = " + expression.interpret());
        
        // 20 / (4 - 2) = 10
        expression = new Divide(
                new Number(20),
                new Subtract(new Number(4), new Number(2))
        );
        System.out.println("20 / (4 - 2) = " + expression.interpret());
        
        System.out.println();
        
        // Boolean expression example
        System.out.println("=== Boolean Expression Interpreter ===");
        BooleanExpression boolExpr = new And(
                new Constant(true),
                new Or(new Constant(false), new Constant(true))
        );
        System.out.println("true AND (false OR true) = " + boolExpr.interpret());
        
        boolExpr = new Not(
                new And(new Constant(true), new Constant(false))
        );
        System.out.println("NOT (true AND false) = " + boolExpr.interpret());
        
        System.out.println();
        
        // Role-based access control example
        System.out.println("=== Role-Based Access Control ===");
        Context admin = new Context("ADMIN", 5);
        Context user = new Context("USER", 2);
        
        Rule adminRule = new RoleRule("ADMIN");
        Rule levelRule = new LevelRule(3);
        Rule combinedRule = new AndRule(adminRule, levelRule);
        
        System.out.println("Admin can access (ADMIN role): " + adminRule.evaluate(admin));
        System.out.println("User can access (ADMIN role): " + adminRule.evaluate(user));
        System.out.println("Admin has level >= 3: " + levelRule.evaluate(admin));
        System.out.println("User has level >= 3: " + levelRule.evaluate(user));
        System.out.println("Admin has ADMIN role AND level >= 3: " + combinedRule.evaluate(admin));
    }
}
