package org.ai.produitservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "topic-1", groupId = "group1")
    public void listenGroup1(String message) {
        System.out.println("Received Message in group1: " + message);
    }

    @KafkaListener(topics = "topic-2", groupId = "group2")
    public void listenGroup2(String message) {
        System.out.println("Received Message in group2: " + message);
    }
}