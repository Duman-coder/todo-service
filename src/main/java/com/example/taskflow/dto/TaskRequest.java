package com.example.taskflow.dto;

import com.example.taskflow.model.enums.TaskPriority;
import com.example.taskflow.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequest(
        @NotBlank String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime deadline,
        UUID assignedUserId,
        UUID projectId
) {}

