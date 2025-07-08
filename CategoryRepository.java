package com.onlinebookstore.OnlineBookstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookstore.OnlineBookstore.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    List<Category> findByCategoryContainingIgnoreCase(String key);
}
