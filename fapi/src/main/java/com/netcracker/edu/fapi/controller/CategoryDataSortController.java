package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catSort")
public class CategoryDataSortController {
    @Autowired
    private CategoryDataService categoryDataService;

    @RequestMapping
    public ResponseEntity<List<CategoryViewModel>> getAllSortedCategories(@RequestParam(name = "sort") String sortParam, @RequestParam(name = "format") String sortFormat) {
        return ResponseEntity.ok(categoryDataService.getAllSortedCategories(sortParam, sortFormat));
    }
}
