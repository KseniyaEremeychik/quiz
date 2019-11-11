package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable(name = "id") Integer id) {
        categoryService.deleteCategory(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
