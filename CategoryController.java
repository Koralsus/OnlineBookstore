package com.onlinebookstore.OnlineBookstore.controller;

import com.onlinebookstore.OnlineBookstore.model.Category;
import com.onlinebookstore.OnlineBookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category created = categoryService.createCategory(category);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category updated = categoryService.updateCategory(category);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId){
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(@RequestParam("name") String category){
        List<Category> categories = categoryService.searchCategoriesByName(category);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}/count-books")
    public ResponseEntity<Integer> countBooksInCategory(@PathVariable Integer categoryId){
        int count = categoryService.countBooksInCategory(categoryId);
        return ResponseEntity.ok(count);
    }
}
