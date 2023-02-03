package com.example.librarymanagement.Controller;


import com.example.librarymanagement.Model.Book;
import com.example.librarymanagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getBookDetails(@RequestParam(value = "name", required = false) String name){
        List<Book> books = bookService.getBookDetails(name);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
