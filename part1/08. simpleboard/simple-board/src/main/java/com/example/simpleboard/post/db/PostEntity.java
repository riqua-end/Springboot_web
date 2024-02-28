package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long boardId;

    @ManyToOne
    @JsonIgnore // 무한 반복 탈출
    @ToString.Exclude // ToString의 무한 반복을 막을 때 사용
    private BoardEntity board; // 객체지향으로 접근, 1:N 설정 , board_id

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @Transient // 데이터베이스에 해당 컬럼이 없을 때 사용
    private List<ReplyEntity> replyList = List.of();
}
