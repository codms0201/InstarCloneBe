package com.example.InstarCloneBe.Board.controller;

import com.example.InstarCloneBe.Board.dto.reponse.BoardResponse;
import com.example.InstarCloneBe.Board.dto.request.BoardRequest;
import com.example.InstarCloneBe.Board.entity.Board;
import com.example.InstarCloneBe.Board.service.BoardService;
import com.example.InstarCloneBe.member.entity.Member;
import com.example.InstarCloneBe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<BoardResponse> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BoardResponse>> getBoardsByMemberId(@PathVariable Long userId) {
        List<BoardResponse> boards = boardService.getBoardsByMemberId(userId);
        return ResponseEntity.ok(boards);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardResponse> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequest boardRequest) {
        BoardResponse updatedBoard = boardService.updateBoard(boardId, boardRequest);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{boardId}/user")
    public ResponseEntity<Long> getUserIdByBoardId(@PathVariable Long boardId) {
        Long userId = boardService.getUserIdByBoardId(boardId);
        return ResponseEntity.ok(userId);
    }
}
