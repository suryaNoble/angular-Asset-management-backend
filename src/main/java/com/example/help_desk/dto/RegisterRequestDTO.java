package com.example.help_desk.dto;


public class RegisterRequestDTO {

    private String name;
    private String email;
    private String password;
    private Long roleId;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getRoleId() {
        return roleId;
    }
}