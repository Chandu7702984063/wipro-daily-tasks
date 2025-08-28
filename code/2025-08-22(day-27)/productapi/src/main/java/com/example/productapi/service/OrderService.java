package com.example.productapi.service;

import com.example.productapi.model.Order;
import com.example.productapi.model.Product;
import com.example.productapi.repository.OrderRepository;
import com.example.productapi.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;

    public OrderService(ProductRepository productRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order placeOrder(Long productId, int purchaseQty) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (purchaseQty <= 0) {
            throw new RuntimeException("Purchase quantity must be greater than 0");
        }

        if (product.getQty() < purchaseQty) {
            throw new RuntimeException("Not enough stock available!");
        }

        // reduce stock
        product.setQty(product.getQty() - purchaseQty);
        productRepo.save(product);

        // save order
        Order order = new Order(product.getName(), purchaseQty, LocalDateTime.now());
        return orderRepo.save(order);
    }

    public List<Order> getOrderHistory() {
        return orderRepo.findAll();
    }
}
