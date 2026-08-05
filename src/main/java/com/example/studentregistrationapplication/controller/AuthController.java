package com.example.studentregistrationapplication.controller;

import com.example.studentregistrationapplication.model.ApiResponse;
import com.example.studentregistrationapplication.model.LoginRequest;
import com.example.studentregistrationapplication.model.User;
import com.example.studentregistrationapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = authService.login(loginRequest.getUserName(), loginRequest.getPassword());

        if (user.isPresent()) {
            ApiResponse<User> response = new ApiResponse<>(
                    true,
                    "Login successful",
                    user.get()
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<User> response = new ApiResponse<>(
                    false,
                    "Invalid username or password",
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}