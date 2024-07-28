package com.example.InstarCloneBe.Comment.service;

import com.example.InstarCloneBe.Board.entity.Board;
import com.example.InstarCloneBe.Board.repository.BoardRepository;
import com.example.InstarCloneBe.Comment.dto.request.CommentRequest;
import com.example.InstarCloneBe.Comment.dto.response.CommentResponse;
import com.example.InstarCloneBe.Comment.Entity.Comment;
import com.example.InstarCloneBe.Comment.Repository.CommentRepository;
import com.example.InstarCloneBe.member.entity.Member;
import com.example.InstarCloneBe.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommentResponse addComment(CommentRequest commentRequest) {
        Board board = boardRepository.findById(commentRequest.boardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        Member member = memberRepository.findById(commentRequest.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Comment comment = Comment.builder()
                .content(commentRequest.content())
                .board(board)
                .member(member)
                .build();

        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }


}