package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDTO;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;

    public BoardDTO toDto(BoardEntity boardEntity){

        var postList = boardEntity.getPostList().stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());


        return BoardDTO.builder()
                .id(boardEntity.getId())
                .status(boardEntity.getStatus())
                .boardName(boardEntity.getBoardName())
                .postList(postList)
                .build();
    }
}
