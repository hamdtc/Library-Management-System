package com.example.librarymanagement.Service;

import com.example.librarymanagement.DataBase.CardRepository;
import com.example.librarymanagement.Model.LibraryCard;
import com.example.librarymanagement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public LibraryCard createAndReturn(User user){
        LibraryCard card = new LibraryCard();
        card.setCreatedOn(new Date());
        card.setLastUpdatedOn(new Date());
        card.setUser(user);
        cardRepository.save(card);
        return card;
    }

}
