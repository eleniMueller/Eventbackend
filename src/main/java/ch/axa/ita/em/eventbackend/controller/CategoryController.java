package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("{id}")
    public String getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

}
