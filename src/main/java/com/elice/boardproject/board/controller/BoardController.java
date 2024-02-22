package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardDTO;
import com.elice.boardproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/articles")
    public ResponseEntity<Board> addBoard(@RequestBody BoardDTO boardDTO) {
        Board savedBoard = boardService.save(boardDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }
}
