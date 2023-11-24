package ua.training.microservicepatterns.upload.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import ua.training.microservicepatterns.upload.service.MetadataService;

@Profile("!local")
@EnableBinding(EventCommunications.class)
@RequiredArgsConstructor
public class StorageEventListener {

    private final ObjectMapper objectMapper;
    private final MetadataService metadataService;

    @SneakyThrows
    @StreamListener(EventCommunications.STORAGE_EVENT)
    public void handleMessage(String storageEventBody) {
        JsonNode event = objectMapper.readTree(storageEventBody);
        String filename = event.get("name").asText();
        metadataService.storeMetadataByFilename(filename);
    }
}
