package com.example.jpa.user.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    // JpaRepository 는 Spring Data JPA에서 제공하는 기본적인 JPA 저장소 기능을 제공하는 인터페이스입니다.
    // Spring Data JPA에서 제공하는 기본적인 CRUD (Create, Read, Update, Delete) 기능을 제공하는 인터페이스입니다.
    // UserEntity 클래스와 Long 타입의 ID를 사용하는 JPA 저장소에 대한 추가 기능을 제공합니다.
}
