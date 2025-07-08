package com.onlinebookstore.OnlineBookstore.DTO.BookDTO;
import java.time.LocalDate;
import java.util.*;

public class BookInfoDTO {
    private String title;
    private String author;
    private double price;
    private String isbn;
    private String bookDesc;
    private LocalDate datePublished;
    private Integer categoryId;
    private String imageUrl;
    private Integer salesCount;

    public BookInfoDTO(){}

    public BookInfoDTO(String title, String author, double price, String isbn, String bookDesc, LocalDate datePublished, Integer categoryId, String imageUrl, Integer salesCount){
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.bookDesc = bookDesc;
        this.datePublished = datePublished;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.salesCount = salesCount;
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

    public LocalDate getDatePublished(){
        return this.datePublished;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Integer getSalesCount() {
        return this.salesCount;
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

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }
}
