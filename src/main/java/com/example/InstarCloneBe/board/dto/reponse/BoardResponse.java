package com.example.InstarCloneBe.board.dto.reponse;

import com.example.InstarCloneBe.board.entity.Board;

public record BoardResponse(Long id, Long memberId, String content, String imgUrl) {
    public static BoardResponse fromEntity(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getMember().getId(),
                board.getContent(),
                board.getImgUrl()
        );
    }
}