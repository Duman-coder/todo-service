package com.example.taskflow.controller;

import com.example.taskflow.model.mongo.LogEntry;
import com.example.taskflow.service.LogEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogEntryController {

    private final LogEntryService logEntryService;

    @GetMapping
    public ResponseEntity<List<LogEntry>> getLogs(@RequestParam(required = false) String level) {
        if (level != null) {
            return ResponseEntity.ok(logEntryService.getByLevel(level));
        } else {
            return ResponseEntity.ok(logEntryService.getAll());
        }
    }
}

