package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 예외 발생시 현재 클래스로 들어옴
@RestController
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class) //ArgumentException 예외를 받음
    public String handleException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }


    @ExceptionHandler(value = IllegalArgumentException.class) //ArgumentException 예외를 받음
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
