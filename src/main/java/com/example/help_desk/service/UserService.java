package com.example.help_desk.service;


import com.example.help_desk.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
}
