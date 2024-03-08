package com.example.jwt.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    private static String secretKey = "java11SpringBootJWTTokenIssueMethod";

    // **JWT 토큰 생성**
    public String create(
            Map<String, Object> claims, // JWT Claims 에 담길 정보
            LocalDateTime expireAt      // JWT 만료 시각
    ){

        // HMAC-SHA256 알고리즘, 비밀 키로 서명에 사용될 키 생성
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // 자바 8 Date 타입으로 변환 (LocalDateTime -> Instant -> Date)
        var _exprieAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

        // JWT 빌더를 사용하여 토큰 생성
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) // 서명 설정
                .setClaims(claims)                       // Claims 설정
                .setExpiration(_exprieAt)                // 만료 시간 설정
                .compact();                              // 컴팩트한 문자열 JWT 생성
    }

    // **JWT 토큰 검증**
    public void validation(String token){

        // HMAC-SHA256 알고리즘,  동일한 비밀 키를 사용하여 파서 생성
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try{
            // 파서를 사용하여 JWT 토큰 파싱 및 Claims 추출
            var result = parser.parseClaimsJws(token);

            result.getBody().entrySet().forEach(value -> {
                log.info("key  : {} ,value = {} " , value.getKey(),value.getValue()); // Claims의 key, value 출력
            });

        } catch (Exception e){
            // 예외별 처리
            if (e instanceof SignatureException){
                throw new RuntimeException("JWT Token Not Valid Exception"); // 서명이 유효하지 않을 경우
            } else if (e instanceof ExpiredJwtException) {
                throw new RuntimeException("JWT Token Expired Exception"); // 토큰이 만료된 경우
            } else {
                throw new RuntimeException("JWT Token Validation Exception"); // 그 외 JWT 검증 예외
            }
        }

    }
}
