package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class blogControllerTest {

    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>hello spring boot</h1>";
    }

    @GetMapping("/test")
    public String text(){
        return "git";
    }



}
