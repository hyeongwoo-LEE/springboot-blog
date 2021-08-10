package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Jpa Naming 전략
    // Select * from user where username =? and passoword = ?;
    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "Select * from user where username =?1 and passoword = ?2", nativeQuery = true)
//    User login(String username, String password);

}
