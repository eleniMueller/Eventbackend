package ch.axa.ita.em.eventbackend.category;

import ch.axa.ita.em.eventbackend.model.Category;
import ch.axa.ita.em.eventbackend.repository.CategoryRepository;
import ch.axa.ita.em.eventbackend.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertDefaultCategoriesSuccess() {
        doAnswer(invocation -> null).when(categoryRepository).saveAll(anyList());
        assertDoesNotThrow(() -> categoryService.insertDefaultCategories());
        verify(categoryRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testInsertDefaultCategoriesThrowsException() {
        doThrow(new RuntimeException("DB error")).when(categoryRepository).saveAll(anyList());
        assertDoesNotThrow(() -> categoryService.insertDefaultCategories());
        verify(categoryRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testGetCategoryByIdSuccess() {
        Category category = new Category(1L, "Feier/Apéro", "Ein Anlass, bei dem gefeiert wird.");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        String categoryName = categoryService.getCategoryById(1L);
        assertEquals("Feier/Apéro", categoryName);
    }

    @Test
    void testGetCategoryByIdNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        String categoryName = categoryService.getCategoryById(1L);
        assertNull(categoryName);
    }

    @Test
    void testGetCategoryByIdThrowsException() {
        when(categoryRepository.findById(1L)).thenThrow(new RuntimeException("DB error"));
        String categoryName = categoryService.getCategoryById(1L);
        assertNull(categoryName);
    }
}