package com.example.session.db;

import com.example.session.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {

    // UserDto 타입의 객체들을 담는 리스트로, 간단한 메모리 기반 사용자 데이터베이스 역할을 한다.
    private final List<UserDto> userList = new ArrayList<>();

    // 주어진 이름 (name)과 일치하는 사용자를 찾는 메서드.
    // Optional<UserDto> 타입을 반환하는데, 이는 사용자가 있을 수도 있고 없을 수도 있다는 것을 의미한다.
    public Optional<UserDto> findByName(String name) {
        return userList.stream().filter(it -> { // 이름이 일치하는 사용자만 필터링
            return it.getName().equals(name);
        }).findFirst(); // 이름이 일치하는 첫번째 사용자를 Optional로 반환
    }

    @PostConstruct // UserRepository 객체가 생성된 후에 이 메서드가 자동으로 실행된다.
    public void init() {

        userList.add(new UserDto("홍길동", "1234"));
        userList.add(new UserDto("유관순", "1234"));
        userList.add(new UserDto("이순신", "1234"));

    }
}
