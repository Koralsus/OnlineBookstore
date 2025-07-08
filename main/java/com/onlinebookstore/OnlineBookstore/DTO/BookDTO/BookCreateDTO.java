package com.onlinebookstore.OnlineBookstore.DTO.BookDTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.*;

public class BookCreateDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Positive
    private double price;
    private String isbn;
    private String bookDesc;
    @PositiveOrZero
    private int stockQuantity;
    private LocalDate datePublished;
    private Integer categoryId;
    private String imageUrl;

    public BookCreateDTO() {}

    public BookCreateDTO(String title, String author, double price, String isbn, String bookDesc, int stockQuantity, LocalDate datePublished, Integer categoryId, String imageUrl) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.bookDesc = bookDesc;
        this.stockQuantity = stockQuantity;
        this.datePublished = datePublished;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public double getPrice() {
        return this.price;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getBookDesc() {
        return this.bookDesc;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public LocalDate getDatePublished() {
        return this.datePublished;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
