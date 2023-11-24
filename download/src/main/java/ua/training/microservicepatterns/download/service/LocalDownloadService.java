package ua.training.microservicepatterns.download.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
@Profile("local")
@RequiredArgsConstructor
public class LocalDownloadService implements DownloadService {

    @Value("${local.uploadDir}")
    private String uploadDir;

    @Override
    public Resource downloadFile(String filename) {
        return new FileSystemResource(Paths.get(uploadDir, filename));
    }
}
