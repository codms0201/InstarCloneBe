package com.example.InstarCloneBe.board.dto.request;

import com.example.InstarCloneBe.board.entity.Board;
import com.example.InstarCloneBe.member.entity.Member;

public record BoardRequest(Long memberId, String content, String imgUrl) {
    public Board toEntity(Member member) {
        return Board.builder()
                .content(this.content)
                .imgUrl(this.imgUrl)
                .member(member)
                .build();
    }
}
