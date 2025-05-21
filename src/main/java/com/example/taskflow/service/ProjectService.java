package com.example.taskflow.service;

import com.example.taskflow.dto.ProjectRequest;
import com.example.taskflow.dto.ProjectResponse;
import com.example.taskflow.exception.ProjectNotFoundException;
import com.example.taskflow.model.Project;
import com.example.taskflow.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectResponse create(ProjectRequest request) {
        Project project = Project.builder()
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .ownerId(request.ownerId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return toResponse(projectRepository.save(project));
    }

    public Page<ProjectResponse> getAll(Pageable pageable) {
        return projectRepository.findAll(pageable).map(this::toResponse);
    }

    public ProjectResponse getById(Long id) {
        return toResponse(projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id)));
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        project.setName(request.name());
        project.setDescription(request.description());
        project.setStatus(request.status());
        project.setOwnerId(request.ownerId());
        project.setUpdatedAt(LocalDateTime.now());
        return toResponse(projectRepository.save(project));
    }

    public void delete(Long id) {
        projectRepository.delete(projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id)));
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getOwnerId(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

}

