package main.java.learning.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot concepts demo with a simple REST endpoint.
 */
@SpringBootApplication
@RestController
public class SpringBootConceptsDemo {

    public static void main(String[] args) {
        System.out.println("=== Spring Boot Concepts ===");
        SpringApplication.run(SpringBootConceptsDemo.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot";
    }
}
