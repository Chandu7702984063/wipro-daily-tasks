package com.example.kafka_producer.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "learn-subject";

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    public String sendMessage(@RequestParam String msg) {
        kafkaTemplate.send(TOPIC, msg);
        return "Sent message: " + msg;
    }
}
