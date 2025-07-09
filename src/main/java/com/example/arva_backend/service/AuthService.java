package com.example.arva_backend.service;


import com.example.arva_backend.model.dto.UserDTO;
import com.example.arva_backend.model.entity.User;
import com.example.arva_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUser(String email){
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        return user;
    }

    public User createUser(UserDTO userDTO){

//        int countEmail = userRepository.countByEmail(userDTO.getEmail());
//
//        if(countEmail > 0){
//            throw new RuntimeException("Email is already taken.");
//        }

        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setCreatedAt(new Date());
        newUser.setUpdatedAt(new Date());

        userRepository.save(newUser);

        return newUser;

    }
}
