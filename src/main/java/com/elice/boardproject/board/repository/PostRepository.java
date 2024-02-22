package com.elice.boardproject.board.repository;

import com.elice.boardproject.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
