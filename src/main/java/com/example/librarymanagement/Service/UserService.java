package com.example.librarymanagement.Service;

import com.example.librarymanagement.DataBase.UserRepository;
import com.example.librarymanagement.Model.LibraryCard;
import com.example.librarymanagement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    CardService cardService;

    @Autowired
    UserRepository userRepository;

    public LibraryCard createUser(User user) {

        user=userRepository.save(user);
        //connect with card
        LibraryCard libraryCard= cardService.createAndReturn(user);
        return libraryCard;

    }

    public User getUserById(int id) {
        User user=userRepository.findById(id).get();
        return user;
    }

    public User getUserByMobile(String mobile) {
        User user=userRepository.findByMobileNumber(mobile);
        return user;
    }
}
