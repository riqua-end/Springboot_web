package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // login logic
    public String login(
        LoginRequest loginRequest,
        HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if (optionalUser.isPresent()){

            var userDto = optionalUser.get();

            if (userDto.getPassword().equals(pw)){
                /*// 쿠키에 해당 정보를 저장
                var cookie = new Cookie("authorization-cookie",userDto.getId());

                cookie.setDomain("localhost"); // 해당 도메인에서만 사용
                cookie.setPath("/"); // 경로 지정
                cookie.setMaxAge(-1); // 연결된 동안만 사용
                cookie.setHttpOnly(true); // 자바스크립트에서 해당 값을 읽을 수 없도록 보안 처리
//                cookie.setSecure(true); // Https 에서만 사용되도록 설정, 로컬호스트에서는 http 라서 적용안됨

                httpServletResponse.addCookie(cookie);*/

                return userDto.getId();
            }

        }else {
            throw new RuntimeException("User Not Found");
        }

        return null;
    }
}
