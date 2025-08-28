package com.example.productapi.controller;

import com.example.productapi.model.Order;
import com.example.productapi.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/place/{productId}")
    public Order placeOrder(@PathVariable Long productId, @RequestParam int qty) {
        return service.placeOrder(productId, qty);
    }

    @GetMapping("/history")
    public List<Order> getHistory() {
        return service.getOrderHistory();
    }
}
