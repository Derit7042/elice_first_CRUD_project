package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.domain.Post;
import com.elice.boardproject.board.dto.AddPostRequest;
import com.elice.boardproject.board.dto.PostResponse;
import com.elice.boardproject.board.dto.UpdatePostRequest;
import com.elice.boardproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequest addPostRequest) {
        Post savedPost = boardService.save(addPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }

    @GetMapping("api/posts/{id}")
    public ResponseEntity<PostResponse> findPost(@PathVariable("id") long id) {
        Post post = boardService.findById(id);

        return ResponseEntity.ok()
                .body(new PostResponse(post));
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        List<PostResponse> posts = boardService.findAll()
                .stream()
                .map(PostResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(posts);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deletePosts(@PathVariable("id") long id) {
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") long id, @RequestBody UpdatePostRequest updatePostRequest) {
        Post updatedPost = boardService.update(id, updatePostRequest);

        return ResponseEntity.ok()
                .body(updatedPost);
    }
}

