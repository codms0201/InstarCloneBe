package com.example.InstarCloneBe.oauth.dto;

import lombok.Builder;

@Builder
public record SignupRequest(
        Long memberId,
        String name,
        String email,
        String picture,
        String nickname
) {
}
