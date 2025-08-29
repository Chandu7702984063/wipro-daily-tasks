package com.bookmyfood.orderservice.controller;

import com.bookmyfood.orderservice.entity.OrderEntity;
import com.bookmyfood.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Order API")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam Long userId) {
        OrderEntity order = service.createOrder(userId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long orderId) {
        service.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> listAll() { return ResponseEntity.ok(service.listAll()); }

    @GetMapping("/{userId}")
    public ResponseEntity<?> listByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.listByUser(userId));
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<?> details(@PathVariable Long orderId) {
        OrderEntity o = service.get(orderId);
        return o == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(o);
    }
}
