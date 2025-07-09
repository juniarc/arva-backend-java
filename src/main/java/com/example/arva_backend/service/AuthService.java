package com.example.arva_backend.service;


import com.example.arva_backend.model.entity.User;
import com.example.arva_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User findUser(String email){
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        return user;
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }
}
