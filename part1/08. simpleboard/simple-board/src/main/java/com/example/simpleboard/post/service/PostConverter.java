package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostConverter {


    public PostDTO toDto(PostEntity postEntity){
        return PostDTO.builder()
                .id(postEntity.getId())
                .boardId(postEntity.getBoard().getId())
                .userName(postEntity.getUserName())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .postedAt(postEntity.getPostedAt())
                .build();
    }
}
