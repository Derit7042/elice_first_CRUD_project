package com.elice.boardproject;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.repository.BoardRepository;
import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class DataInit {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("init stub data");
        boardRepository.create(new Board("투수 게시판", "투수와 관련된 얘기를 나누고 정보를 공유할 수 있는 게시판입니다."));
        boardRepository.create(new Board("타자 게시판", "타자와 관련된 얘기를 나누고 정보를 공유할 수 있는 게시판입니다."));
        Board board = boardRepository.findById(1L).orElseThrow(() -> new RuntimeException());
        Board board2 = boardRepository.findById(2L).orElseThrow(() -> new RuntimeException());

        postRepository.save(new Post(board,"구속 빨라지려면", "구속 빨라지려면 어떤 운동을 해야하나요?"));
        postRepository.save(new Post(board, "투구하기 전에 하면 좋은 것", "투구하기 전에 스트레칭과 튜빙으로 어깨에 긴장감을 주며 풀어주면 좋습니다."));
        postRepository.save(new Post(board,"김광현 슬라이더 어떻게 던지나요?", "김광현 슬라이더 그립이랑 어떻게 던지는지 궁금해요."));

        postRepository.save(new Post(board2, "전문적으로 타자 훈련 받고싶은데", "전문적으로 타자 훈련 받고 싶은데, 좋은 레슨장 있나요?"));
        postRepository.save(new Post(board2, "체중이동의 중요성", "강한 파워를 만들어내기 위한 조건"));
        Post post = postRepository.findById(1L).orElseThrow(() -> new RuntimeException());
        Post post2 = postRepository.findById(2L).orElseThrow(() -> new RuntimeException());

        commentRepository.save(new Comment(post, "섀도우 피칭이라는 것이 있는데 해보세요"));
        commentRepository.save(new Comment(post, "하체운동 많이 하세요."));
        commentRepository.save(new Comment(post2, "좋은 정보 감사합니다 ^^"));
    }
}

