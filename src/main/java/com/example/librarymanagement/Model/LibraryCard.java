package com.example.librarymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date lastUpdatedOn;

    @OneToOne(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("libraryCard")
    private User user ;

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("libraryCard")
    private List<Book> books;


}
