package com.bookmyfood.paymentservice.service;

import com.bookmyfood.paymentservice.config.KafkaConfig;
import com.bookmyfood.paymentservice.entity.Payment;
import com.bookmyfood.paymentservice.entity.PaymentStatus;
import com.bookmyfood.paymentservice.event.OrderEvent;
import com.bookmyfood.paymentservice.event.PaymentEvent;
import com.bookmyfood.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderEventConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);
    
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;
    
    public OrderEventConsumer(PaymentService paymentService, 
                            PaymentRepository paymentRepository,
                            PaymentEventPublisher paymentEventPublisher) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
        this.paymentEventPublisher = paymentEventPublisher;
    }
    
    @KafkaListener(
        topics = KafkaConfig.ORDER_EVENTS_TOPIC,
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "orderKafkaListenerContainerFactory"
    )
    @Transactional
    public void handleOrderCreated(@Payload OrderEvent orderEvent) {
        try {
            log.info("Received order event: {}", orderEvent);
            
            // Create a payment record
            Payment payment = new Payment();
            payment.setOrderId(orderEvent.getOrderId());
            payment.setUserId(orderEvent.getUserId());
            payment.setPaymentAmount(orderEvent.getTotalAmount());
            payment.setPaymentStatus(PaymentStatus.PENDING);
            payment.setPaymentMethod("CREDIT_CARD"); // Default, can be overridden
            payment.setTransactionId("TXN_" + UUID.randomUUID().toString().substring(0, 10).toUpperCase());
            payment.setPaymentTimestamp(LocalDateTime.now());
            
            // Save the payment
            payment = paymentRepository.save(payment);
            log.info("Created payment record for order: {}", orderEvent.getOrderId());
            
            // Process the payment (simulated)
            boolean paymentSuccess = processPayment(payment);
            
            // Update payment status
            payment.setPaymentStatus(paymentSuccess ? PaymentStatus.COMPLETED : PaymentStatus.FAILED);
            paymentRepository.save(payment);
            
            // Publish payment status event
            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrderId(orderEvent.getOrderId());
            paymentEvent.setUserId(orderEvent.getUserId());
            paymentEvent.setPaymentId(payment.getId().toString());
            paymentEvent.setAmount(payment.getPaymentAmount());
            paymentEvent.setStatus(paymentSuccess ? 
                PaymentEvent.PaymentStatus.PAYMENT_COMPLETED : 
                PaymentEvent.PaymentStatus.PAYMENT_FAILED);
            paymentEvent.setMessage(paymentSuccess ? "Payment processed successfully" : "Payment processing failed");
            paymentEvent.setPaymentMethod(payment.getPaymentMethod());
            paymentEvent.setTransactionId(payment.getTransactionId());
            
            paymentEventPublisher.publishPaymentProcessed(paymentEvent);
            
        } catch (Exception e) {
            log.error("Error processing order event: {}", orderEvent, e);
            // TODO: Implement dead letter queue or retry mechanism
        }
    }
    
    private boolean processPayment(Payment payment) {
        // Simulate payment processing
        try {
            // In a real application, this would integrate with a payment gateway
            Thread.sleep(1000); // Simulate processing time
            return true; // 80% success rate simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
