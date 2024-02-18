package com.example.rest.controller;

import com.example.rest.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//SpringBoot Web에서 응답 만드는 방법
@RequestMapping("/api/v1")
@RestController
@Slf4j
public class ResponseApiController {

    //http://localhost:8181/api/v1
    @GetMapping("")
    public UserRequest user() {

        var user = new UserRequest();

        user.setUserAge(20);
        user.setUserName("홍길동");
        user.setEmail("hong@gmail.com");

        log.info("user: {}" , user);

        return user;
    }

    //status 코드 값 변경
    //ResponseEntity 는 예외처리 할 때 주로 사용
//    @GetMapping("")
////    @RequestMapping(path = "", method = RequestMethod.GET)
//    public ResponseEntity<UserRequest> user() {
//
//        var user = new UserRequest();
//
//        user.setUserAge(20);
//        user.setUserName("홍길동");
//        user.setEmail("hong@gmail.com");
//
//        log.info("user: {}" , user);
//
//        var response = ResponseEntity
//                .status(HttpStatus.BAD_REQUEST) //response 값 변경
//                .header("x-custom","hi") //header값 변경
//                .body(user);
//
//        return response;
//    }
}
