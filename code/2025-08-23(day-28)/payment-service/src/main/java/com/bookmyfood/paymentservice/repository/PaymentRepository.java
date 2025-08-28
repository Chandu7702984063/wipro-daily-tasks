package com.bookmyfood.paymentservice.repository;

import com.bookmyfood.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(String userId);
    Optional<Payment> findByOrderId(String orderId);
    List<Payment> findByPaymentStatus(Integer status);
}
