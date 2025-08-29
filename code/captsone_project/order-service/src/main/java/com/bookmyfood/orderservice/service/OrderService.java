package com.bookmyfood.orderservice.service;

import com.bookmyfood.orderservice.client.ProductClient;
import com.bookmyfood.orderservice.entity.CartItem;
import com.bookmyfood.orderservice.entity.OrderEntity;
import com.bookmyfood.orderservice.entity.OrderItem;
import com.bookmyfood.orderservice.repository.CartItemRepository;
import com.bookmyfood.orderservice.repository.OrderItemRepository;
import com.bookmyfood.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final CartItemRepository cartRepo;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepo, OrderItemRepository itemRepo,
            CartItemRepository cartRepo, ProductClient productClient) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
        this.productClient = productClient;
    }

    @Transactional
    public OrderEntity createOrder(Long userId) {
        List<CartItem> items = cartRepo.findByUserId(userId);
        if (items.isEmpty())
            throw new RuntimeException("Cart is empty");

        // Reserve inventory
        for (CartItem ci : items) {
            productClient.reserve(ci.getProductId(), ci.getQuantity());
        }

        // Create order
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setStatus("CREATED");
        order.setTotalAmount(0.0);
        order.setCreatedAt(Instant.now());
        order = orderRepo.save(order);

        double total = 0.0;
        for (CartItem ci : items) {
            OrderItem oi = new OrderItem();
            oi.setOrderId(order.getId());
            oi.setProductId(ci.getProductId());
            oi.setQuantity(ci.getQuantity());
            oi.setPriceAtPurchase(0.0); // price lookup omitted for brevity
            itemRepo.save(oi);
            total += 0.0; // update with actual price if available
        }

        order.setTotalAmount(total);
        orderRepo.save(order);

        // Clear cart
        cartRepo.deleteAll(items);

        return order;
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        OrderEntity order = orderRepo.findById(orderId).orElseThrow();
        order.setStatus("CANCELLED");
        orderRepo.save(order);

        List<OrderItem> items = itemRepo.findByOrderId(orderId);
        for (OrderItem oi : items) {
            productClient.release(oi.getProductId(), oi.getQuantity());
        }
    }

    public List<OrderEntity> listAll() {
        return orderRepo.findAll();
    }

    public List<OrderEntity> listByUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public OrderEntity get(Long id) {
        return orderRepo.findById(id).orElse(null);
    }
}
