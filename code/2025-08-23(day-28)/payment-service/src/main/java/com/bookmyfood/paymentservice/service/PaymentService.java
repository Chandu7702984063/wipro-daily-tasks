package com.bookmyfood.paymentservice.service;

import com.bookmyfood.paymentservice.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Payment getPaymentById(Long id);
    Payment getPaymentByOrderId(String orderId);
    List<Payment> getPaymentsByUserId(String userId);
    List<Payment> getPaymentsByStatus(Integer status);
    Payment updatePaymentStatus(Long paymentId, Integer status);
    void processRefund(String orderId);
}
