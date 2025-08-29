package com.bookmyfood.orderservice.controller;

import com.bookmyfood.orderservice.entity.CartItem;
import com.bookmyfood.orderservice.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Cart API")
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService service;
    public CartController(CartService service) { this.service = service; }

    @PostMapping("/addProd")
    public ResponseEntity<?> add(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int qty) {
        return ResponseEntity.ok(service.add(userId, productId, qty));
    }

    @DeleteMapping("/deleteProd/{itemId}")
    public ResponseEntity<?> delete(@PathVariable Long itemId) {
        service.delete(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long itemId, @RequestParam int qty) {
        return ResponseEntity.ok(service.update(itemId, qty));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> list(@PathVariable Long userId) {
        return ResponseEntity.ok(service.list(userId));
    }
}
