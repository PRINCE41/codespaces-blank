package main.java.learning.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Spring framework concepts: dependency injection and bean configuration.
 */
@Configuration
public class SpringConceptsDemo {

    public static void main(String[] args) {
        System.out.println("=== Spring Concepts ===");
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConceptsDemo.class)) {
            GreetingService service = context.getBean(GreetingService.class);
            System.out.println(service.greet("Spring Learner"));
        }
    }

    @Bean
    GreetingService greetingService() {
        return new GreetingService();
    }
}

@Component
class GreetingService {
    String greet(String name) {
        return "Hello " + name + " from Spring DI";
    }
}
