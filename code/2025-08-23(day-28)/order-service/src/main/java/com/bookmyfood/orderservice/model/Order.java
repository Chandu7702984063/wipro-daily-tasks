package com.bookmyfood.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String orderId;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Long restaurantId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items;
    
    @Column(nullable = false)
    private BigDecimal totalAmount;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    private String paymentId;
    private String transactionId;
    private String paymentStatus;
    
    @Column(nullable = false)
    private String deliveryAddress;
    
    private String deliveryInstructions;
    
    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();
    
    private LocalDateTime deliveryDate;
    
    @Column(nullable = false)
    private String customerName;
    
    @Column(nullable = false)
    private String customerPhone;
    
    private String customerEmail;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    public enum OrderStatus {
        CREATED,
        PAYMENT_PENDING,
        PAYMENT_CONFIRMED,
        PAYMENT_FAILED,
        ORDER_CONFIRMED,
        ORDER_PREPARING,
        ORDER_READY,
        ORDER_PICKED_UP,
        ORDER_DELIVERED,
        ORDER_CANCELLED,
        REFUNDED
    }
    
    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        NET_BANKING,
        UPI,
        CASH_ON_DELIVERY,
        WALLET
    }
}
