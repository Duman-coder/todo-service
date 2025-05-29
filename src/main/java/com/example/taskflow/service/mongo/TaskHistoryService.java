package com.example.taskflow.service;

import com.example.taskflow.model.mongo.TaskHistory;
import com.example.taskflow.repository.mongo.TaskHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;

    public void logAction(Long taskId, String action, Long performedBy, Map<String, Object> details) {
        TaskHistory history = TaskHistory.builder()
                .taskId(taskId)
                .action(action)
                .performedBy(performedBy)
                .timestamp(LocalDateTime.now())
                .details(details)
                .build();

        taskHistoryRepository.save(history);
    }

    public List<TaskHistory> getTaskHistory(Long taskId) {
        return taskHistoryRepository.findAllByTaskId(taskId);
    }
}

