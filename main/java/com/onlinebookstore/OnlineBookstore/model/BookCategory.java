package com.onlinebookstore.OnlineBookstore.model;

import jakarta.persistence.*;
import com.onlinebookstore.OnlineBookstore.model.Book;
import com.onlinebookstore.OnlineBookstore.model.Category;

public class BookCategory {
    private Book book;
    private Category category;
}
