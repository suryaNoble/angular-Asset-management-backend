package com.example.help_desk.service;

import com.example.help_desk.dto.LoginRequestDTO;
import com.example.help_desk.dto.LoginResponseDTO;
import com.example.help_desk.dto.RegisterRequestDTO;

public interface AuthService {
    void register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}