package com.elice.boardproject.comment.service;

import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.global.exception.ExceptionCode;
import com.elice.boardproject.global.exception.ServiceLogicException;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    public List<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
        log.info(post.getTitle());

        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        Comment foundComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> foundComment.setContent(content));

        return commentRepository.save(foundComment);
    }

    public void deleteComment(Long commentId) {
        Comment foundcomment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        commentRepository.delete(foundcomment);
    }
}
