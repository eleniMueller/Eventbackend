package ch.axa.ita.em.eventbackend.category;

import ch.axa.ita.em.eventbackend.controller.CategoryController;
import ch.axa.ita.em.eventbackend.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetCategoryByIdSuccess() {
        when(categoryService.getCategoryById(1L)).thenReturn("Feier/Apéro");
        String response = categoryController.getCategoryById(1L);
        assertEquals("Feier/Apéro", response);
    }
    @Test
    void testGetCategoryByIdNotFound() {
        when(categoryService.getCategoryById(1L)).thenReturn(null);
        String response = categoryController.getCategoryById(1L);
        assertEquals(null, response);
    }
}