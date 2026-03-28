package com.example.help_desk.service.impl;


import com.example.help_desk.entity.Ticket;
import com.example.help_desk.entity.TicketHistory;
import com.example.help_desk.entity.User;
import com.example.help_desk.repository.TicketHistoryRepository;
import com.example.help_desk.repository.TicketRepository;
import com.example.help_desk.repository.UserRepository;
import com.example.help_desk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketHistoryRepository ticketHistoryRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicketStatus(Long ticketId, String status) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();

        ticket.setStatus(status);
        Ticket updated = ticketRepository.save(ticket);
        
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
        
//        User currentUser = userRepository.findByEmail(email).orElse(null);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Now we find the user by that email
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for token: " + email));
        System.out.println("2. User saved to DB with ID in TicketServiceImpl: " + currentUser);
        TicketHistory history = new TicketHistory();
        history.setTicket(updated);
        history.setStatus(status);
        history.setChangedBy(currentUser); 
        history.setChangedAt(java.time.LocalDateTime.now());

        ticketHistoryRepository.save(history);

        return updated;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.findByDescriptionContaining(keyword);
    }
    
    @Override
    public List<TicketHistory> getTicketHistory(Long ticketId) {
        return ticketHistoryRepository.findByTicketIdOrderByChangedAtDesc(ticketId);
    }
}