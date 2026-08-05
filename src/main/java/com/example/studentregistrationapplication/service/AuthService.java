package com.example.studentregistrationapplication.service;

import com.example.studentregistrationapplication.model.User;
import com.example.studentregistrationapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
}