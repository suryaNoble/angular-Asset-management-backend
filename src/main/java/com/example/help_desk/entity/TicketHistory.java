package com.example.help_desk.entity;

//import java.time.LocalDateTime;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Table(name = "TicketHistory")
//@Data
//public class TicketHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String status;
//
//    private LocalDateTime changedAt;
//
//    // Relationship with Ticket
//    @ManyToOne
//    @JoinColumn(name = "TicketId")
//    private Ticket ticket;
//}


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TicketHistory")
public class TicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    private LocalDateTime changedAt;

    @ManyToOne
    @JoinColumn(name = "TicketId", referencedColumnName = "Id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "ChangedBy", referencedColumnName = "Id")
    private User changedBy;

    public Integer getId() { return id; }
    public String getStatus() { return status; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public Ticket getTicket() { return ticket; }
    public User getChangedBy() { return changedBy; }

    public void setId(Integer id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public void setChangedBy(User changedBy) { this.changedBy = changedBy; }
}