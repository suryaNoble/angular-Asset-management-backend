package com.example.help_desk.service.impl;

import com.example.help_desk.dto.LoginRequestDTO;
import com.example.help_desk.dto.LoginResponseDTO;
import com.example.help_desk.dto.RegisterRequestDTO;
import com.example.help_desk.entity.Role;
import com.example.help_desk.entity.User;
import com.example.help_desk.repository.RoleRepository;
import com.example.help_desk.repository.UserRepository;
import com.example.help_desk.security.JwtUtil;
import com.example.help_desk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    
//
//    @Autowired
//    private JwtUtil jwtUtil;
	
	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    // Removed PasswordEncoder from constructor completely
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequestDTO request) {
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow();

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(token);
    }
}