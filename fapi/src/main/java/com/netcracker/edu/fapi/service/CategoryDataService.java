package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.CategoryViewModel;

import java.util.List;

public interface CategoryDataService {
    List<CategoryViewModel> getAll();

    List<CategoryViewModel> getAllSortedCategories(String sortParam, String sortFormat);

    void deleteCategory(Integer id);

    CategoryViewModel addCategory(CategoryViewModel category);
}
