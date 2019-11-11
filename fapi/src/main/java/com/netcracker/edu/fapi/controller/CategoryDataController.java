package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/cat")
public class CategoryDataController {

    @Autowired
    private CategoryDataService categoryDataService;

    @RequestMapping
    public ResponseEntity<List<CategoryViewModel>> getAllCategories() {
        return ResponseEntity.ok(categoryDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable String id) {
        categoryDataService.deleteCategory(Integer.valueOf(id));
    }
}
