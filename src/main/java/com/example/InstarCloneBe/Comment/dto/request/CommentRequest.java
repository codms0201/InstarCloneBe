package com.example.InstarCloneBe.Comment.dto.request;

public record CommentRequest(String content, Long boardId, Long memberId) {
}
