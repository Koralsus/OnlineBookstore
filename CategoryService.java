package com.onlinebookstore.OnlineBookstore.service;

import com.onlinebookstore.OnlineBookstore.model.Category;
import java.util.List;


public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Integer categoryId);
    Category getCategoryById(Integer categoryId);
    List<Category> searchCategoriesByName(String key);
    List<Category> getAllCategories();
    int countBooksInCategory(Integer categoryId);
}
