package com.api.doc.repository;

import com.api.doc.dto.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BoardRepository {

    private static final Map<Long, Board> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Board save(Board board) {
        board.setBoardId(++sequence);
        store.put(sequence, board);
        return board;
    }

    public List<Board> findAll() { return new ArrayList<>(store.values()); }
    public Board findById(Long boardId) { return store.get(boardId); }
}
