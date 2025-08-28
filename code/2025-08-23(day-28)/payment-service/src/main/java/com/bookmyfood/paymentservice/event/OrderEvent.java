package com.bookmyfood.paymentservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String eventId;
    private String orderId;
    private Long userId;
    private Long restaurantId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private String timestamp;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        private Long foodItemId;
        private String name;
        private int quantity;
        private BigDecimal price;
    }

    public enum OrderStatus {
        CREATED,
        PAYMENT_PENDING,
        PAYMENT_COMPLETED,
        PAYMENT_FAILED,
        ORDER_CONFIRMED,
        ORDER_PREPARING,
        ORDER_READY,
        ORDER_PICKED_UP,
        ORDER_DELIVERED,
        ORDER_CANCELLED
    }
}
