package com.example.productapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName; // store product name
    private Integer purchasedQty;
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(String productName, Integer purchasedQty, LocalDateTime orderDate) {
        this.productName = productName;
        this.purchasedQty = purchasedQty;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPurchasedQty() {
        return purchasedQty;
    }

    public void setPurchasedQty(Integer purchasedQty) {
        this.purchasedQty = purchasedQty;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

}
