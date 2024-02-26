package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {

    private final String title;
    private final String content;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
