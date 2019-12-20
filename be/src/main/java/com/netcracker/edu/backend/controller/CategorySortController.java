package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoriesSort")
public class CategorySortController {
    private CategoryService categoryService;

    @Autowired
    public CategorySortController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Category> getAllSortedCategories(@RequestParam(name = "sort") String sortParam,
                                                     @RequestParam(name = "format") String sortFormat) {
        return categoryService.getAllSortedCategories(sortParam, sortFormat);
    }
}
