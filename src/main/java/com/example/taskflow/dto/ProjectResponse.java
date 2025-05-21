package com.example.taskflow.dto;

import com.example.taskflow.model.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        ProjectStatus status,
        UUID ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

