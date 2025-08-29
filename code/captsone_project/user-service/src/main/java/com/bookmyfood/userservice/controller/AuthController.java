package com.bookmyfood.userservice.controller;

import com.bookmyfood.userservice.entity.UserEntity;
import com.bookmyfood.userservice.model.AuthRequest;
import com.bookmyfood.userservice.model.AuthResponse;
import com.bookmyfood.userservice.service.UserService;
import com.bookmyfood.common.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        UserEntity u = userService.register(req.getUsername(), req.getPassword(), req.getRole());
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        return userService.findByUsername(req.getUsername())
                .map(u -> {
                    if (userService.checkPassword(u, req.getPassword())) {
                        String token = JwtUtil.generateToken(u.getUsername(), u.getRole());
                        return ResponseEntity.ok(new AuthResponse(token));
                    }
                    return ResponseEntity.status(401).body(java.util.Map.of("error", "Invalid credentials"));
                })
                .orElse(ResponseEntity.status(401).body(java.util.Map.of("error", "User not found")));
    }
}
