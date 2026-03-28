//package com.example.help_desk.service.impl;
//
//import com.example.help_desk.dto.LoginRequestDTO;
//import com.example.help_desk.dto.LoginResponseDTO;
//import com.example.help_desk.dto.RegisterRequestDTO;
//import com.example.help_desk.entity.Role;
//import com.example.help_desk.entity.User;
//import com.example.help_desk.repository.RoleRepository;
//import com.example.help_desk.repository.UserRepository;
//import com.example.help_desk.security.JwtUtil;
//import com.example.help_desk.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
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
//	
//	@Autowired
//    private AuthenticationManager authenticationManager;
//	private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final JwtUtil jwtUtil;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    // Removed PasswordEncoder from constructor completely
//    public AuthServiceImpl(UserRepository userRepository,
//                           RoleRepository roleRepository,
//                           JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public void register(RegisterRequestDTO request) {
//        Role role = roleRepository.findById(request.getRoleId()).orElseThrow();
//
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPasswordHash(request.getPassword());
//        user.setRole(role);
//
//        userRepository.save(user);
//    }
//
//    @Override
//    public LoginResponseDTO login(LoginRequestDTO request) {
//    	// 1. Authenticate the user
//        authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        
//        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
//        
//        if (!user.getPasswordHash().equals(request.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail());
//
//        return new LoginResponseDTO(token,user.getRole().getName());
//    }
//}



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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired // Single constructor injection is the best practice for Spring Boot
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterRequestDTO request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        
        // CRITICAL: Encode the password before saving!
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        // 1. Let Spring Security handle the credential check (hashing check is done here automatically)
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        // 2. Fetch the user after successful authentication
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generate the token
        String token = jwtUtil.generateToken(user.getEmail());

        // 4. Return the token and the role name (Admin/Employee)
        return new LoginResponseDTO(token, user.getRole().getName());
    }
}

