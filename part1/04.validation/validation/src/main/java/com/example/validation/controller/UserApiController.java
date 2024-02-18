package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    // MethodArgumentNotValidException
    // @Valid 어노테이션을 통해 유효성 검사를 수행합니다.
    // 만약 요청 본문의 유효성 검사에 실패하면 MethodArgumentNotValidException이 발생합니다.
    // Spring Framework에서는 이 예외를 자동으로 처리하고 BindingResult에 에러를 저장합니다.

    @PostMapping("")
    public Api<UserRegisterRequest> register(
            @Valid // 요청 본문의 유효성 검사를 수행합니다.
            @RequestBody Api<UserRegisterRequest> userRegisterRequest // 요청 본문을 받아옵니다.
    ){
        log.info("init : {}",userRegisterRequest);

        var body = userRegisterRequest.getData(); // 요청에서 유저 등록 데이터를 추출합니다.

        // 응답을 생성합니다.
        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value())) // 상태 코드를 설정합니다.
                .resultMessage(HttpStatus.OK.getReasonPhrase()) // 상태 메시지를 설정합니다.
                .data(body) // 데이터를 설정합니다.
                .build(); // 응답 객체를 생성합니다.

        return response; // 응답을 반환합니다.
    }
}
