package com.example.help_desk.controller;


import com.example.help_desk.dto.LoginRequestDTO;
import com.example.help_desk.dto.LoginResponseDTO;
import com.example.help_desk.dto.RegisterRequestDTO;
import com.example.help_desk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}