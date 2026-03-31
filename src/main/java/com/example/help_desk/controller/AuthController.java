package com.example.help_desk.controller;


import com.example.help_desk.dto.LoginRequestDTO;
import com.example.help_desk.dto.LoginResponseDTO;
import com.example.help_desk.dto.RegisterRequestDTO;
import com.example.help_desk.service.AuthService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.ok(Map.of("message", "registration successful go login"));
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}