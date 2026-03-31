package com.example.help_desk.dto;

public interface CategoryTreeDTO {
    Integer getId();
    String getVisualTree();
    String getFullHierarchy();
    Integer getCategoryLevel();
    Boolean getIsLeafNode(); // Spring will automatically map SQL BIT/tinyint to Boolean
}