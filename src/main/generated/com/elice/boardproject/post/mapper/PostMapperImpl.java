package com.elice.boardproject.post.mapper;

import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.entity.PostPostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T17:48:05+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postPostDTOToPost(PostPostDto postPostDto) {
        if ( postPostDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( postPostDto.getTitle() );
        post.setContent( postPostDto.getContent() );

        return post;
    }
}
