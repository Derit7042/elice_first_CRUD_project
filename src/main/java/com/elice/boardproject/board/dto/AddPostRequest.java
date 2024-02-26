package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.domain.Post;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddPostRequest {
    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}

