package com.example.InstarCloneBe.Board.controller;

import com.example.InstarCloneBe.Board.dto.request.BoardRequest;
import com.example.InstarCloneBe.Board.entity.Board;
import com.example.InstarCloneBe.Board.service.BoardService;
import com.example.InstarCloneBe.member.entity.Member;
import com.example.InstarCloneBe.member.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Board> createBoard(@RequestBody BoardRequest boardRequest) {
        Member member = memberService.findMemberById(boardRequest.memberId());
        return ResponseEntity.ok(boardService.createBoard(boardRequest, member));
    }
}
