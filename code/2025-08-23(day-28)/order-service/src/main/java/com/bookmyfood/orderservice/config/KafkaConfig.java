package com.bookmyfood.orderservice.config;

public class KafkaConfig {
    
    // Topics
    public static final String ORDER_EVENTS_TOPIC = "order_events";
    public static final String PAYMENT_EVENTS_TOPIC = "payment_events";
    
    // Consumer groups
    public static final String ORDER_SERVICE_GROUP = "order-service-group";
    
    private KafkaConfig() {
        // Private constructor to prevent instantiation
    }
}
