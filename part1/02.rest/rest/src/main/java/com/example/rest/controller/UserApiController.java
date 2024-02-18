package com.example.rest.controller;

import com.example.rest.model.Api;
import com.example.rest.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {


    // 데이터베이스 없이 List에 유저 정보 담기
    // UserResponse 클래스의 빌더를 사용하여 정보를 효과적으로 구성할 수 있음.

    // 유저 정보를 담을 리스트 생성
    private static List<UserResponse> userList = List.of(
            // 첫 번째 유저 정보
            UserResponse.builder()
                    .id("1")
                    .age(10)
                    .name("홍길동")
                    .build(),

            // 두 번째 유저 정보
            UserResponse.builder()
                    .id("2")
                    .age(10)
                    .name("유관순")
                    .build()
    );

    // 위의 코드는 데이터베이스를 사용하지 않고도 애플리케이션 내에서 사용할 수 있는 간단한 유저 리스트를 생성하는 예시
    // UserResponse 클래스의 빌더를 활용하여 각 유저의 속성을 쉽게 설정할 수 있음
    // 각 유저의 id, age, name은 빌더 패턴을 사용하여 명시적이고 가독성 있게 설정
    // 생성된 유저 리스트는 애플리케이션 내에서 필요한 데이터를 간편하게 활용할 수 있음


    // IDE나 컴파일러 설정에서 메서드 파라미터의 이름이 유지되지 않는다면
    // @PathVariable에 명시적으로 value를 사용하여 템플릿 변수와 메서드 파라미터 이름을 일치시켜야 함
    @GetMapping("/id/{userId}")
    public Api<UserResponse> getUser(
            @PathVariable(value = "userId") String userId
    ){

        //강제로 예외처리 던지기
        if(true){
            throw new RuntimeException();
        }

        // 사용자 목록에서 주어진 userId와 일치하는 사용자를 찾기 위해 스트림 사용
        var user = userList.stream().filter(
                        it -> it.getId().equals(userId)
                )
                .findFirst()  // 첫 번째로 일치하는 사용자를 찾음
                .get();       // Optional에서 사용자를 꺼내옴

        // API 응답을 생성하기 위해 빌더 패턴 사용
        Api<UserResponse> response = Api.<UserResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))  // HTTP 상태 코드를 문자열로 설정
                .resultMessage(HttpStatus.OK.name())               // HTTP 상태 코드의 이름을 설정
                .data(user)                                       // 찾은 사용자를 응답 데이터로 설정
                .build();                                         // API 응답 객체 생성

        return response;  // 완성된 API 응답 반환
    }

}

/*  JSON 데이터 예시

// 데이터가 존재하되, result_code와 result_message가 null인 경우
* {
    "result_code": null,
    "result_message": null,
    "data": {
        "id": "1",
        "name": "홍길동",
        "age": 10
    }
}
*
// result_code와 result_message 값이 설정된 경우
* {
    "result_code": "200",
    "result_message": "OK",
    "data": {
        "id": "2",
        "name": "유관순",
        "age": 10
    }
}

* // 데이터가 없을 때 서버에서 지정한 형태로 파싱하기
* {
    "result_code": "404",
    "result_message": "Not Found",
    "data": null
}

* */


// 에러 관련 주석 수정

/*
 *  발생한 에러: java.lang.IllegalArgumentException
 *  이 에러는 파라미터의 이름 정보가 클래스 파일에 없어서 발생합니다.
 *  String 타입의 인자에 이름이 명시되지 않았음.
 *  @PathVariable(value = "userId") String userId 로 수정하여 에러를 해결함.
 *
 * */

