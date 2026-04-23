# Design Patterns Implementation

A comprehensive implementation of all 23 Gang of Four (GoF) design patterns in Java with educational examples for each pattern.

## Project Structure

```
src/designpatterns/
├── creational/           # Object creation patterns
│   ├── singleton/        # Ensure only one instance
│   ├── factory/          # Create without specifying classes  
│   ├── builder/          # Complex object construction
│   └── prototype/        # Clone objects
├── structural/           # Object composition patterns
│   ├── adapter/          # Interface conversion
│   ├── bridge/           # Decouple abstraction from implementation
│   ├── composite/        # Tree structures
│   ├── decorator/        # Add features dynamically
│   ├── facade/           # Unified subsystem interface
│   ├── flyweight/        # Share fine-grained objects
│   └── proxy/            # Control access
└── behavioral/           # Object interaction patterns
    ├── observer/         # Notify about state changes
    ├── strategy/         # Interchangeable algorithms
    ├── command/          # Encapsulate requests
    ├── chain_of_responsibility/  # Handler chain
    ├── state/            # Behavior changes with state
    ├── template_method/  # Algorithm skeleton
    ├── visitor/          # Operations on structures
    ├── iterator/         # Sequential access
    ├── mediator/         # Encapsulate interaction
    ├── memento/          # Capture/restore state
    └── interpreter/      # Define language grammar
```

## Patterns Overview

### Creational Patterns (5)

| Pattern | Purpose | Example Use Case |
|---------|---------|------------------|
| **Singleton** | One instance, global access | Logger, configuration manager |
| **Factory Method** | Create without specifying classes | Document types, vehicle types |
| **Abstract Factory** | Create related object families | UI themes (light/dark) |
| **Builder** | Complex object construction | Pizza, HTTP requests |
| **Prototype** | Clone objects | Document copying, game objects |

### Structural Patterns (7)

| Pattern | Purpose | Example Use Case |
|---------|---------|------------------|
| **Adapter** | Convert incompatible interfaces | Payment gateways, media players |
| **Bridge** | Decouple abstraction/implementation | Device rendering, remote controls |
| **Composite** | Tree structures | File systems, GUI components |
| **Decorator** | Add features dynamically | Coffee ordering, text formatting |
| **Facade** | Unified subsystem interface | Database transactions, home automation |
| **Flyweight** | Share fine-grained objects | Game particles, font rendering |
| **Proxy** | Control access to another object | Lazy loading, access control |

### Behavioral Patterns (11)

| Pattern | Purpose | Example Use Case |
|---------|---------|------------------|
| **Observer** | Notify about state changes | Event systems, MVC architecture |
| **Strategy** | Interchangeable algorithms | Payment methods, sorting algorithms |
| **Command** | Encapsulate requests as objects | Undo/redo, button actions, queues |
| **Chain of Responsibility** | Pass request along handler chain | Logging levels, approval workflows |
| **State** | Behavior changes with state | Traffic lights, order processing |
| **Template Method** | Algorithm skeleton in base class | Data processing, document rendering |
| **Visitor** | Operations on object structures | File export, report generation |
| **Iterator** | Sequential access without exposure | Collection traversal, pagination |
| **Mediator** | Encapsulate object interaction | Chat rooms, air traffic control |
| **Memento** | Capture and restore state | Undo/redo, save points, checkpoints |
| **Interpreter** | Define and interpret grammar | SQL parsers, expression evaluators |

## Quick Start

### Compile All Patterns
```bash
javac -d . src/designpatterns/**/*.java
```

### Run the Reference
```bash
java designpatterns.DesignPatternsReference
```

### Run Individual Pattern Examples

**Creational:**
```bash
java designpatterns.creational.singleton.SingletonDemo
java designpatterns.creational.factory.FactoryDemo
java designpatterns.creational.factory.AbstractFactoryDemo
java designpatterns.creational.builder.BuilderDemo
java designpatterns.creational.prototype.PrototypeDemo
```

