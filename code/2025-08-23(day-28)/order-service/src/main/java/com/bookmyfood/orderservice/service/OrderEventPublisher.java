package com.bookmyfood.orderservice.service;

import com.bookmyfood.orderservice.config.KafkaConfig;
import com.bookmyfood.orderservice.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderEventPublisher {
    
    private static final Logger log = LoggerFactory.getLogger(OrderEventPublisher.class);
    
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    public OrderEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void publishOrderCreated(OrderEvent event) {
        try {
            event.setEventId(UUID.randomUUID().toString());
            event.setStatus(OrderEvent.OrderStatus.CREATED);
            event.setTimestamp(java.time.LocalDateTime.now().toString());
            
            log.info("Publishing order created event: {}", event);
            
            CompletableFuture<SendResult<String, Object>> future = 
                kafkaTemplate.send(KafkaConfig.ORDER_EVENTS_TOPIC, event.getOrderId(), event);
                
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Successfully sent order created event for order: {}", event.getOrderId());
                } else {
                    log.error("Failed to send order created event for order: {} - {}", 
                        event.getOrderId(), ex.getMessage(), ex);
                }
            });
        } catch (Exception e) {
            log.error("Unexpected error while publishing order created event: {}", event, e);
            throw new RuntimeException("Failed to publish order created event", e);
        }
    }
    
    public void updateOrderStatus(String orderId, OrderEvent.OrderStatus status) {
        try {
            OrderEvent event = new OrderEvent();
            event.setEventId(UUID.randomUUID().toString());
            event.setOrderId(orderId);
            event.setStatus(status);
            event.setTimestamp(java.time.LocalDateTime.now().toString());
            
            log.info("Publishing order status update: {} for order: {}", status, orderId);
            
            kafkaTemplate.send(KafkaConfig.ORDER_EVENTS_TOPIC, orderId, event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Successfully updated order status to {} for order: {}", status, orderId);
                    } else {
                        log.error("Failed to update order status for order: {} - {}", 
                            orderId, ex.getMessage(), ex);
                    }
                });
        } catch (Exception e) {
            log.error("Unexpected error while updating order status for order: {}", orderId, e);
            throw new RuntimeException("Failed to update order status", e);
        }
    }
}
