package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(String username);
}



//Jpa Naming 전략
// Select * from user where username =? and passoword = ?;
//User findByUsernameAndPassword(String username, String password);

//    @Query(value = "Select * from user where username =?1 and passoword = ?2", nativeQuery = true)
//    User login(String username, String password);