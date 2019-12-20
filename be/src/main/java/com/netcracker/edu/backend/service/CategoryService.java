package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Category;

import java.util.Optional;

public interface CategoryService {
    Iterable<Category> getAllCategories();

    void deleteCategory(Integer id);

    Category addCategory(Category category);

    Iterable<Category> getAllSortedCategories(String sortParam, String sortFormat);

    Optional<Category> getCategoryById(Integer id);

    Boolean isCategoryExist(String categoryName);
}
