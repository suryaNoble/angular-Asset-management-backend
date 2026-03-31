package com.example.help_desk.dto;

public class LoginResponseDTO {

    private String token;
    private String role;
    private Integer id;      // Added ID
    private String email;   // Added Email

    public LoginResponseDTO(String token, String role, Integer id, String email) {
        this.token = token;
        this.role = role;
        this.id = id;
        this.email = email;
    }

    // Getters
    public String getToken() { return token; }
    public String getRole() { return role; }
    public Integer getId() { return id; }
    public String getEmail() { return email; }
}