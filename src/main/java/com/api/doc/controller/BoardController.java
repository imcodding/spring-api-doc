package com.api.doc.controller;

import com.api.doc.dto.Board;
import com.api.doc.repository.BoardRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @Operation(summary = "게시글 목록 조회 API", description = "Bearer 토큰이 필요합니다.")
    @GetMapping("/api/v1/board/list")
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Operation(summary = "게시글 상세 조회 API", description = "Bearer 토큰이 필요합니다.")
    @GetMapping("/api/v1/board/{boardId}")
    public Board getBoard(@PathVariable Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Operation(summary = "게시글 등록 API", description = "Bearer 토큰이 필요합니다.")
    @PostMapping("/api/v1/board")
    public Board addBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }
}
