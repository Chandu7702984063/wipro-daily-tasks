package com.example.letsgo.service;

import com.example.letsgo.dto.VehicleMovementDTO;
import com.example.letsgo.entity.VehicleMovement;
import com.example.letsgo.repository.VehicleMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMovementService {

    @Autowired
    private VehicleMovementRepository repository;

    public VehicleMovement saveMovement(VehicleMovementDTO dto) {
        VehicleMovement vm = new VehicleMovement();
        vm.setVehId(dto.getVehId());
        vm.setLat(dto.getLat());
        vm.setLongitude(dto.getLongitude());
        return repository.save(vm);
    }
}
