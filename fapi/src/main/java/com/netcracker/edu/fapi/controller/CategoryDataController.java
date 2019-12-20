package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class CategoryDataController {

    @Autowired
    private CategoryDataService categoryDataService;

    @RequestMapping(value = "/api/catAll")
    public ResponseEntity<List<CategoryViewModel>> getAllCategories() {
        return ResponseEntity.ok(categoryDataService.getAll());
    }

    @RequestMapping(value = "/api/catDel/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable String id) {
        categoryDataService.deleteCategory(Integer.valueOf(id));
    }

    @RequestMapping(value = "/api/catAdd", method = RequestMethod.POST)
    public ResponseEntity<CategoryViewModel> addCategory(@RequestBody @Valid CategoryViewModel category) {
        if (category != null) {
            CategoryViewModel newCategory = categoryDataService.addCategory(category);
            if (newCategory == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return ResponseEntity.ok(newCategory);
            }
        }
        return null;
    }
}
