package com.bookmyfood.productservice.service;

import com.bookmyfood.productservice.entity.Product;
import com.bookmyfood.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> list() { return repo.findAll(); }
    public Product get(Long id) { return repo.findById(id).orElse(null); }
    public Product save(Product p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }

    public boolean reserve(Long id, int qty) {
        Product p = repo.findById(id).orElse(null);
        if (p == null || p.getQuantity() < qty) return false;
        p.setQuantity(p.getQuantity() - qty);
        repo.save(p);
        return true;
    }

    public void release(Long id, int qty) {
        Product p = repo.findById(id).orElse(null);
        if (p != null) {
            p.setQuantity(p.getQuantity() + qty);
            repo.save(p);
        }
    }
}
