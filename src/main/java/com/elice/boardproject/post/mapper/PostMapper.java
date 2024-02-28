package com.elice.boardproject.post.mapper;

import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.entity.PostPostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postPostDTOToPost(PostPostDto postPostDto);

};