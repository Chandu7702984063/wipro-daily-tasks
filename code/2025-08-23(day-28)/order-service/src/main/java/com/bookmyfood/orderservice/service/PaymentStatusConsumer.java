package com.bookmyfood.orderservice.service;

import com.bookmyfood.orderservice.event.PaymentEvent;
import com.bookmyfood.orderservice.model.Order;
import com.bookmyfood.orderservice.model.OrderStatus;
import com.bookmyfood.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentStatusConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(PaymentStatusConsumer.class);
    
    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;
    
    public PaymentStatusConsumer(OrderRepository orderRepository, 
                               OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }
    
    @KafkaListener(
        topics = "${spring.kafka.topic.payment-events}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "paymentKafkaListenerContainerFactory"
    )
    @Transactional
    public void handlePaymentStatusUpdate(@Payload PaymentEvent paymentEvent) {
        try {
            log.info("Received payment status update: {}", paymentEvent);
            
            // Find the order by ID
            Optional<Order> orderOpt = orderRepository.findByOrderId(paymentEvent.getOrderId());
            
            if (orderOpt.isEmpty()) {
                log.warn("Order not found for payment update: {}", paymentEvent.getOrderId());
                return;
            }
            
            Order order = orderOpt.get();
            
            // Update order status based on payment status
            switch (paymentEvent.getStatus()) {
                case PAYMENT_COMPLETED:
                    order.setStatus(OrderStatus.PAYMENT_CONFIRMED);
                    order.setPaymentId(paymentEvent.getPaymentId());
                    order.setTransactionId(paymentEvent.getTransactionId());
                    break;
                    
                case PAYMENT_FAILED:
                    order.setStatus(OrderStatus.PAYMENT_FAILED);
                    order.setPaymentStatus("FAILED");
                    break;
                    
                case PAYMENT_REFUNDED:
                    order.setStatus(OrderStatus.REFUNDED);
                    order.setPaymentStatus("REFUNDED");
                    break;
                    
                default:
                    log.warn("Unhandled payment status: {}", paymentEvent.getStatus());
                    return;
            }
            
            // Save the updated order
            orderRepository.save(order);
            log.info("Updated order {} with payment status: {}", 
                    order.getOrderId(), paymentEvent.getStatus());
            
            // Publish order updated event if needed
            // orderEventPublisher.publishOrderUpdated(...);
            
        } catch (Exception e) {
            log.error("Error processing payment status update: {}", paymentEvent, e);
            // TODO: Implement dead letter queue or retry mechanism
        }
    }
}
