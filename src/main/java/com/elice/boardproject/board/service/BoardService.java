package com.elice.boardproject.board.service;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardDTO;
import com.elice.boardproject.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO.toEntity());
    }
}
