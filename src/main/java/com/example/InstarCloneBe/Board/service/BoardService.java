package com.example.InstarCloneBe.Board.service;

import com.example.InstarCloneBe.Board.dto.reponse.BoardResponse;
import com.example.InstarCloneBe.Board.dto.request.BoardRequest;
import com.example.InstarCloneBe.Board.entity.Board;
import com.example.InstarCloneBe.Board.repository.BoardRepository;
import com.example.InstarCloneBe.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BoardResponse> getBoardsByMemberId(Long memberId) {
        return boardRepository.findByMemberId(memberId).stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardResponse updateBoard(Long boardId, BoardRequest boardRequest) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        board.setContent(boardRequest.content());
        board.setImgUrl(boardRequest.imgUrl());
        boardRepository.save(board);

        return BoardResponse.fromEntity(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        boardRepository.delete(board);
    }

    @Transactional
    public Long getUserIdByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        return board.getMember().getId();
    }
}
