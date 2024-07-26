//package com.example.InstarCloneBe.Board.controller;
//
//import com.example.InstarCloneBe.Board.dto.request.BoardRequest;
//import com.example.InstarCloneBe.Board.entity.Board;
//import com.example.InstarCloneBe.Board.service.BoardService;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@RestController
//@RequestMapping("/api/boards")
//@NoArgsConstructor
//public class BoardController {
//
//    @Autowired
//    private BoardService boardService;
//
//    @PostMapping("/create")
//    public ResponseEntity<Board> createBoard(
//            @RequestParam("content") String content,
//            @RequestParam("imgUrl") MultipartFile imgUrl) {
//
//        // 이미지 파일 처리
//        String imgUrlPath = ""; // 파일 처리 후 이미지 URL 설정
//
//        BoardRequest boardRequest = new BoardRequest(content, imgUrlPath);
//        Board board = boardService.createBoard(boardRequest);
//        return ResponseEntity.ok(board);
//    }
//}
