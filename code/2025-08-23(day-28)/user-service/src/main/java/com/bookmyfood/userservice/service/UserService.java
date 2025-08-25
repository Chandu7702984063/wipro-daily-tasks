package com.bookmyfood.userservice.service;

import com.bookmyfood.userservice.entity.UserEntity;
import com.bookmyfood.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) { this.repo = repo; }

    public UserEntity register(String username, String password) {
        UserEntity u = new UserEntity(username, encoder.encode(password), "USER");
        return repo.save(u);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public boolean checkPassword(UserEntity user, String raw) {
        return encoder.matches(raw, user.getPassword());
    }
}
