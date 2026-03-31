package com.example.help_desk.controller;

import com.example.help_desk.dto.CategoryTreeDTO;
import com.example.help_desk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryTreeDTO> getAllCategories() {
        return categoryService.getCategoryTree();
    }
}