package ua.training.microservicepatterns.upload.service;

import ua.training.microservicepatterns.upload.rest.UploadRequest;

public interface UploadService {

    String uploadFile(UploadRequest request);
}
