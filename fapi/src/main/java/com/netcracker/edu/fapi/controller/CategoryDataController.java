package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    //todo check if category exists
    @RequestMapping(value = "/api/catAdd", method = RequestMethod.POST)
    public ResponseEntity<CategoryViewModel> addCategory(@RequestBody CategoryViewModel category) {
        if(category != null) {
            return ResponseEntity.ok(categoryDataService.addCategory(category));
        }
        return null;
    }
}
