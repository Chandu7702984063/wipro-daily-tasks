package com.bookmyfood.foodservice.controller;

import com.bookmyfood.foodservice.entity.FoodItem;
import com.bookmyfood.foodservice.repository.FoodRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodRepository repo;

    public FoodController(FoodRepository repo) { this.repo = repo; }

    @GetMapping
    public List<FoodItem> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public FoodItem one(@PathVariable Long id) { return repo.findById(id).orElse(null); }

    @PostMapping
    public FoodItem add(@RequestBody FoodItem f) { return repo.save(f); }

    @PutMapping("/{id}")
    public FoodItem update(@PathVariable Long id, @RequestBody FoodItem f) {
        f.setId(id); return repo.save(f);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
