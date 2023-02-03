package com.example.librarymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name="library_card_id")
    @JsonIgnoreProperties("books")
    private LibraryCard libraryCard;
    @Column(name="library_card_id", updatable = false, insertable = false, nullable = false)
    private int libraryCardId;

    @ManyToOne
    @JoinColumn(name="book_id")
    @JsonIgnoreProperties("transactions")
    private Book book;
    @Column(name="book_id" ,updatable = false, insertable = false, nullable = false)
    private int bookId;

    private int fineAmount;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;





}
