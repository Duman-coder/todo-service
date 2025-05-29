package com.example.taskflow.service;

import com.example.taskflow.model.mongo.DocumentFile;
import com.example.taskflow.repository.mongo.DocumentFileRepository;
import com.mongodb.client.gridfs.GridFSBucket;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentFileService {

    private final DocumentFileRepository documentFileRepository;
    private final GridFsTemplate gridFsTemplate;
    private final GridFSBucket gridFSBucket;

    public DocumentFile uploadFile(Long taskId, MultipartFile file) throws IOException {
        // MIME-проверка
        String contentType = file.getContentType();
        if (!List.of("application/pdf", "image/png", "image/jpeg").contains(contentType)) {
            throw new IllegalArgumentException("Недопустимый тип файла: " + contentType);
        }

        // Ограничение размера — 10MB
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("Размер файла превышает 10 МБ");
        }

        // Сохраняем в GridFS
        ObjectId fileId = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType()
        );

        // Сохраняем метаинформацию
        DocumentFile documentFile = DocumentFile.builder()
                .id(fileId.toHexString())
                .taskId(taskId)
                .filename(file.getOriginalFilename())
                .fileType(contentType)
                .size(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .build();

        return documentFileRepository.save(documentFile);
    }

    public List<DocumentFile> getFilesByTaskId(Long taskId) {
        return documentFileRepository.findAllByTaskId(taskId);
    }

    public Optional<DocumentFile> getById(String id) {
        return documentFileRepository.findById(id);
    }

    public void delete(String id) {
        documentFileRepository.deleteById(id);
        gridFSBucket.delete(new ObjectId(id));
    }
}

