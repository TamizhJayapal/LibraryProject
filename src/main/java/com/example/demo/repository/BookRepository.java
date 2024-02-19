package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);

    List<Book> findAllByAuthorAndPrice(String author, double price);

//    @Query("select b from Book b where b.author = ?1")
//    Book findByAuthorName(String author);

    @Query(value="select * from book where author = ?1", nativeQuery = true)
    Book findByAuthorName(String author);

    @Query(value = "select * from book where author = :author", nativeQuery = true)
    List<Book> findByAuthorNameWithNativeQuery(String author);

    List<Book> getBookByGreaterThan(double price);

    List<Book> findByAuthorNamedNative(String author);

}
