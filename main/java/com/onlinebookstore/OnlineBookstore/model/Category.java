package com.onlinebookstore.OnlineBookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer categoryId;

    private String category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="book_categories",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="book_id"))
    private Set<Book> books;

    public Category(){

    }

    public Integer getCategoryId(){
        return this.categoryId;
    }

    public String getCategory(){
        return this.category;
    }

    public Set<Book> getBooks(){
        return this.books;
    }

    public void setCategoryId(Integer newId){
        this.categoryId = newId;
    }

    public void setCategory(String newCategory){
        this.category = newCategory;
    }

    public void setBooks(Set<Book> newBooks){
        this.books = newBooks;
    }
}
