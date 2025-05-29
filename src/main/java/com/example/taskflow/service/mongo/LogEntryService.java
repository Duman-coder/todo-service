package com.example.taskflow.service;

import com.example.taskflow.model.mongo.LogEntry;
import com.example.taskflow.repository.mongo.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;

    public void log(String level, String message, Map<String, Object> context) {
        LogEntry logEntry = LogEntry.builder()
                .level(level)
                .message(message)
                .context(context)
                .timestamp(LocalDateTime.now())
                .build();

        logEntryRepository.save(logEntry);
    }

    public List<LogEntry> getAll() {
        return logEntryRepository.findAll();
    }

    public List<LogEntry> getByLevel(String level) {
        return logEntryRepository.findAllByLevel(level);
    }
}

