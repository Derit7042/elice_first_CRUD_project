package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardDTO {
    private Long userId;
    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
