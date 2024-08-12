package ch.axa.ita.em.eventbackend;

import ch.axa.ita.em.eventbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventBackendApplication implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(EventBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            categoryService.insertDefaultCategories();
        } catch (Exception e) {
            throw new RuntimeException("Error while inserting default categories: " + e.getMessage());
        }
    }
}