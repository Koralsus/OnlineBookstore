package com.onlinebookstore.OnlineBookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookstore.OnlineBookstore.model.Book;

import java.util.Optional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable);
    Page<Book> findByCategory_CategoryId(Integer categoryId, Pageable pageable);
    Optional<Book> findByIsbn(String isbn);
    Page<Book> findByOrderBySalesCountDesc(Pageable pageable);
}
