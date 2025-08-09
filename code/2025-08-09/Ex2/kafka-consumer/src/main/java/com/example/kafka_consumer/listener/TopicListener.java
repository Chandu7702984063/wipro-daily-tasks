package com.example.kafka_consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

    @KafkaListener(topics = "learn-subject", groupId = "spring-consumer-group")
    public void consume(String message) {
        System.out.println("Consumer received: " + message);
    }
}
