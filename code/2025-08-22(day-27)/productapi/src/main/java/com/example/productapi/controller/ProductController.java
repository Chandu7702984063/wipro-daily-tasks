package com.example.productapi.controller;

import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
