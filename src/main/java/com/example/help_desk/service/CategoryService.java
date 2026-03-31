package com.example.help_desk.service;

import com.example.help_desk.dto.CategoryTreeDTO;
import com.example.help_desk.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryTreeDTO> getCategoryTree() {
        return categoryRepository.getCategoryTree();
    }
}