package com.example.letsgo.controller;

import com.example.letsgo.dto.VehicleMovementDTO;
import com.example.letsgo.entity.VehicleMovement;
import com.example.letsgo.service.VehicleMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/move")
public class VehicleMovementController {

    @Autowired
    private VehicleMovementService service;

    @PostMapping
    public VehicleMovement move(@RequestBody VehicleMovementDTO dto) {
        return service.saveMovement(dto);
    }
}
