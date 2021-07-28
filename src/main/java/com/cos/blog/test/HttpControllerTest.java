package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(DATA)
@RestController
public class HttpControllerTest {



    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail() ;
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청"+ m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    @PutMapping("http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    @DeleteMapping("http/delete")
    public String deleteTest(){
        return "delete 요청" ;
    }


}
