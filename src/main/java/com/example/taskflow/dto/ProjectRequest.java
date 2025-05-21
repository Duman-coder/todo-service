package com.example.taskflow.dto;

import com.example.taskflow.model.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ProjectRequest(
        @NotBlank String name,
        String description,
        ProjectStatus status,
        UUID ownerId
) {}

