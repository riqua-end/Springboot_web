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
