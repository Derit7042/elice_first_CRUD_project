package com.elice.boardproject.post.service;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.global.exception.ExceptionCode;
import com.elice.boardproject.global.exception.ServiceLogicException;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostService {
    
    private final PostRepository postRepository;
    private final BoardService boardService;

    public PostService(PostRepository postRepository, BoardService boardService) {
        this.boardService = boardService;
        this.postRepository = postRepository;
    }

    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            return postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
        }
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
    }

    public Post createPost(Post post, Long boardId) {
        Board boardToCreate = boardService.findBoardById(boardId);
        post.setBoard(boardToCreate);
        Post savedPost = postRepository.save(post);

        return savedPost;
    }

    public Post updatePost(Post post, Long postId) {
        post.setId(postId);
        Post foundPost = postRepository.findById(post.getId())
                            .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
        
        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> foundPost.setTitle(title));
        Optional.ofNullable(post.getContent())
                .ifPresent(content -> foundPost.setContent(content));
        
        return postRepository.save(foundPost);
    }

    public void deletePost(Long id) {
        Post foundPost = postRepository.findById(id)
                            .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));

        postRepository.delete(foundPost);
    }
}
