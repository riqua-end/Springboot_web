package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(
            mappedBy = "board"
    ) // 1:N 설정 (1은 BoardEntity, N은 PostEntity), N 설정은 mappedBy =
    private List<PostEntity> postList = List.of();
}
