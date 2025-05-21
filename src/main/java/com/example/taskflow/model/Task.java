package com.example.taskflow.model;

import com.example.taskflow.model.enums.TaskPriority;
import com.example.taskflow.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDateTime deadline;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(name = "assigned_user_id")
    private UUID assignedUserId;

    @Column(name = "project_id")
    private UUID projectId;
}

