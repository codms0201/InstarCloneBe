package com.example.InstarCloneBe.Board.service;

import com.example.InstarCloneBe.Board.dto.request.BoardRequest;
import com.example.InstarCloneBe.Board.entity.Board;
import com.example.InstarCloneBe.Board.repository.BoardRepository;
import com.example.InstarCloneBe.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public Board createBoard(BoardRequest boardRequest, Member member) {
        Board board = boardRequest.toEntity(member);
        return boardRepository.save(board);
    }

////    @Transactional
////    public BoardResponse updateBoard(Long id, BoardRequest boardRequest) {
////        Board board = boardRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
////
////        board.setContent(boardRequest.content());
////        board.setImgUrl(boardRequest.imgUrl());
////
////        Board updatedBoard = boardRepository.save(board);
////        return BoardResponse.fromEntity(updatedBoard);
////    }
////
////    public BoardResponse getBoardById(Long id) {
////        Board board = boardRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
////        return BoardResponse.fromEntity(board);
////    }
////
////    public List<BoardResponse> getAllBoards() {
////        return boardRepository.findAll().stream()
////                .map(BoardResponse::fromEntity)
////                .collect(Collectors.toList());
////    }
////
////    @Transactional
////    public void deleteBoard(Long id) {
////        Board board = boardRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
////        boardRepository.delete(board);
////    }
}
