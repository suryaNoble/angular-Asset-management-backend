package com.example.help_desk.entity;

//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Table(name = "Categories")
//@Data
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "ParentCategoryId")
//    private Category parent;
//}


import jakarta.persistence.*;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "ParentCategoryId", referencedColumnName = "Id")
    private Category parentCategory;

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Category getParentCategory() { return parentCategory; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setParentCategory(Category parentCategory) { this.parentCategory = parentCategory; }
}