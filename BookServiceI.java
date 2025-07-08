package com.onlinebookstore.OnlineBookstore.service.implementations;

import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookInfoDTO;
import com.onlinebookstore.OnlineBookstore.model.Book;
import com.onlinebookstore.OnlineBookstore.model.Category;
import com.onlinebookstore.OnlineBookstore.repository.BookRepository;
import com.onlinebookstore.OnlineBookstore.repository.CategoryRepository;
import com.onlinebookstore.OnlineBookstore.service.BookService;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookCreateDTO;
import com.onlinebookstore.OnlineBookstore.DTO.BookDTO.BookUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class BookServiceI implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BookInfoDTO mapToBookInfoDTO(Book book) {
        BookInfoDTO dto = new BookInfoDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setIsbn(book.getIsbn());
        dto.setBookDesc(book.getBookDesc());
        dto.setDatePublished(book.getDatePublished());
        dto.setCategoryId(book.getCategory() != null ? book.getCategory().getCategoryId() : null);
        dto.setImageUrl(book.getImageUrl());
        return dto;
    }

    @Override
    public Page<BookInfoDTO> searchBooksByTitle(String bookTitle, Pageable pageable){
        return bookRepository.findByTitleContainingIgnoreCase(bookTitle, pageable)
                .map(this::mapToBookInfoDTO);
    }

    @Override
    public Page<BookInfoDTO> searchBooksByAuthor(String bookAuthor, Pageable pageable){
        return bookRepository.findByAuthorContainingIgnoreCase(bookAuthor, pageable)
                .map(this::mapToBookInfoDTO);
    }

    @Override
    public Optional<BookInfoDTO> searchBooksByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn).map(this::mapToBookInfoDTO);
    }

    @Override
    public Page<BookInfoDTO> searchBooksByAuthorOrTitle(String title, String author, Pageable pageable){
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(title, author, pageable)
                .map(this::mapToBookInfoDTO);
    }

    @Override
    public BookInfoDTO createBook(BookCreateDTO dto){
        if (bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
            throw new RuntimeException("This ISBN already exists.");
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setBookDesc(dto.getBookDesc());
        book.setIsbn(dto.getIsbn());
        book.setStocks(dto.getStockQuantity());
        book.setDatePublished(dto.getDatePublished());

        if (dto.getCategoryId() != null){
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            book.setCategory(category);
        }

        Book savedBook = bookRepository.save(book);
        return mapToBookInfoDTO(savedBook);
    }

    @Override
    public BookInfoDTO updateBook(BookUpdateDTO dto){
        if (dto.getBookId() == null) {
            throw new IllegalArgumentException("Book ID cannot be null for update");
        }
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setBookDesc(dto.getBookDesc());
        book.setIsbn(dto.getIsbn());
        book.setStocks(dto.getStockQuantity());
        book.setDatePublished(dto.getDatePublished());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            book.setCategory(category);
        }

        Book updatedBook = bookRepository.save(book);
        return mapToBookInfoDTO(updatedBook);
    }

    @Override
    public void deleteBook(Integer bookId){
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookInfoDTO getBookById(Integer bookId){
        return mapToBookInfoDTO(bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found")));
    }

    @Override
    public Page<BookInfoDTO> searchBooksByCategory(Integer categoryId, Pageable pageable){
        return bookRepository.findByCategory_CategoryId(categoryId, pageable)
                .map(this::mapToBookInfoDTO);
    }

    @Override
    public Page<BookInfoDTO> getAllBooks(Pageable pageable){
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(this::mapToBookInfoDTO);
    }

    @Override
    public Page<BookInfoDTO> getBestSellers(Pageable pageable){
        Page<Book> books = bookRepository.findByOrderBySalesCountDesc(pageable);
        return books.map(this::mapToBookInfoDTO);
    }
}
