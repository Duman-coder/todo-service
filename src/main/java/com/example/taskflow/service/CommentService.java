package com.example.taskflow.service;

import com.example.taskflow.dto.CommentRequest;
import com.example.taskflow.dto.CommentResponse;
import com.example.taskflow.exception.CommentNotFoundException;
import com.example.taskflow.model.Comment;
import com.example.taskflow.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponse create(Long taskId, CommentRequest request) {
        Comment comment = Comment.builder()
                .taskId(taskId)
                .userId(request.userId())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return toResponse(commentRepository.save(comment));
    }

    public Page<CommentResponse> getAllByTaskId(Long taskId, Pageable pageable) {
        return commentRepository.findAllByTaskId(taskId, pageable)
                .map(this::toResponse);
    }

    public void delete(Long taskId, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        if (!comment.getTaskId().equals(taskId)) {
            throw new IllegalArgumentException("Комментарий не принадлежит задаче");
        }
        commentRepository.delete(comment);
    }

    private CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getTaskId(),
                comment.getUserId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

}

