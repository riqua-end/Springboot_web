package com.example.session.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto { // 사용자 정보를 저장하는 역할

    private String name;
    private String password;
}
