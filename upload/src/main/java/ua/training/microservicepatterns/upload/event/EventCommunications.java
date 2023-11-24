package ua.training.microservicepatterns.upload.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventCommunications {
    String STORAGE_EVENT = "eventTopic";

    @Input(STORAGE_EVENT)
    SubscribableChannel storageEvent();
}
