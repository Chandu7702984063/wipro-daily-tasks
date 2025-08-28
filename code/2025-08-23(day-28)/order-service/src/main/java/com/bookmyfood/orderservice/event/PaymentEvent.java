package com.bookmyfood.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String eventId;
    private String orderId;
    private Long userId;
    private String paymentId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String message;
    private String timestamp;
    private String paymentMethod;
    private String transactionId;

    public enum PaymentStatus {
        PAYMENT_INITIATED,
        PAYMENT_PROCESSING,
        PAYMENT_COMPLETED,
        PAYMENT_FAILED,
        PAYMENT_REFUND_INITIATED,
        PAYMENT_REFUNDED,
        PAYMENT_REFUND_FAILED
    }
}
