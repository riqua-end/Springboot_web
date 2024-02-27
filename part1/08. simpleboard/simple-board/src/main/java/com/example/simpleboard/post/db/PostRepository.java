package com.example.simpleboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    // 게시글 삭제 후 게시글 상태가 UNREGISTERED 인것만 불러오기

    // select * from post where id = ? and status = ? order by id desc limit 1
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);
}
