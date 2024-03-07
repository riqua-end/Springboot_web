package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "authorization-cookie" , required = false)
            String authorizationCookie
    ) {
        log.info("authorizationCookie : {} ",authorizationCookie);
        // {"id":"3791b985-9de6-4570-8f96-8e45c569fe17","name":"유관순","password":"1234"}
        var optionalUserDto = userRepository.findById(authorizationCookie);

        return optionalUserDto.get();

        /*
        var cookies = httpServletRequest.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {
                log.info("key : {}, value : {}", cookie.getName(), cookie.getValue());
            }
        }
        */
    }

    @GetMapping("/me2")
    public UserDto me2(
        @RequestHeader(name = "authorization", required = false)
        String authorizationHeader
    ){
        log.info("authorizationHeader : {}",authorizationHeader);
        var optionalUserDto = userRepository.findById(authorizationHeader);
        return optionalUserDto.get();
    }

}
