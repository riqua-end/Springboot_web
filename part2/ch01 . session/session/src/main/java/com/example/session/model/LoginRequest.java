package com.example.session.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginRequest { // 로그인 시 사용자로부터 아이디와 비밀번호를 받는 역할

    private String id;

    private String password;
}
