package com.example.help_desk.entity;

//import java.time.LocalDateTime;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Table(name = "AuditLogs")
//@Data
//public class AuditLogs {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String oldStatus;
//
//    private String newStatus;
//
//    private LocalDateTime changedAt;
//
//    // Relationship with Ticket
//    @ManyToOne
//    @JoinColumn(name = "TicketId")
//    private Ticket ticket;
//
//    // Relationship with User
//    @ManyToOne
//    @JoinColumn(name = "UserId")
//    private User user;
//}



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLogs")
public class AuditLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketId;

    private String oldStatus;

    private String newStatus;

    private LocalDateTime changedAt;

    private Long userId;
    
    private Long assetId;

    public Long getId() { return id; }
    public Long getTicketId() { return ticketId; }
    public String getOldStatus() { return oldStatus; }
    public String getNewStatus() { return newStatus; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public Long getUserId() { return userId; }
    public Long getAssetId() {return assetId;}

    public void setId(Long id) { this.id = id; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public void setOldStatus(String oldStatus) { this.oldStatus = oldStatus; }
    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setAssetId(Long assetId) {this.assetId = assetId;}
}