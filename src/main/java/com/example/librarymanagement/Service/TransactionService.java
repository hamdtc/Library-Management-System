package com.example.librarymanagement.Service;


import com.example.librarymanagement.DataBase.BookRepository;
import com.example.librarymanagement.DataBase.CardRepository;
import com.example.librarymanagement.DataBase.TransactionRepository;
import com.example.librarymanagement.Model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${books.max_allowed}")
    int max_allowed_books;

    @Value("${books.max_allowed_days}")
    int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {

        //check whether bookId and cardId already exist
        log.info("cardId{},bookId{}",cardId,bookId);
        LibraryCard card = cardRepository.findById(cardId).get();
        Book book = bookRepository.findByIdAndAvailability(bookId,AvailabilityStatus.AVAILABLE.name());


        //1.if the book is not present
        if (book==null){
            throw new Exception("Book is either unavailable or not present");
        }

        //2. number of books issued against the card is strictly less than max_allowed_books
        if (card.getBooks().size() >= max_allowed_books){
            throw new Exception("Book limit has reached for this card");
    }
        //book is present and available
        Transactions transaction = new Transactions();
        transaction.setBook(book);
        transaction.setLibraryCard(card);

        book.setLibraryCard(card);
        book.setAvailabilityStatus(AvailabilityStatus.ISSUED);


        //adding to book
        List<Book> list = card.getBooks();
        list.add(book);
        card.setBooks(list);
        cardRepository.save(card);

        bookRepository.save(book);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }



    public void returnBook(Integer cardId, Integer bookId,Integer fine) throws Exception{

        Transactions transaction = transactionRepository.findByLibraryCardIdAndBookIdAndTransactionStatusOrderByIdDesc(cardId,bookId,TransactionStatus.SUCCESSFUL);

        Date date=new Date();
        long dif=date.getTime()-transaction.getTransactionDate().getTime();
        int amount=0;
        if(TimeUnit.DAYS.convert(dif,TimeUnit.MILLISECONDS)>getMax_allowed_days){
            amount+=(TimeUnit.DAYS.convert(dif,TimeUnit.MILLISECONDS)-getMax_allowed_days)*fine_per_day;
        }
        log.info("amount{},fine{}",amount,fine);
        if(amount>0 && fine<amount){

            throw new Exception( "please complete previous fine amount: "+ amount);
        }

       Book book= bookRepository.findById(bookId).get();
              book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
              book.setLibraryCard(null);
              bookRepository.save(book);


        //transaction update to transaction
        transaction.setTransactionStatus(TransactionStatus.RETURNED);
        transaction.setFineAmount(amount);
        transactionRepository.save(transaction);


        //Removing from book
        LibraryCard card=cardRepository.findById(cardId).get();
        List<Book> list = card.getBooks();
        list.remove(book);
        int n= list.size();
        log.info("n{}",n);
        card.setBooks(list);
        cardRepository.save(card);


    }
}
