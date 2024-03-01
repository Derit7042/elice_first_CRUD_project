package com.elice.boardproject.board.service;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.repository.BoardRepository;
import com.elice.boardproject.global.exception.ExceptionCode;
import com.elice.boardproject.global.exception.ServiceLogicException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    
    private final BoardRepository boardRepository;
    private Board foundBoard;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                            .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public Board createBoard(Board board) {
        return boardRepository.create(board);
    }

    public Board updateBoard(Board board) {
        foundBoard = boardRepository.findById(board.getId())
                                    .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        Optional.ofNullable(board.getName())
                .ifPresent(name -> { foundBoard = foundBoard.toBuilder().name(name).build(); });

        foundBoard = foundBoard.toBuilder().description(board.getDescription()).build();
        
        return boardRepository.update(foundBoard);
    }

    public void deleteBoard(Long id) {
        foundBoard = boardRepository.findById(id)
                                    .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
        
        boardRepository.delete(foundBoard);
    }

}
