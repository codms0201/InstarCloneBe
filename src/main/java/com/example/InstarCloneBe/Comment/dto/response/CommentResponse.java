package com.example.InstarCloneBe.Comment.dto.response;

import com.example.InstarCloneBe.Comment.Entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, Long boardId, Long memberId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getBoard().getId(),
                comment.getMember().getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}