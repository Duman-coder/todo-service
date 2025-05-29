package com.example.taskflow.controller;

import com.example.taskflow.model.mongo.TaskHistory;
import com.example.taskflow.service.TaskHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/history")
@RequiredArgsConstructor
public class TaskHistoryController {

    private final TaskHistoryService taskHistoryService;

    @GetMapping
    public ResponseEntity<List<TaskHistory>> getHistory(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskHistoryService.getTaskHistory(taskId));
    }
}

