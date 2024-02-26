package com.elice.boardproject.board.service;

import com.elice.boardproject.board.domain.Post;
import com.elice.boardproject.board.dto.AddPostRequest;
import com.elice.boardproject.board.dto.UpdatePostRequest;
import com.elice.boardproject.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    public Post save(AddPostRequest addPostRequest) {
        return boardRepository.save(addPostRequest.toEntity());
    }

    public List<Post> findAll() {
        return boardRepository.findAll();
    }

    public Post findById(long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public Post update(long id, UpdatePostRequest updatePostRequest) {
        Post post = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        post.update(updatePostRequest.getTitle(), updatePostRequest.getContent());

        return post;
    }
}

