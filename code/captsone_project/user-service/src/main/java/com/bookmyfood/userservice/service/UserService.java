package com.bookmyfood.userservice.service;

import com.bookmyfood.userservice.entity.UserEntity;
import com.bookmyfood.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserEntity register(String username, String password, String role) {
        // Encode password
        String encodedPassword = encoder.encode(password);

        // Create and populate user entity
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(role != null ? role : "CUSTOMER");

        // Save to database
        return repo.save(user);
    }

    public boolean checkPassword(UserEntity u, String raw) {
        return encoder.matches(raw, u.getPassword());
    }

    public Optional<UserEntity> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<UserEntity> list() {
        return repo.findAll();
    }

    public Optional<UserEntity> get(Long id) {
        return repo.findById(id);
    }

    public UserEntity update(UserEntity u) {
        return repo.save(u);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
