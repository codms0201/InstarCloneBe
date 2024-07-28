package com.example.InstarCloneBe.Comment.controller;

import com.example.InstarCloneBe.Comment.dto.request.CommentRequest;
import com.example.InstarCloneBe.Comment.dto.response.CommentResponse;
import com.example.InstarCloneBe.Comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.addComment(commentRequest);
        return ResponseEntity.ok(commentResponse);
    }
}
