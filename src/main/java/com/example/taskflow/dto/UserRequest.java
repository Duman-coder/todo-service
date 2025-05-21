package com.example.taskflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email
) {}

