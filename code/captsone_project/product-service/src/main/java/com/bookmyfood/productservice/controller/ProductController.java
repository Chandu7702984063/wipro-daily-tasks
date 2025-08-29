package com.bookmyfood.productservice.controller;

import com.bookmyfood.productservice.entity.Product;
import com.bookmyfood.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Product API")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @Operation(summary="List products")
    @GetMapping
    public ResponseEntity<?> list() { return ResponseEntity.ok(service.list()); }

    @Operation(summary="Get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Product p = service.get(id);
        return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @Operation(summary="Create product (ADMIN)")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product p) { return ResponseEntity.ok(service.save(p)); }

    @Operation(summary="Update product (ADMIN)")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Product p) { return ResponseEntity.ok(service.save(p)); }

    @Operation(summary="Delete product (ADMIN)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.ok().build(); }

    // endpoints for order-service
    @PostMapping("/{id}/reserve")
    public ResponseEntity<?> reserve(@PathVariable Long id, @RequestParam int qty) {
        return service.reserve(id, qty) ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body(java.util.Map.of("error","Insufficient quantity"));
    }

    @PostMapping("/{id}/release")
    public ResponseEntity<?> release(@PathVariable Long id, @RequestParam int qty) {
        service.release(id, qty);
        return ResponseEntity.ok().build();
    }
}
