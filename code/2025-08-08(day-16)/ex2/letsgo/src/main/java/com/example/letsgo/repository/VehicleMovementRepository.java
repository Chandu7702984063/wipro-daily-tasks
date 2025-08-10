package com.example.letsgo.repository;

import com.example.letsgo.entity.VehicleMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMovementRepository extends JpaRepository<VehicleMovement, Long> {
}
