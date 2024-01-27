package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")  //  http://localhost:8080/api/get-all-books
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = repository.findAll();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/save-book")
    public void saveBook(@RequestBody Book book){
        repository.save(book);
    }

    @DeleteMapping("/delete-book")
    public void deleteBook(@RequestParam long id){
        repository.deleteById(id);
    }

    @PutMapping("/update-book")
    public void updateBook(@RequestParam long id, @RequestBody Book newBook){
        Optional<Book> book = repository.findById(id);
        if(book.isPresent()){
            newBook.setId(id);
            repository.save(newBook);
        }
    }
}
