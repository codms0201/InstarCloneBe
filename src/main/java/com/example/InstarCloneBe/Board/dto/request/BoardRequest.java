package com.example.InstarCloneBe.Board.dto.request;

import com.example.InstarCloneBe.Board.entity.Board;

public record BoardRequest(String content, String imgUrl) {
    public Board toEntity() {
        return Board.builder()
                .content(content)
                .imgUrl(imgUrl)
                .build();
    }
}
