package com.elice.boardproject.comment.controller;

import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.entity.CommentDto;
import com.elice.boardproject.comment.mapper.CommentMapper;
import com.elice.boardproject.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    public String createComment(@ModelAttribute CommentDto commentDto, @RequestParam("postId") Long postId, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        commentService.createComment(postId, comment);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/{commentId}/edit")
    public String updateComment(@PathVariable("commentId") Long commentId, @ModelAttribute CommentDto commentDto, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        Comment updatedComment = commentService.updateComment(commentId, comment);

        redirectAttributes.addAttribute("postId", updatedComment.getPost().getId());
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/posts";
    }
}
