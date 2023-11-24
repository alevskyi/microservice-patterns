package ua.training.microservicepatterns.upload.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.training.microservicepatterns.upload.rest.UploadRequest;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private static final String SEARCH_SERVICE_URL = "http://search/";

    private final Map<String, UploadRequest> targetFilenameMetadataMap = Collections.synchronizedMap(new HashMap<>());
    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;
    private final ObjectMapper objectMapper;

    public void storeImmediately(String filename, UploadRequest request) {
        storeMetadata(filename, request);
    }

    public void storeDelayed(String filename, UploadRequest request) {
        targetFilenameMetadataMap.put(filename, request);
    }

    public void storeMetadataByFilename(String filename) {
        if (!targetFilenameMetadataMap.containsKey(filename)) {
            throw new IllegalStateException("Metadata not found for filename: " + filename);
        }
        storeMetadata(filename, targetFilenameMetadataMap.get(filename));
    }

    @SneakyThrows
    private void storeMetadata(String filename, UploadRequest request) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("description", request.getDescription());
        payload.put("tags", request.getTags());
        payload.put("filename", filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> body = new HttpEntity<>(objectMapper.writeValueAsString(payload), headers);
        CircuitBreaker.decorateSupplier(circuitBreaker, () -> restTemplate.postForLocation(SEARCH_SERVICE_URL + "save", body)).get();
    }
}
