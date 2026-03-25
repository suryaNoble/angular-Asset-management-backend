package com.example.help_desk.service.impl;


import com.example.help_desk.entity.Ticket;
import com.example.help_desk.entity.TicketHistory;
import com.example.help_desk.repository.TicketHistoryRepository;
import com.example.help_desk.repository.TicketRepository;
import com.example.help_desk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

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

        TicketHistory history = new TicketHistory();
        history.setTicket(updated);
        history.setStatus(status);

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
}