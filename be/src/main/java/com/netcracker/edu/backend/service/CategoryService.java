package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Category;

public interface CategoryService {
    Iterable<Category> getAllCategories();
    void deleteCategory(Integer id);
    Category addCategory(Category category);
    Iterable<Category> getAllSortedCategories(String sortParam);
}
