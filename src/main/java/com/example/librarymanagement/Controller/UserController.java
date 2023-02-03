package com.example.librarymanagement.Controller;

import com.example.librarymanagement.Model.LibraryCard;
import com.example.librarymanagement.Model.User;
import com.example.librarymanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("")
    public ResponseEntity createUser(@RequestBody User user){
        LibraryCard libraryCard = userService.createUser(user);
        return new ResponseEntity<>(user.getName()+" Library card has been Generated with ID "+libraryCard.getId(),
                HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<User> getUserByFilter(@RequestParam(value = "id",defaultValue = "") Integer id,
                                            @RequestParam(value = "mobile",defaultValue = "") String mobile){
       User user=null;
        if(id!=null) {
            user = userService.getUserById(id);
        }else if(!mobile.isEmpty()){
           user= userService.getUserByMobile(mobile);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
