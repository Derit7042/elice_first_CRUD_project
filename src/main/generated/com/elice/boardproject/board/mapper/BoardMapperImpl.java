package com.elice.boardproject.board.mapper;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.entity.BoardPostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T17:48:05+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board boardPostDtoToBoard(BoardPostDto boardPostDto) {
        if ( boardPostDto == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.name( boardPostDto.getName() );
        board.description( boardPostDto.getDescription() );

        return board.build();
    }
}
