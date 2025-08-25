package com.bookmyfood.foodservice.repository;

import com.bookmyfood.foodservice.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodItem, Long> {
}
