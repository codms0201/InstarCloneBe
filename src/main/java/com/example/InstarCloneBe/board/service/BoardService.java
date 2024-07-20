package com.example.InstarCloneBe.board.service;


import com.example.InstarCloneBe.board.entity.Board;
import com.example.InstarCloneBe.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoardResponse createBoard(BoardRequest boardRequest) {
        Member member = memberRepository.findById(boardRequest.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Board board = boardRequest.toEntity(member);
        Board savedBoard = boardRepository.save(board);
        return BoardResponse.fromEntity(savedBoard);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardRequest boardRequest) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        board.setContent(boardRequest.content());
        board.setImgUrl(boardRequest.imgUrl());

        Board updatedBoard = boardRepository.save(board);
        return BoardResponse.fromEntity(updatedBoard);
    }

    public BoardResponse getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        return BoardResponse.fromEntity(board);
    }

    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        boardRepository.delete(board);
    }
}
