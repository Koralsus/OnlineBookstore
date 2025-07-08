package com.onlinebookstore.OnlineBookstore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.*;
import java.util.*;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Integer bookId;
    private String title;
    private String author;
    private double price;
    private String isbn;
    @Column(name = "book_desc")
    private String bookDesc;
    @Column(name="date_published")
    private LocalDate datePublished;

    @Column(name="stock_quantity")
    private int stockQuantity;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime creation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "sales_count")
    private Integer salesCount = 0;

    public Book(){

    }

    public Integer getBookId(){
        return this.bookId;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public double getPrice(){
        return this.price;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public String getBookDesc(){
        return this.bookDesc;
    }

    public LocalDate getDatePublished() {
        return this.datePublished;
    }

    public int getStocks(){
        return this.stockQuantity;
    }

    public LocalDateTime getCreation(){
        return this.creation;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Category getCategory() {
        return this.category;
    }

    public Integer getSalesCount(){
        return this.salesCount;
    }

    public void setBookId(Integer newId){
        this.bookId = newId;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setAuthor(String newAuthor){
        this.author = newAuthor;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setIsbn(String newIsbn){
        this.isbn = newIsbn;
    }

    public void setBookDesc(String newDesc){
        this.bookDesc = newDesc;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public void setStocks(int newStocks){
        this.stockQuantity = newStocks;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }
}
