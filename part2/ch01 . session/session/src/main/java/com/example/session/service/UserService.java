package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // UserRepository 의존성 주입
    @Autowired
    private UserRepository userRepository;

    public void login(LoginRequest loginRequest, HttpSession httpSession){

        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        // UserRepository 의 findByName 메서드를 호출해 입력된 id를 찾는다. 결과는 Optional<UserDto>로 반환
        var optionalUser = userRepository.findByName(id);

        if (optionalUser.isPresent()){ // 만약 optionalUser안에 사용자 데이터가 존재한다면 아래 코드 실행

            var userDto = optionalUser.get(); // Optional 객체에서 userDto 객체를 꺼냄

            if (userDto.getPassword().equals(pw)){ // 사용자가 입력한 비밀번호와 데이터베이스의 비밀번호가 일치하면 세션에 저장
                // 세션에 "USER" 라는 이름으로 userDto 저장
                httpSession.setAttribute("USER",userDto);

            }else { // 입력한 비밀번호와 데이터베이스의 비밀번호가 일치하지 않으면 런타임 에러
                throw new RuntimeException("Password Not Match");
            }

        }else {
            // 없는 유저
            throw new RuntimeException("User Not Found");
        }
    }
}
