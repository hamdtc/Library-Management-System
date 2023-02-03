package com.example.librarymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String author;
    private int price;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private AvailabilityStatus availabilityStatus;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private LibraryCard libraryCard;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<Transactions> transactions;



}
