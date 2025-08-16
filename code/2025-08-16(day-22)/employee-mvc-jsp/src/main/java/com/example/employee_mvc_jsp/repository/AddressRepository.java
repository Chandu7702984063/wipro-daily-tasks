package com.example.employee_mvc_jsp.repository;

import com.example.employee_mvc_jsp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> { }
