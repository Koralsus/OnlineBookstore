package com.onlinebookstore.OnlineBookstore.service;

import com.onlinebookstore.OnlineBookstore.model.Book;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookCreateDTO;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookUpdateDTO;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookService {
    BookInfoDTO mapToBookInfoDTO(Book book);
    Page<BookInfoDTO> searchBooksByTitle(String bookTitle, Pageable pageable);
    Page<BookInfoDTO> searchBooksByAuthor(String bookAuthor, Pageable pageable);
    Optional<BookInfoDTO> searchBooksByIsbn(String isbn);
    Page<BookInfoDTO> searchBooksByAuthorOrTitle(String title, String author, Pageable pageable);
    BookInfoDTO createBook(BookCreateDTO dto);
    BookInfoDTO updateBook(BookUpdateDTO dto);
    void deleteBook(Integer bookId);
    BookInfoDTO getBookById(Integer id);
    Page<BookInfoDTO> searchBooksByCategory(Integer categoryId, Pageable pageable);
    Page<BookInfoDTO> getAllBooks(Pageable pageable);
    Page<BookInfoDTO> getBestSellers(Pageable pageable);
}