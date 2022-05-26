package com.api.doc.dto;

import lombok.Data;

@Data
public class Board {

    private Long boardId;
    private String title;
    private String content;
    private String userId;

    public Board(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
