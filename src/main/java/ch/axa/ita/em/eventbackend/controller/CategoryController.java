package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("{id}")
    public String getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

}
