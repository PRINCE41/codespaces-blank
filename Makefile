.PHONY: help compile clean run-reference run-singleton run-decorator run-observer run-strategy run-facade run-all

help:
	@echo "Design Patterns Build System"
	@echo ""
	@echo "Available targets:"
	@echo "  make compile        - Compile all patterns"
	@echo "  make clean          - Remove all .class files"
	@echo "  make run-reference  - Run the pattern reference guide"
	@echo "  make run-singleton  - Run Singleton demo"
	@echo "  make run-decorator  - Run Decorator demo"
	@echo "  make run-observer   - Run Observer demo"
	@echo "  make run-strategy   - Run Strategy demo"
	@echo "  make run-facade     - Run Facade demo"
	@echo "  make run-all        - Run all demo patterns"

compile:
	@echo "Compiling all patterns..."
	@find src -name "*.java" | xargs javac -d .
	@echo "✓ Compilation successful"

clean:
	@echo "Cleaning up..."
	@find . -name "*.class" -delete
	@echo "✓ Cleaned"

run-reference: compile
	@echo "Running Design Patterns Reference..."
	@java designpatterns.DesignPatternsReference

run-singleton: compile
	@echo "Running Singleton Demo..."
	@java designpatterns.creational.singleton.SingletonDemo

run-factory: compile
	@echo "Running Factory Demo..."
	@java designpatterns.creational.factory.FactoryDemo

run-builder: compile
	@echo "Running Builder Demo..."
	@java designpatterns.creational.builder.BuilderDemo

run-prototype: compile
	@echo "Running Prototype Demo..."
	@java designpatterns.creational.prototype.PrototypeDemo

run-adapter: compile
	@echo "Running Adapter Demo..."
	@java designpatterns.structural.adapter.AdapterDemo

run-bridge: compile
	@echo "Running Bridge Demo..."
	@java designpatterns.structural.bridge.BridgeDemo

run-composite: compile
	@echo "Running Composite Demo..."
	@java designpatterns.structural.composite.CompositeDemo

run-decorator: compile
	@echo "Running Decorator Demo..."
	@java designpatterns.structural.decorator.DecoratorDemo

run-facade: compile
	@echo "Running Facade Demo..."
	@java designpatterns.structural.facade.FacadeDemo

run-flyweight: compile
	@echo "Running Flyweight Demo..."
	@java designpatterns.structural.flyweight.FlyweightDemo

run-proxy: compile
	@echo "Running Proxy Demo..."
	@java designpatterns.structural.proxy.ProxyDemo

run-observer: compile
	@echo "Running Observer Demo..."
	@java designpatterns.behavioral.observer.ObserverDemo

run-strategy: compile
	@echo "Running Strategy Demo..."
	@java designpatterns.behavioral.strategy.StrategyDemo

run-command: compile
	@echo "Running Command Demo..."
	@java designpatterns.behavioral.command.CommandDemo

run-chain: compile
	@echo "Running Chain of Responsibility Demo..."
	@java designpatterns.behavioral.chain_of_responsibility.ChainOfResponsibilityDemo

run-state: compile
	@echo "Running State Demo..."
	@java designpatterns.behavioral.state.StateDemo

run-template: compile
	@echo "Running Template Method Demo..."
	@java designpatterns.behavioral.template_method.TemplateMethodDemo

run-visitor: compile
	@echo "Running Visitor Demo..."
	@java designpatterns.behavioral.visitor.VisitorDemo

run-iterator: compile
	@echo "Running Iterator Demo..."
	@java designpatterns.behavioral.iterator.IteratorDemo

run-mediator: compile
	@echo "Running Mediator Demo..."
	@java designpatterns.behavioral.mediator.MediatorDemo

run-memento: compile
	@echo "Running Memento Demo..."
	@java designpatterns.behavioral.memento.MementoDemo

run-interpreter: compile
	@echo "Running Interpreter Demo..."
	@java designpatterns.behavioral.interpreter.InterpreterDemo

run-all: compile
	@echo "Running all pattern demos..."
	@echo ""
	@echo "=== CREATIONAL PATTERNS ==="
	@java designpatterns.creational.singleton.SingletonDemo
	@echo ""
	@java designpatterns.creational.factory.FactoryDemo
	@echo ""
	@java designpatterns.creational.builder.BuilderDemo
	@echo ""
	@java designpatterns.creational.prototype.PrototypeDemo
	@echo ""
	@echo "=== STRUCTURAL PATTERNS ==="
	@java designpatterns.structural.adapter.AdapterDemo
	@echo ""
	@java designpatterns.structural.bridge.BridgeDemo
	@echo ""
	@java designpatterns.structural.composite.CompositeDemo
	@echo ""
	@java designpatterns.structural.decorator.DecoratorDemo
	@echo ""
	@java designpatterns.structural.facade.FacadeDemo
	@echo ""
	@java designpatterns.structural.flyweight.FlyweightDemo
	@echo ""
	@java designpatterns.structural.proxy.ProxyDemo
	@echo ""
	@echo "=== BEHAVIORAL PATTERNS ==="
	@java designpatterns.behavioral.observer.ObserverDemo
	@echo ""
	@java designpatterns.behavioral.strategy.StrategyDemo
	@echo ""
	@java designpatterns.behavioral.command.CommandDemo
	@echo ""
	@java designpatterns.behavioral.chain_of_responsibility.ChainOfResponsibilityDemo
	@echo ""
	@java designpatterns.behavioral.state.StateDemo
	@echo ""
	@java designpatterns.behavioral.template_method.TemplateMethodDemo
	@echo ""
	@java designpatterns.behavioral.visitor.VisitorDemo
	@echo ""
	@java designpatterns.behavioral.iterator.IteratorDemo
	@echo ""
	@java designpatterns.behavioral.mediator.MediatorDemo
	@echo ""
	@java designpatterns.behavioral.memento.MementoDemo
	@echo ""
	@java designpatterns.behavioral.interpreter.InterpreterDemo
