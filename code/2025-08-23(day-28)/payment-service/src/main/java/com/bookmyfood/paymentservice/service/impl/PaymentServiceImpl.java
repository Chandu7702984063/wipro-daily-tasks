package com.bookmyfood.paymentservice.service.impl;

import com.bookmyfood.paymentservice.entity.Payment;
import com.bookmyfood.paymentservice.repository.PaymentRepository;
import com.bookmyfood.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment processPayment(Payment payment) {
        // Generate a transaction ID
        payment.setTransactionId("TXN" + UUID.randomUUID().toString().replace("-", "").substring(0, 15));
        payment.setPaymentTimestamp(LocalDateTime.now().toString());
        
        // In a real application, integrate with a payment gateway here
        // For now, we'll simulate a successful payment
        payment.setPaymentStatus(1); // 1 = Completed
        
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public Payment getPaymentByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order: " + orderId));
    }

    @Override
    public List<Payment> getPaymentsByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }

    @Override
    public List<Payment> getPaymentsByStatus(Integer status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    @Override
    public Payment updatePaymentStatus(Long paymentId, Integer status) {
        Payment payment = getPaymentById(paymentId);
        payment.setPaymentStatus(status);
        return paymentRepository.save(payment);
    }

    @Override
    public void processRefund(String orderId) {
        Payment payment = getPaymentByOrderId(orderId);
        // In a real application, integrate with payment gateway for refund
        payment.setPaymentStatus(3); // 3 = Refunded
        paymentRepository.save(payment);
    }
}
