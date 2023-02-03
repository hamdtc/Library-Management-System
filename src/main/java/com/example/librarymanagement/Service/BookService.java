package com.example.librarymanagement.Service;

import com.example.librarymanagement.DataBase.BookRepository;
import com.example.librarymanagement.Model.AvailabilityStatus;
import com.example.librarymanagement.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository;

    public void addBook(Book book) {
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        bookRepository.save(book);
    }

    public List<Book> getBookDetails(String name) {
        return bookRepository.findByTitle(name);
    }
}
