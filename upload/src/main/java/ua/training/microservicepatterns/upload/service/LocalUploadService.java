package ua.training.microservicepatterns.upload.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.training.microservicepatterns.upload.rest.UploadRequest;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Profile("local")
@RequiredArgsConstructor
public class LocalUploadService implements UploadService {

    private final MetadataService metadataService;
    @Value("${local.uploadDir}")
    private String uploadDir;

    @PostConstruct
    public void validateDirectory() {
        File dir = Paths.get(uploadDir).toFile();
        if (!dir.exists()) {
            throw new RuntimeException("Upload dir not found");
        }
        if (!dir.isDirectory()) {
            throw new RuntimeException("Upload dir is not a directory");
        }
    }

    @Override
    @SneakyThrows
    public String uploadFile(UploadRequest request) {
        String filename = UUID.randomUUID().toString();
        Path destination = Paths.get(uploadDir, filename);
        request.getFile().transferTo(destination);
        metadataService.storeImmediately(filename, request);
        return filename;
    }
}
