package com.api.doc;

import com.api.doc.dto.Board;
import com.api.doc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        boardRepository.save(new Board("제목입니다.", "내용입니다.", "userA"));
        boardRepository.save(new Board("제목입니다.", "내용입니다.", "userB"));
    }
}
