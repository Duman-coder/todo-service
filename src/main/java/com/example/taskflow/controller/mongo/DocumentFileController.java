package com.example.taskflow.controller;

import com.example.taskflow.model.mongo.DocumentFile;
import com.example.taskflow.service.DocumentFileService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RestController
@RequestMapping("/tasks/{taskId}/documents")
@RequiredArgsConstructor
public class DocumentFileController {

    private final DocumentFileService documentFileService;
    private final GridFsTemplate gridFsTemplate;

    @PostMapping
    public ResponseEntity<DocumentFile> upload(@PathVariable Long taskId,
                                               @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(documentFileService.uploadFile(taskId, file));
    }

    @GetMapping
    public ResponseEntity<List<DocumentFile>> getAll(@PathVariable Long taskId) {
        return ResponseEntity.ok(documentFileService.getFilesByTaskId(taskId));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("id") String id) throws IOException {
        GridFsResource resource = gridFsTemplate.getResource(
                gridFsTemplate.findOne(query(where("_id").is(new ObjectId(id))))
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType(resource.getContentType()))
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        documentFileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
