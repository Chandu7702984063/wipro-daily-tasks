package com.bookmyfood.paymentservice.controller;

import com.bookmyfood.paymentservice.entity.Payment;
import com.bookmyfood.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.processPayment(payment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }

    @PutMapping("/{paymentId}/status/{status}")
    public ResponseEntity<Payment> updatePaymentStatus(
            @PathVariable Long paymentId,
            @PathVariable Integer status) {
        return ResponseEntity.ok(paymentService.updatePaymentStatus(paymentId, status));
    }

    @PostMapping("/refund/{orderId}")
    public ResponseEntity<Void> processRefund(@PathVariable String orderId) {
        paymentService.processRefund(orderId);
        return ResponseEntity.ok().build();
    }
}
