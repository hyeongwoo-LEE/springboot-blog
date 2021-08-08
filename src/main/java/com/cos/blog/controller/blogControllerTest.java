package com.cos.blog.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/param")
    public String h(Pageable pageable){
        System.out.println(pageable.getSort());
        System.out.println(pageable);
        System.out.println(pageable.getSortOr(pageable.getSort()));
        return "";
    }

    @PostMapping("/body")
    public String f(@RequestBody List<String> sort){
        System.out.println(sort);
        return "";
    }

}
