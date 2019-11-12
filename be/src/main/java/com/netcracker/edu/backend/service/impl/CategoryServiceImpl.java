package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.repository.CategoryRepository;
import com.netcracker.edu.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Iterable<Category> getAllSortedCategories(String sortParam) {
        return categoryRepository.findAll(Sort.by(sortParam));
    }
}
