package com.bookmyfood.paymentservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    
    public static final String PAYMENT_TOPIC = "payment_events";
    public static final String PAYMENT_STATUS_TOPIC = "payment_status_events";
    
    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name(PAYMENT_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
    
    @Bean
    public NewTopic paymentStatusTopic() {
        return TopicBuilder.name(PAYMENT_STATUS_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
