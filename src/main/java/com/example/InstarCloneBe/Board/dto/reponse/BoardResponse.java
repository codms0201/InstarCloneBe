package com.example.InstarCloneBe.Board.dto.reponse;

import com.example.InstarCloneBe.Board.entity.Board;

import java.time.LocalDateTime;

public record BoardResponse(Long id, String content, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static BoardResponse fromEntity(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getContent(),
                board.getImgUrl(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}