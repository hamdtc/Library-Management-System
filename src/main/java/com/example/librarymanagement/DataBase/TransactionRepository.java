package com.example.librarymanagement.DataBase;

import com.example.librarymanagement.Model.LibraryCard;
import com.example.librarymanagement.Model.TransactionStatus;
import com.example.librarymanagement.Model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer> {


    Transactions findByLibraryCardIdAndBookIdAndTransactionStatusOrderByIdDesc(Integer cardId, Integer bookId, TransactionStatus status);
}