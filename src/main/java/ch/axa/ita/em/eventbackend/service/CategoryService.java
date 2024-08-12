package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Category;
import ch.axa.ita.em.eventbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final Logger logger = Logger.getLogger(CategoryService.class.getName());

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void insertDefaultCategories() {
        try {
            List<Category> defaultCategories = Arrays.asList(
                    new Category(1L, "Feier/Apéro", "Ein Anlass, bei dem gefeiert wird."),
                    new Category(2L, "Externer Anlass", "Ein Anlass, der von einer externen Organisation durchgeführt wird."),
                    new Category(3L, "Schulung", "Eine Schulung oder ein Kurs."),
                    new Category(4L, "Sport", "Ein Anlass, bei dem Sport betrieben wird."),
                    new Category(5L, "Interner Anlass", "Ein Anlass, der von der eigenen Organisation durchgeführt wird."),
                    new Category(6L, "Unterhaltung", "Ein Anlass, bei dem Unterhaltung geboten wird."),
                    new Category(7L, "Öffentliche Veranstaltung", "Ein Anlass, der öffentlich ist.")
            );
            categoryRepository.saveAll(defaultCategories);
            logger.info("Default categories inserted successfully");
        } catch (Exception e) {
            logger.warning("Error while inserting default categories: " + e.getMessage());
        }
    }

    public String getCategoryById(Long id) {
        try {
            return categoryRepository.findById(id)
                    .map(Category::getName)
                    .orElse(null);
        } catch (Exception e) {
            logger.warning("Error while fetching category: " + e.getMessage());
            return null;
        }
    }
}