package com.example.taskflow.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        Long taskId,
        Long userId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

