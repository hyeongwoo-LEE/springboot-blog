package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void find() throws Exception{
        //given

        Optional<User> result = userRepository.findByUsername("aa");

        if (result.isPresent()){
            User user = result.get();
            System.out.println(user.toString());
        }else if (result.isEmpty()){
            System.out.println("없다");
        }
        //when
        //then
    }

}