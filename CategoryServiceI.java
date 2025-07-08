package com.onlinebookstore.OnlineBookstore.service.implementations;

import com.onlinebookstore.OnlineBookstore.model.Category;
import com.onlinebookstore.OnlineBookstore.repository.CategoryRepository;
import com.onlinebookstore.OnlineBookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceI implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category){
        if (category.getCategory() == null || category.getCategory().trim().isEmpty()) {
            throw new RuntimeException("Category name cannot be empty");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category){
        if (category.getCategory() == null || category.getCategory().trim().isEmpty()) {
            throw new RuntimeException("Category name cannot be empty");
        }
        Category currentCategory = categoryRepository.findById(category.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        currentCategory.setCategory(category.getCategory());
        return categoryRepository.save(currentCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId){
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategoriesByName(String category){
        return categoryRepository.findByCategoryContainingIgnoreCase(category);
    }

    @Override
    public Category getCategoryById(Integer categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found"));
    }

    @Override
    public int countBooksInCategory(Integer categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()
                    -> new RuntimeException("Category not found"));

        return category.getBooks() != null ? category.getBooks().size() : 0;
    }
}
