package com.example.taskflow.dto;

import com.example.taskflow.model.enums.TaskPriority;
import com.example.taskflow.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime deadline,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID assignedUserId,
        UUID projectId
) {}

