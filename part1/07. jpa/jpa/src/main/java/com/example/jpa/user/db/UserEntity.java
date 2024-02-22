package com.example.jpa.user.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
*   @Data: Lombok 어노테이션으로 getter, setter, equals, hashCode, toString 메서드를 자동으로 생성합니다.
    @NoArgsConstructor: Lombok 어노테이션으로 인자 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor: Lombok 어노테이션으로 모든 필드를 인자로 받는 생성자를 자동으로 생성합니다.
    @Builder: Lombok 어노테이션으로 빌더 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
    @Entity(name = "user"): 이 클래스가 JPA 엔티티임을 명시하고 'user' 테이블에 매핑한다는 것을 나타냅니다.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user") // user table하고 연결
public class UserEntity {
    @Id
    // Mysql 데이터베이스를 사용하는 경우 id 필드는 AUTO_INCREMENT 속성을 가진 INT 형식으로 생성됩니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 어노테이션을 사용하여 주요 키 필드의 값이 어떻게 생성되는지 설정

    private Long id; // Primary key

    private String name;

    private Integer age;

    private String email;
}
