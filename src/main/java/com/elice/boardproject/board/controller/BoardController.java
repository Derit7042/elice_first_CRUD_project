package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.entity.BoardPostDto;
import com.elice.boardproject.board.mapper.BoardMapper;
import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
    
    private final BoardService boardService;
    private final PostService postService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, PostService postService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.postService = postService;
        this.boardMapper = boardMapper;
    }

    @GetMapping
    public String getBoards(Model model) {
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    /*
     comment 키워드 검색 기능 추가
     */
    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable("boardId") Long boardId,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10") int size,
                           @RequestParam(name = "keyword", required = false) String keyword,
                           Model model) {
        Board board = boardService.findBoardById(boardId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);

        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);
        return "board/board";
    }
    
    @GetMapping("/create")
    public String createBoard(Model model) {
        return "board/createBoard";
    }

    @PostMapping("/create")
    public String createBoardPost(@ModelAttribute BoardPostDto boardPostDto) {
        Board board = boardMapper.boardPostDtoToBoard(boardPostDto);
        boardService.createBoard(board);

        return "redirect:/boards";
    }

    @GetMapping("/{boardId}/edit")
    public String editBoard(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findBoardById(boardId);
        model.addAttribute("board", board);

        return "board/editBoard";
    }

    @PostMapping("/{boardId}/edit")
    public String editBoardPost(@PathVariable("boardId") Long boardId, @ModelAttribute BoardPostDto boardPostDto) {
        Board board = boardMapper.boardPostDtoToBoard(boardPostDto).toBuilder().id(boardId).build();
        boardService.updateBoard(board);

        return "redirect:/boards";
    }

    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);

        return "redirect:/boards";
    }
    
}
