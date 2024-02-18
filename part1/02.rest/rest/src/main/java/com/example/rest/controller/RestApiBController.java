package com.example.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/b")
public class RestApiBController {

    @GetMapping("/hello")
    public void hello(){
        // * NumberFormatException은 명시적으로 지정해주지 않아서 RestApiExceptionHandler의 Exception.class 로 예외처리됨
        throw new NumberFormatException("Number Format Exception!");
    }

    // * 글로벌 예외처리 RestApiController의 (RestApiExceptionHandler)에서 처리하지 않고 명시적으로 지정 가능함
    /*@ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity numberFormatException(NumberFormatException e){
        log.error("RestApiBController",e);
        return ResponseEntity.ok().build();
    }*/

}
