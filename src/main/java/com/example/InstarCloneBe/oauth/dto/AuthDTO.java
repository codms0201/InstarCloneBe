package com.example.InstarCloneBe.oauth.dto;

import lombok.Builder;


@Builder
public record AuthDTO(
        Long memberId,
        String name,
        String email

) {
}