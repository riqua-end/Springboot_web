package com.example.session.controller;

import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @GetMapping("/me")
    public UserDto me(
            HttpSession httpSession
    ){
        var userObject = httpSession.getAttribute("USER"); // 세션에 저장된 정보 불러오기

        if(userObject != null){ // 세션이 만료될수도 있어서 null 체크
            return (UserDto) userObject;
        }else {
            return null;
        }
    }
}
