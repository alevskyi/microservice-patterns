package ua.training.microservicepatterns.download.service;

import org.springframework.core.io.Resource;

public interface DownloadService {

    Resource downloadFile(String filename);
}
