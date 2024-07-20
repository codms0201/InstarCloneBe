package com.example.InstarCloneBe.board.dto.request;

import com.example.InstarCloneBe.board.entity.Board;

public record BoardRequest(String content, String imgUrl) {
    public Board toEntity() {
        return Board.builder()
                .content(content)
                .imgUrl(imgUrl)
                .build();
    }
}
