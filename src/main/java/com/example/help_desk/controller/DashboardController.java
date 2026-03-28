package com.example.help_desk.controller;

import com.example.help_desk.dto.DashboardStatsDTO;
import com.example.help_desk.repository.AssetRepository;
import com.example.help_desk.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final TicketRepository ticketRepository;
    private final AssetRepository assetRepository;

    public DashboardController(TicketRepository ticketRepository, AssetRepository assetRepository) {
        this.ticketRepository = ticketRepository;
        this.assetRepository = assetRepository;
    }

    @GetMapping("/stats")
    public DashboardStatsDTO getStats() {
        long openTickets = ticketRepository.countByStatus("Open");
        long assigned = assetRepository.countByStatus("Assigned");
        long available = assetRepository.countByStatus("Available");
        
        // Logic for tickets older than 24 hours
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        long pendingLongTime = ticketRepository.countByStatusAndCreatedAtBefore("Open", oneDayAgo);

        return new DashboardStatsDTO(openTickets, assigned, available, pendingLongTime);
    }
}