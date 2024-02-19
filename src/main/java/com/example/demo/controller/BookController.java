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

    @GetMapping("/get-all-books-by-author-price")
    public ResponseEntity<List<Book>> getAllBooksByAuthorAndPrice(
            @RequestParam String author, @RequestParam double price
    ){
        List<Book> books = repository.findAllByAuthorAndPrice(author, price);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/get-all-books-by-price/{price}")
    public ResponseEntity<List<Book>> getAllBooksByPrice(@PathVariable double price){
        List<Book> books = repository.getBookByGreaterThan(price);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
       Optional<Book> book = repository.findById(id);
       return ResponseEntity.ok(book);
    }

    @GetMapping("/get-book-by-name/{name}")
    public ResponseEntity<Optional<Book>> getBookByName(@PathVariable String name) {
        Optional<Book> book = Optional.ofNullable(repository.findByName(name));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/get-book-by-author/{author}")
    public ResponseEntity<Optional<Book>> getBookByAuthor(@PathVariable String author) {
        Optional<Book> book = Optional.ofNullable(repository.findByAuthorName(author));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/get-book-by-author-named-native/{author}")
    public ResponseEntity<List<Book>> getBookByAuthorNamedNative(@PathVariable String author) {
        List<Book> book = repository.findByAuthorNamedNative(author);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/get-book-by-author-native/{author}")
    public ResponseEntity<List<Book>> getBookByAuthorNative(@PathVariable String author) {
        List<Book> books = repository.findByAuthorNameWithNativeQuery(author);
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
