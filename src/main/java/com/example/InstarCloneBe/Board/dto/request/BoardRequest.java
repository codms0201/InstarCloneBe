package com.example.InstarCloneBe.Board.dto.request;

import com.example.InstarCloneBe.Board.entity.Board;
import com.example.InstarCloneBe.member.entity.Member;

public record BoardRequest(String content, String imgUrl, Long memberId) {
    public Board toEntity(Member member) {
        return Board.builder()
                .member(member)
                .content(content)
                .imgUrl(imgUrl)
                .build();
    }
}