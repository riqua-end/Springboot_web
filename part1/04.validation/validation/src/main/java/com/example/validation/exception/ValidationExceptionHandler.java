package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice // 모든 @ControllerAdvice와 @ResponseBody에 적용되는 전역적인 예외 처리기를 정의합니다.
public class ValidationExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    // MethodArgumentNotValidException 예외를 처리하는 메서드입니다.
    public ResponseEntity<Api> validationException(
            MethodArgumentNotValidException exception // 발생한 예외 객체를 받아옵니다.
    ){
        log.error("",exception); // 에러 로그를 출력합니다.

        // 유효성 검사 실패로 인한 각 필드의 에러 메시지를 생성합니다.
        var errorMessageList = exception.getFieldErrors().stream() // 필드 에러 목록을 스트림으로 변환합니다.
                .map( it -> {
                    var format = "%s : { %s } 는 %s"; // 에러 메시지 형식을 지정합니다.
                    var message = String.format(format,it.getField(),it.getRejectedValue(),it.getDefaultMessage());
                    return message;
                }).collect(Collectors.toList()); // 스트림 요소를 리스트로 수집합니다.

        // 에러 응답 객체를 생성합니다.
        var error = Api.Error
                .builder()
                .errorMessage(errorMessageList) // 유효성 검사 실패로 인한 에러 메시지를 설정합니다.
                .build();

        var errorResponse = Api
                .builder()
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value())) // 상태 코드를 설정합니다.
                .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase()) // 상태 메시지를 설정합니다.
                .error(error) // 에러 객체를 설정합니다.
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); // BAD_REQUEST 상태 코드와 에러 응답을 반환합니다.
    }
}

/*
*
Builder 패턴 적용: Api 클래스는 빌더 패턴을 사용하여 객체를 생성합니다.

이 패턴을 사용하면 객체를 생성하고 초기화하는 과정을 분리할 수 있으며, 객체 생성 코드를 간결하게 유지할 수 있습니다.

객체 초기화: builder() 메서드는 Api 클래스의 빌더 객체를 생성합니다. 이 빌더 객체를 사용하여 Api 객체의 필드를 설정하고 초기화합니다.

객체 생성: build() 메서드는 빌더 객체에서 설정된 값으로 실제 Api 객체를 생성합니다. 이렇게 생성된 Api 객체는 에러 응답으로 사용됩니다.

*/