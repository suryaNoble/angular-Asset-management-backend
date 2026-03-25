package com.example.help_desk.controller;

import com.example.help_desk.dto.TicketRequestDTO;
import com.example.help_desk.entity.Asset;
import com.example.help_desk.entity.Ticket;
import com.example.help_desk.entity.User;
import com.example.help_desk.repository.AssetRepository;
import com.example.help_desk.repository.UserRepository;
import com.example.help_desk.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public TicketController(TicketService ticketService,
                            UserRepository userRepository,
                            AssetRepository assetRepository) {
        this.ticketService = ticketService;
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
    }

    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequestDTO dto) {

        User user = userRepository.findById(dto.getCreatedBy()).orElseThrow();
        Asset asset = null;

        if (dto.getAssetId() != null) {
            asset = assetRepository.findById(dto.getAssetId()).orElseThrow();
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus("Open");
        ticket.setCreatedBy(user);
        ticket.setAsset(asset);

        return ticketService.createTicket(ticket);
    }
}