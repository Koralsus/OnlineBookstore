package com.onlinebookstore.OnlineBookstore.controller;


import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookCreateDTO;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookInfoDTO;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.onlinebookstore.OnlineBookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/books/")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookInfoDTO> createBook(@Valid @RequestBody BookCreateDTO dto) {
        BookInfoDTO createdBook = bookService.createBook(dto);
        return ResponseEntity.status(201).body(createdBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookInfoDTO> updateBook(@Valid @PathVariable Integer id, @RequestBody BookUpdateDTO dto) {
        dto.setBookId(id);
        BookInfoDTO updatedBook = bookService.updateBook(dto);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookInfoDTO> getBookById(@PathVariable Integer id) {
        BookInfoDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<Page<BookInfoDTO>> searchByTitle(@RequestParam String title, Pageable pageable) {
        Page<BookInfoDTO> books = bookService.searchBooksByTitle(title, pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/author")
    public ResponseEntity<Page<BookInfoDTO>> searchByAuthor(@RequestParam String author, Pageable pageable) {
        Page<BookInfoDTO> books = bookService.searchBooksByAuthor(author, pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/author-title")
    public ResponseEntity<Page<BookInfoDTO>> searchByAuthorOrTitle(@RequestParam String query, Pageable pageable) {
        Page<BookInfoDTO> books = bookService.searchBooksByAuthorOrTitle(query, query, pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/isbn")
    public ResponseEntity<BookInfoDTO> searchByIsbn(@RequestParam String isbn) {
        return bookService.searchBooksByIsbn(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public Page<BookInfoDTO> getBooksByCategory(@PathVariable Integer categoryId, Pageable pageable){
        return bookService.searchBooksByCategory(categoryId, pageable);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<BookInfoDTO>> getAllBooks(Pageable pageable) {
        Page<BookInfoDTO> books = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/best-sellers")
    public Page<BookInfoDTO> getBestSellers(@PageableDefault(size = 5) Pageable pageable){
        Page<BookInfoDTO> bestSellers = bookService.getBestSellers(pageable);
        return bookService.getBestSellers(pageable);
    }
}
