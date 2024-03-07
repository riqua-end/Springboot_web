# Fastcampus course3

```
SpringBoot 로 배우는 Backend 웹 개발
```

[02 . REST API , Exception]
---
* REST API 와 Exception Handler로 예외처리 하기


[04 . Spring Validation]
---
* Spring validation 을 이용해서 유효성 검사


[05 . memory db ]
---
* Memory Database 실습

[06 . docker-compose ]
---
* Docker 설치 및 설정
* Mysql workbench version : mysql-workbench-community-8.0.36-winx64
```
version: "3"
services:
  db:
    image: mysql:8.0.36
    restart: always
    command:
      - --lower_case_table_names=1
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci

    container_name: mysql
    ports:
      - "3306:3306"
    environment:
      - MYSQL_DATABASE=mydb
      - MYSQL_ROOT_PASSWORD=[password]
      - TZ=Asia/Seoul
    volumes:
      - C:\Temp\MYSQL:/var/lib/mysql
```

[07 . jpa ]
---
* Spring Data JPA 로 MySQL Database 연결하고 적용

[07_1 memorydbToJpa ]
---
### memorydbToJpa
* 기존 Memorydb 프로젝트를 Jpa로 변환
* MySQL Database 사용
* Query Method , Native Method (@Query 어노테이션)
* @Query는 실행할 메서드 위에 정적쿼리를 작성 ( JQPL 문법 )
#### JPQL ?
* JPA(Java Persistence API)에서 사용하는 객체 지향 쿼리 언어. SQL과 유사하지만, 엔티티 객체를 중심으로 쿼리를 작성하며 데이터베이스 종속성 없이 사용할 수 있다는 장점이 있음
### application.yaml
```
spring:
  jpa:
    show-sql: true
    properties:
      format_sql: true
      dialect: package org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: validate
  datasource:
    url: jdbc:mysql://localhost:3306/book_store?useSSL=false&useUnicode=true&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: [username]
    password: [password]

server:
  port: 8181
```
---
### [ Spring Data JPA Query Method Docs ]
* <https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html>
---

## [08 . simple_board]
* Language : Java 17
* Framework : Spring Boot 3.2.3
* DBMS : MySQL 8
* DB Library : JPA
---
### [ simple_board ERD ]
![스크린샷 2024-02-24 174604](https://github.com/riqua-end/Springboot_web/assets/129530989/137daefd-9c61-47e9-997f-30e33f2f74c9)


---
## [09 . filter & interceptor & AOP]
### Filter ?
* 필터는 Servlet 또는 컨트롤러에 도달하기 전에 요청 및 응답을 가로채서 처리
### ContentCaching ?
* 일반적으로 Servlet은 요청 및 응답 본문을 한번만 읽을 수 있음.
* ContentCaching 을 사용하면 본문을 캐싱하여 나중에 다시 읽을 수 있음.
* ContentCaching 을 사용하여 요청/응답 본문을 문자열로 변환하여 쉽게 로깅
* copyBodyToResponse()로 본문을 다시 실제 응답 객체에 복사해야됨
### interceptor
* 스프링 인터셉터는 HTTP 요청을 가로채서 처리하는 컴포넌트
* 클라이언트의 요청이 컨트롤러로 전달되기 전에 사전 작업을 수행하거나,
* 컨트롤러의 실행 이후에 사후 작업을 수행.
### AOP ?
* AOP (Aspect Oriented Programming) - 관점 지향 프로그래밍
* AOP는 공통 기능을 코드의 여러 지점에 분리하여 코드의 모듈성과 유지보수성을 향상시키는 프로그래밍 기법
* 암호화/복호화 외에도 로깅, 성능 측정 등 다양한 용도로 활용
---
![스크린샷 2024-03-03 171341](https://github.com/riqua-end/Springboot_web/assets/129530989/ddc976d7-7cb1-481d-908e-0dc491c2f330)
![스크린샷 2024-03-03 171420](https://github.com/riqua-end/Springboot_web/assets/129530989/96f8a87b-8195-4d95-873b-6103745c4792)

## Filter 와 AOP 의 차이점
* **Filter** :  요청/응답 레벨에서 적용 , 응답/요청 필터링 , (인증,권한관리,캐싱 등)
* **AOP** : 메서드, 필드 등 코드 레벨에서 적용 , 공통 기능 분리 및 재사용 (로깅,트랜잭션,보안 등)

## HTTP Session
HTTP Session 인증은 웹 어플리케이션에서 사용하는 인증 방법으로 사용자 인증 정보를 **서버측에서 유지하고 관리**하기 위한 방법

### 인증 과정
1. 사용자가 로그인을 시도
2. 서버는 사용자의 인증 정보를 검증하여 session id를 생성
3. 세션은 서버측에서 관리되며, 서버에서 갱신 및 정보를 변경
4. 세션 ID는 쿠키 방식으로 사용자에게 전달 되며, 웹 어플리케이션에서 사용

## HTTP Cookie
HTTP Cookie는 웹 브라우저와 웹 서버간에 상태 정보를 유지하기 위한 기술
쿠키는 HTTP 헤더에 Set-Cookie와 같은 헤더를 통해 서버에서 클라이언트에 전송
쿠키는 키-값 쌍으로 이루어져 있으며, 이름, 값, 유효기간, 도메인, 경로 등의 정보를 포함

### 특징
1. 쿠키는 클라이언트 측에 저장.즉 서버가 클라이언트의 상태 정보를 확인하려면 쿠키를 클라이언트에서 전송받아야됨
2. 쿠키는 유효기간을 가지고 있음. 유효기간이 지나면 쿠키는 삭제됨
3. 쿠키는 보안 문제가 있을 수 있음. 쿠키에 민감한 정보를 저장시에는 HTTPS와 같은 보안 프로토콜을 사용하여 암호화
4. 쿠키는 브라우저에서 관리되기 때문에 브라우저에서 쿠리를 삭제하거나 다른 브라우저에서 접속하는 경우에는 쿠키를 공유 할 수 없음.

## HTTP Header

### HTTP Header를 통한 인증
* 서버와 클라이언트 간의 인증을 HTTP 헤더를 통해서 수행
* HTTP Basic,HTTP Digest,Oauth 와 같은 프로토콜을 통해서 구현
* 그 외에는 특정 header에 token 또는 특정한 값을 넣어서 사용자를 인식하고 인증
