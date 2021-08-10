package com.cos.blog.controller.api;


import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseDTO<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출됨" );
        //실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
        user.setRole(RoleType.USER);
        int result = userService.회원가입(user);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(),result); //자바오브젝트를 JSON으로 반환(Jackson)
    }

    @PostMapping("/api/user/login")
    public ResponseDTO<Integer> login(@RequestBody User user, HttpSession session){

        System.out.println("UserApiController: login 호출됨" );

        User principal = userService.로그인(user); //principal 접급 주체

        if(principal != null){
            session.setAttribute("principal", principal);
        }

        return new ResponseDTO<>(HttpStatus.OK.value(), 1);

    }
}
