package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "book")
@NamedQuery(name="Book.getBookByGreaterThan", query = "select b from Book b where b.price > :price")
@NamedNativeQuery(name="Book.findByAuthorNamedNative",  query = "select * from book where author = :author", resultClass = Book.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String genre;
    private String author;
    private double price;

    public Book() {
    }

    public Book(long id, String name, String genre, String author, double price) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
