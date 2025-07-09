package com.example.arva_backend.controller;

import com.example.arva_backend.model.dto.UserDTO;
import com.example.arva_backend.model.entity.User;
import com.example.arva_backend.repository.UserRepository;
import com.example.arva_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO){
        try{
            User savedUser = authService.createUser(userDTO);

            // Remove password for response
            savedUser.setPassword(null);

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Register successfully");
            response.put("user", savedUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