**Structural:**
```bash
java designpatterns.structural.adapter.AdapterDemo
java designpatterns.structural.bridge.BridgeDemo
java designpatterns.structural.composite.CompositeDemo
java designpatterns.structural.decorator.DecoratorDemo
java designpatterns.structural.facade.FacadeDemo
java designpatterns.structural.flyweight.FlyweightDemo
java designpatterns.structural.proxy.ProxyDemo
```

**Behavioral:**
```bash
java designpatterns.behavioral.observer.ObserverDemo
java designpatterns.behavioral.strategy.StrategyDemo
java designpatterns.behavioral.command.CommandDemo
java designpatterns.behavioral.chain_of_responsibility.ChainOfResponsibilityDemo
java designpatterns.behavioral.state.StateDemo
java designpatterns.behavioral.template_method.TemplateMethodDemo
java designpatterns.behavioral.visitor.VisitorDemo
java designpatterns.behavioral.iterator.IteratorDemo
java designpatterns.behavioral.mediator.MediatorDemo
java designpatterns.behavioral.memento.MementoDemo
java designpatterns.behavioral.interpreter.InterpreterDemo
```

## Pattern Selection Guide

**Use when object creation is complex:**
- Need exactly one instance → **Singleton**
- Creating families of related objects → **Abstract Factory**
- Constructing complex objects with many options → **Builder**
- Many optional parameters → **Builder**

**Use when composing objects into structures:**
- Working with incompatible interfaces → **Adapter**
- Creating tree structures → **Composite**
- Adding features without subclassing → **Decorator**
- Simplifying complex subsystems → **Facade**
- Many similar objects affecting memory → **Flyweight**

**Use for object communication/behavior:**
- Multiple objects react to one event → **Observer**
- Different algorithm implementations → **Strategy**
- Need undo/redo functionality → **Command** or **Memento**
- Multiple handlers in sequence → **Chain of Responsibility**
- Behavior depends on state → **State**
- Similar algorithms with different steps → **Template Method**
- Operations on complex structures → **Visitor**

## Key Concepts

### When NOT to use patterns
- Don't force patterns - use only where they solve problems
- Don't over-engineer simple code
- Consider the complexity trade-offs
- Performance matters - some patterns have overhead

### Design Pattern Principles
1. **Single Responsibility** - Each class has one reason to change
2. **Open/Closed** - Open for extension, closed for modification
3. **Liskov Substitution** - Subtypes must be substitutable
4. **Interface Segregation** - Clients shouldn't depend on unused methods
5. **Dependency Inversion** - Depend on abstractions, not concretions

## Documentation Structure

Each pattern file includes:
- **Intent** - What the pattern does
- **Use When** - Appropriate scenarios
- **Example Use Cases** - Real-world applications
- **Implementation** - Working code
- **Demo** - Usage examples

## Learning Path

1. **Start with Creational**: Understand object creation
2. **Then Structural**: Learn object composition
3. **Finally Behavioral**: Understand object interaction

**Alternative by difficulty:**
1. Easy: Singleton, Factory, Observer
2. Medium: Builder, Decorator, Strategy
3. Advanced: Visitor, Mediator, Interpreter

## Features

✅ All 23 GoF patterns implemented  
✅ Multiple examples per pattern  
✅ Clear, well-commented code  
✅ Practical use cases  
✅ Easy-to-run demos  
✅ Comprehensive documentation  
✅ No external dependencies  

## Code Quality

- Clean, readable code
- Consistent naming conventions
- Proper encapsulation
- Practical examples
- Educational comments

## References

- Gang of Four: "Design Patterns: Elements of Reusable Object-Oriented Software"
- Head First Design Patterns
- Refactoring Guru Design Patterns

## License

Educational reference material for learning design patterns.

## Contributing

Suggestions for improvements welcome!
