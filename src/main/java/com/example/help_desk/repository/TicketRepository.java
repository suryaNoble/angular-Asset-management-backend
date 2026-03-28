package com.example.help_desk.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.help_desk.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDescriptionContaining(String keyword);
    long countByStatus(String status);
    long countByStatusAndCreatedAtBefore(String status, LocalDateTime dateTime);
}