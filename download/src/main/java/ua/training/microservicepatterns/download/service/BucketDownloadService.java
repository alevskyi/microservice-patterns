package ua.training.microservicepatterns.download.service;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Profile("!local")
@RequiredArgsConstructor
public class BucketDownloadService implements DownloadService {

    private final Storage storage;
    @Value("${cloud.uploadBucket}")
    private String bucketName;

    @Override
    public Resource downloadFile(String filename) {
        Bucket bucket = storage.get(bucketName);
        return new ByteArrayResource(bucket.get(filename).getContent());
    }
}
