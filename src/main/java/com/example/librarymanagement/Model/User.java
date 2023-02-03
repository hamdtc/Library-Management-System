package com.example.librarymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String mobileNumber;


    private String name;

    private int age;
    private String address;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("user")
    private LibraryCard libraryCard;
}
