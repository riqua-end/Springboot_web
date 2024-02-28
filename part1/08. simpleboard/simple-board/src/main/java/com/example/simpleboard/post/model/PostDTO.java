package com.example.simpleboard.post.model;

import com.example.simpleboard.board.db.BoardEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDTO {

    private Long id;

    private Long boardId;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    private String content;

    private LocalDateTime postedAt;
}
