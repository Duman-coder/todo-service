package com.example.taskflow.service;

import com.example.taskflow.dto.TaskRequest;
import com.example.taskflow.dto.TaskResponse;
import com.example.taskflow.exception.TaskNotFoundException;
import com.example.taskflow.model.Task;
import com.example.taskflow.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(TaskRequest request) {
        Task task = toEntity(request);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return toResponse(taskRepository.save(task));
    }

    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(this::toResponse);
    }

    public TaskResponse getTaskById(Long id) {
        return toResponse(getByIdOrThrow(id));
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = getByIdOrThrow(id);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDeadline(request.deadline());
        task.setAssignedUserId(request.assignedUserId());
        task.setProjectId(request.projectId());
        task.setUpdatedAt(LocalDateTime.now());
        return toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.delete(getByIdOrThrow(id));
    }

    private Task getByIdOrThrow(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    private Task toEntity(TaskRequest dto) {
        return Task.builder()
                .title(dto.title())
                .description(dto.description())
                .status(dto.status())
                .priority(dto.priority())
                .deadline(dto.deadline())
                .assignedUserId(dto.assignedUserId())
                .projectId(dto.projectId())
                .build();
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDeadline(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getAssignedUserId(),
                task.getProjectId()
        );
    }

}

