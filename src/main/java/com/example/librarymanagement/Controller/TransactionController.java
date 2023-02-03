package com.example.librarymanagement.Controller;


import com.example.librarymanagement.Model.Transactions;
import com.example.librarymanagement.Request.BookIssue;
import com.example.librarymanagement.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/book/issue")
    public ResponseEntity<String> issueBook(@RequestBody BookIssue bookIssue) {
        try {
            String str = transactionService.issueBook(bookIssue.getCardId(), bookIssue.getBookId());
            return new ResponseEntity<>( "Book has been issued", HttpStatus.ACCEPTED);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/book/return")
    public ResponseEntity<String> returnBook(@RequestBody BookIssue bookIssue) {
        try {
            transactionService.returnBook(bookIssue.getCardId(), bookIssue.getBookId(), bookIssue.getFine());
            return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
