package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/api/categories/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable(name = "id") Integer id) {
        categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @RequestMapping(value = "api/categoryName", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategoryById(@RequestParam(name = "categoryId") Integer id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/api/categoryCheckName", method = RequestMethod.GET)
    public Boolean isCategoryExist(@RequestParam(name = "categoryName") String categoryName) {
        return this.categoryService.isCategoryExist(categoryName);
    }

    @RequestMapping(value = "/api/categoriesSort", method = RequestMethod.GET)
    public Iterable<Category> getAllSortedCategories(@RequestParam(name = "sort") String sortParam,
                                                     @RequestParam(name = "format") String sortFormat) {
        return categoryService.getAllSortedCategories(sortParam, sortFormat);
    }
}
