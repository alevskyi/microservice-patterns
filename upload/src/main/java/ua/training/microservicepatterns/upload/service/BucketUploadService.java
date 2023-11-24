package ua.training.microservicepatterns.upload.service;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.training.microservicepatterns.upload.rest.UploadRequest;

@Service
@Profile("!local")
@RequiredArgsConstructor
public class BucketUploadService implements UploadService {

    private final Storage storage;
    private final MetadataService metadataService;
    @Value("${cloud.uploadBucket}")
    private String bucketName;

    @SneakyThrows
    @Override
    public String uploadFile(UploadRequest request) {
        Bucket bucket = storage.get(bucketName);
        String filename = UUID.randomUUID().toString();
        bucket.create(filename, request.getFile().getBytes());
        metadataService.storeDelayed(filename, request);
        return filename;
    }
}
