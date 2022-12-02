package com.example.authentication.kafka;

import com.example.authentication.payload.request.ProfileProducerRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final KafkaTemplate<String, ProfileProducerRequest> kafkaTemplate;

    public Producer(KafkaTemplate<String, ProfileProducerRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ProfileProducerRequest profileProducerRequest) {
        this.kafkaTemplate.send("PROFILE", profileProducerRequest);
    }
}
