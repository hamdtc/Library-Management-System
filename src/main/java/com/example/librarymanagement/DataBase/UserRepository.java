package com.example.librarymanagement.DataBase;

import com.example.librarymanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByMobileNumber(String mobile);
}
