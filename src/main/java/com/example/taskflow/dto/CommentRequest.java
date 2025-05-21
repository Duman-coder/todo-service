package com.example.taskflow.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(
        @NotBlank String content,
        Long userId
) {}
