package com.example.jwt;

import com.example.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private JwtService jwtService; // JwtService 빈 주입

	@Test
	void contextLoads() {
		// Spring Boot 애플리케이션의 기본 컨텍스트 로딩 테스트 (주석이 필요없는 자명한 테스트)
	}

	@Test
	void tokenCreate(){
		// Claim 생성 - "user_id" 키에 923 값 저장
		var claims = new HashMap<String,Object>();
		claims.put("user_id",923);

		// 현재 시각에서 60초 후를 만료 시간으로 설정
		var expiredAt = LocalDateTime.now().plusSeconds(60);

		// JwtService의 create 메서드를 사용하여 토큰 생성
		var jwtToken = jwtService.create(claims,expiredAt);

		// 생성된 JWT 토큰 콘솔 출력
		System.out.println(jwtToken);
	}

	@Test
	void tokenValidation(){
		// 검증할 JWT 토큰 (이전에 생성된 토큰이거나 외부의 JWT 토큰)
		var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjMsImV4cCI6MTcwOTg4OTk0OX0.KoB0XY7v7be6_duCqOa2MNOC6XkFJrBF6s6XDxLy43Q";

		// JwtService의 validation 메서드로 토큰 검증
		jwtService.validation(token);
	}
}
