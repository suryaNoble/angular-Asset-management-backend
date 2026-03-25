package com.example.help_desk.entity;

//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Table(name = "Assets")
//@Data
//public class Asset {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//    private String status;
//    private String serialNumber;
//
//    @ManyToOne
//    @JoinColumn(name = "CategoryId")
//    private Category category;
//}


import jakarta.persistence.*;

@Entity
@Table(name = "Assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String status;

    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    private Category category;
    
 // Inside Asset.java
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User assignedTo;

    // Update your Getter/Setter
    public User getAssignedTo() {
        return assignedTo;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getSerialNumber() { return serialNumber; }
    public Category getCategory() { return category; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setStatus(String status) { this.status = status; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setCategory(Category category) { this.category = category; }
}