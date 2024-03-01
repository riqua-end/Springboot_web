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
## [09 . filter]
### Filter ?
* 필터는 Servlet 또는 컨트롤러에 도달하기 전에 요청 및 응답을 가로채서 처리
### ContentCaching ?
* 일반적으로 Servlet은 요청 및 응답 본문을 한번만 읽을 수 있음.
* ContentCaching 을 사용하면 본문을 캐싱하여 나중에 다시 읽을 수 있음.
* ContentCaching 을 사용하여 요청/응답 본문을 문자열로 변환하여 쉽게 로깅
* copyBodyToResponse()로 본문을 다시 실제 응답 객체에 복사해야됨

