package com.example.taskflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "user_id")
    private Long userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

