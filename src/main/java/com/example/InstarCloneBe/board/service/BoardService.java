package com.example.InstarCloneBe.board.service;


import com.example.InstarCloneBe.board.dto.request.BoardRequest;
import com.example.InstarCloneBe.board.entity.Board;
import com.example.InstarCloneBe.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public Board createBoard(BoardRequest boardRequest) {
        Board board = boardRequest.toEntity();
        return boardRepository.save(board);
    }

//    @Transactional
//    public BoardResponse updateBoard(Long id, BoardRequest boardRequest) {
//        Board board = boardRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
//
//        board.setContent(boardRequest.content());
//        board.setImgUrl(boardRequest.imgUrl());
//
//        Board updatedBoard = boardRepository.save(board);
//        return BoardResponse.fromEntity(updatedBoard);
//    }
//
//    public BoardResponse getBoardById(Long id) {
//        Board board = boardRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
//        return BoardResponse.fromEntity(board);
//    }
//
//    public List<BoardResponse> getAllBoards() {
//        return boardRepository.findAll().stream()
//                .map(BoardResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public void deleteBoard(Long id) {
//        Board board = boardRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
//        boardRepository.delete(board);
//    }
}
