package com.bookmyfood.orderservice.controller;

import com.bookmyfood.orderservice.dto.CreateOrderRequest;
import com.bookmyfood.orderservice.dto.OrderResponse;
import com.bookmyfood.orderservice.event.OrderEvent;
import com.bookmyfood.orderservice.service.OrderEventPublisher;
import com.bookmyfood.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderEventPublisher orderEventPublisher;

    public OrderController(OrderService orderService, OrderEventPublisher orderEventPublisher) {
        this.orderService = orderService;
        this.orderEventPublisher = orderEventPublisher;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest req) {
        OrderResponse response = orderService.create(req);
        
        // Publish order created event
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(response.getOrderId().toString());
        orderEvent.setUserId(response.getUserId());
        orderEvent.setRestaurantId(response.getRestaurantId());
        orderEvent.setTotalAmount(response.getTotalAmount());
        orderEvent.setTimestamp(java.time.LocalDateTime.now().toString());
        
        // Convert order items to event items
        orderEvent.setItems(
            response.getItems().stream()
                .map(item -> new OrderEvent.OrderItem(
                    item.getFoodItemId(),
                    item.getName(),
                    item.getQuantity(),
                    item.getPrice()
                ))
                .toList()
        );
        
        orderEventPublisher.publishOrderCreated(orderEvent);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable String id) {
        OrderResponse response = orderService.getById(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> listByUser(@PathVariable String userId) {
        return orderService.listByUser(userId);
    }
}
