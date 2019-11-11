package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.CategoryViewModel;

import java.util.List;

public interface CategoryDataService {
    List<CategoryViewModel> getAll();
    void deleteCategory(Integer id);
}
