package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CategoryDataServiceImpl implements CategoryDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public List<CategoryViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        CategoryViewModel[] categoryViewModelResponse = restTemplate.getForObject(backendServerURL + "/api/categories/", CategoryViewModel[].class);
        return categoryViewModelResponse == null ? Collections.emptyList() : Arrays.asList(categoryViewModelResponse);
    }

    @Override
    public void deleteCategory(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerURL + "api/categories/" + id);
    }

    @Override
    public CategoryViewModel addCategory(CategoryViewModel category) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerURL + "/api/categories", category, CategoryViewModel.class).getBody();
    }

    @Override
    public List<CategoryViewModel> getAllSortedCategories(String sortParam) {
        RestTemplate restTemplate = new RestTemplate();
        CategoryViewModel[] categoryViewModelsResponse = restTemplate.getForObject(backendServerURL + "/api/categoriesSort?sort=" + sortParam, CategoryViewModel[].class);
        return categoryViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(categoryViewModelsResponse);
    }
}
