package com.bookmyfood.orderservice.service;

import com.bookmyfood.orderservice.entity.CartItem;
import com.bookmyfood.orderservice.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartItemRepository repo;

    public CartService(CartItemRepository repo) {
        this.repo = repo;
    }

    public CartItem add(Long userId, Long productId, int qty) {
        CartItem ci = new CartItem();
        ci.setUserId(userId);
        ci.setProductId(productId);
        ci.setQuantity(qty);
        return repo.save(ci);
    }

    public void delete(Long itemId) {
        repo.deleteById(itemId);
    }

    public CartItem update(Long itemId, int qty) {
        CartItem ci = repo.findById(itemId).orElseThrow();
        ci.setQuantity(qty);
        return repo.save(ci);
    }

    public List<CartItem> list(Long userId) {
        return repo.findByUserId(userId);
    }
}
