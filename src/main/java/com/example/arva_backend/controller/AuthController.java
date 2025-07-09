package com.example.arva_backend.controller;

import com.example.arva_backend.model.entity.User;
import com.example.arva_backend.repository.UserRepository;
import com.example.arva_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> findUser(@RequestParam @Valid String email){
        try{
            User user = authService.findUser(email);
            System.out.println(email);
            System.out.println(user);

            return ResponseEntity.ok(user);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
