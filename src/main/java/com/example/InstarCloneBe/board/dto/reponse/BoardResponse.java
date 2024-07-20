//package com.example.InstarCloneBe.board.dto.reponse;
//
//import com.example.InstarCloneBe.board.entity.Board;
//
//import java.time.LocalDateTime;
//
//public record BoardResponse(Long id, String content, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
//    public static BoardResponse fromEntity(Board board) {
//        return new BoardResponse(
//                board.getId(),
//                board.getContent(),
//                board.getImgUrl(),
//                board.getCreatedAt(),
//                board.getUpdatedAt()
//        );
//    }
//}