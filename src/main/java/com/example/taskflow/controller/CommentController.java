package com.example.taskflow.controller;

import com.example.taskflow.dto.CommentRequest;
import com.example.taskflow.dto.CommentResponse;
import com.example.taskflow.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/{taskId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable Long taskId,
            @RequestBody @Valid CommentRequest request
    ) {
        return ResponseEntity.ok(commentService.create(taskId, request));
    }

    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getAll(
            @PathVariable Long taskId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(commentService.getAllByTaskId(taskId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long taskId,
            @PathVariable Long id
    ) {
        commentService.delete(taskId, id);
        return ResponseEntity.noContent().build();
    }
}

