package com.example.simpleboard.board.controller;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDTO;
import com.example.simpleboard.board.model.BoardRequest;
import com.example.simpleboard.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardDTO create(
        @Valid
        @RequestBody BoardRequest boardRequest
    ){
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardDTO view(
        @PathVariable Long id
    ){
        var entity = boardService.view(id);
        log.info("result : {}" , entity);

        return entity;
    }
}
