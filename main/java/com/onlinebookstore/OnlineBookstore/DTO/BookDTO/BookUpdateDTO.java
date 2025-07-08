package com.onlinebookstore.OnlineBookstore.DTO.BookDTO;

import jakarta.annotation.Nullable;
import java.time.LocalDate;
import java.util.*;

public class BookUpdateDTO {
    @Nullable
    private Integer bookId;
    @Nullable
    private String title;
    @Nullable
    private String author;
    @Nullable
    private double price;
    @Nullable
    private String isbn;
    @Nullable
    private String bookDesc;
    @Nullable
    private int stockQuantity;
    @Nullable
    private LocalDate datePublished;
    @Nullable
    private Integer categoryId;
    @Nullable
    private String imageUrl;

    public BookUpdateDTO(){}

    public BookUpdateDTO(Integer bookId, String title, String author, double price, String isbn, String bookDesc, int stockQuantity, LocalDate datePublished, Integer categoryId, String imageUrl){
        this.bookId = bookId;
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

    @Nullable
    public Integer getBookId() {
        return this.bookId;
    }
    @Nullable
    public String getTitle() {
        return this.title;
    }
    @Nullable
    public String getAuthor() {
        return this.author;
    }
    @Nullable
    public double getPrice() {
        return this.price;
    }
    @Nullable
    public String getIsbn() {
        return this.isbn;
    }
    @Nullable
    public String getBookDesc() {
        return this.bookDesc;
    }
    @Nullable
    public int getStockQuantity() {
        return this.stockQuantity;
    }
    @Nullable
    public LocalDate getDatePublished() {
        return this.datePublished;
    }
    @Nullable
    public Integer getCategoryId() {
        return this.categoryId;
    }
    @Nullable
    public String getImageUrl(){
        return this.imageUrl;
    }

    public void setBookId(@Nullable Integer bookId) {
        this.bookId = bookId;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    public void setPrice(@Nullable double price) {
        this.price = price;
    }

    public void setIsbn(@Nullable String isbn) {
        this.isbn = isbn;
    }

    public void setBookDesc(@Nullable String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public void setStockQuantity(@Nullable int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setDatePublished(@Nullable LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public void setCategoryIds(@Nullable Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
