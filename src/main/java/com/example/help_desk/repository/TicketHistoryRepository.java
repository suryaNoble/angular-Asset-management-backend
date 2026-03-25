package com.example.help_desk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.help_desk.entity.TicketHistory;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {
}