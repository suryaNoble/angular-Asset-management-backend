package com.example.help_desk.entity;

//import java.time.LocalDateTime;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Table(name = "Tickets")
//@Data
//public class Ticket {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String title;
//    private String description;
//    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "CreatedBy")
//    private User createdBy;
//
//    private LocalDateTime createdAt;
//}



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    private String status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "CreatedBy", referencedColumnName = "Id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "AssetId", referencedColumnName = "Id")
    private Asset asset;

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getCreatedBy() { return createdBy; }
    public Asset getAsset() { return asset; }

    public void setId(Integer id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
    public void setAsset(Asset asset) { this.asset = asset; }
}