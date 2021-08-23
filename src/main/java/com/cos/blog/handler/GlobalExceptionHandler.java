package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 예외 발생시 현재 클래스로 들어옴
@RestController
public class GlobalExceptionHandler {

    

    @ExceptionHandler(value = Exception.class) //ArgumentException 예외를 받음
    public ResponseDTO<String> handleException(Exception e) {
        return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()); //500

    }
}
