package com.bookmyfood.userservice.controller;

import com.bookmyfood.userservice.entity.UserEntity;
import com.bookmyfood.userservice.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<UserEntity> all() {
        return repo.findAll();
    }
}
