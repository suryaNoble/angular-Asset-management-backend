package com.example.help_desk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.help_desk.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}