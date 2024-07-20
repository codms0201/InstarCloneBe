package com.example.InstarCloneBe.board.controller;


import com.example.InstarCloneBe.board.dto.reponse.BoardResponse;
import com.example.InstarCloneBe.board.dto.request.BoardRequest;
import com.example.InstarCloneBe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody BoardRequest boardRequest) {
        BoardResponse createdBoard = boardService.createBoard(boardRequest);
        return ResponseEntity.ok(createdBoard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoardById(@PathVariable Long id) {
        BoardResponse boardResponse = boardService.getBoardById(id);
        return ResponseEntity.ok(boardResponse);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<BoardResponse> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(@PathVariable Long id, @RequestBody BoardRequest boardRequest) {
        BoardResponse updatedBoard = boardService.updateBoard(id, boardRequest);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
