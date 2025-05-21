package com.example.taskflow.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("Проект с ID " + id + " не найден");
    }
}

