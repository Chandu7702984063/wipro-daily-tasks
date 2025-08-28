package com.bookmyfood.paymentservice.kafka;

import com.bookmyfood.paymentservice.entity.Payment;
import com.bookmyfood.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class PaymentEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentEventConsumer.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    @Value("${kafka.topic.payment-processing}")
    private String paymentProcessingTopic;

    @Value("${kafka.topic.payment-status}")
    private String paymentStatusTopic;

    @KafkaListener(topics = "${kafka.topic.payment-processing}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "paymentKafkaListenerContainerFactory")
    public void consumePaymentEvent(Payment payment) {
        try {
            logger.info("Received payment event for order: {}", payment.getOrderId());

            // Process the payment
            Payment processedPayment = paymentService.processPayment(payment);

            // Send payment status update
            kafkaTemplate.send(paymentStatusTopic, processedPayment.getOrderId(), processedPayment);

            logger.info("Processed payment for order: {}", payment.getOrderId());

        } catch (Exception e) {
            logger.error("Error processing payment for order: " + payment.getOrderId(), e);
            // In a real application, implement retry logic or dead-letter queue here
        }
    }
}
