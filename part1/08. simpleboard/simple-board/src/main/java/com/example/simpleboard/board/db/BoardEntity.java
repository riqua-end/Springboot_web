package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

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
    @Where(clause = "status = 'REGISTERED'") // 조건절 status 가 REGISTERED 인 것만 불러오기
    @Builder.Default // Builder 패턴에서 누락되지 않도록
    @OrderBy(value = "id desc") // 최신글이 상단에 위치하도록 정렬
    private List<PostEntity> postList = List.of();
}
