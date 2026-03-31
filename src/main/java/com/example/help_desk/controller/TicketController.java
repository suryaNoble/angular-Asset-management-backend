package com.example.help_desk.controller;

import com.example.help_desk.dto.TicketRequestDTO;
import com.example.help_desk.entity.Asset;
import com.example.help_desk.entity.Ticket;
import com.example.help_desk.entity.TicketHistory;
import com.example.help_desk.entity.User;
import com.example.help_desk.repository.AssetRepository;
import com.example.help_desk.repository.UserRepository;
import com.example.help_desk.service.TicketService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

//    private final TicketService ticketService;
	@Autowired
    private TicketService ticketService;
	private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

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
    
    //updating status of ticket for ticket lifecycle
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateTicketStatus(@PathVariable Long id, @RequestBody String status) {
    	Ticket updatedTicket = ticketService.updateTicketStatus(id, status);
    	String message = "Your ticket #" + id + " (" + updatedTicket.getTitle() + ") is now " + status;
    	Integer userId = updatedTicket.getCreatedBy().getId();
        messagingTemplate.convertAndSend("/queue/notifications/" + userId, message);

        return ResponseEntity.ok(Map.of("message", "Status updated successfully"));
        
    
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
    
    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUser(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId);
    }
    
    @GetMapping("/{id}/history")
    public List<TicketHistory> getTicketHistory(@PathVariable Long id) {
        return ticketService.getTicketHistory(id);
    }
    
    @GetMapping("/all-history")
    public List<TicketHistory> getAllTicketHistory() {
        return ticketService.getAllTicketHistories();
    }
    
    

    @GetMapping("/search")
    public List<Ticket> searchTickets(@RequestParam String keyword) {
        return ticketService.searchTickets(keyword);
    }
}