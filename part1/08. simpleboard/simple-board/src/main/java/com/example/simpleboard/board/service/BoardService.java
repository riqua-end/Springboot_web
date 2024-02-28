package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDTO;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    // 기본 생성자에 해당 값이 들어가서 생성됨
    // final 은 생성자에서 초기화 되어야 함
    private final BoardRepository boardRepository;

    private final BoardConverter boardConverter;

    public BoardDTO create(
        BoardRequest boardRequest
    ){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var saveEntity = boardRepository.save(entity);
        return boardConverter.toDto(saveEntity);
    }

    public BoardDTO view(Long id) {
        var entity = boardRepository.findById(id).get();
        return boardConverter.toDto(entity);
    }
}
