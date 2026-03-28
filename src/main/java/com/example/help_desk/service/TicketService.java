package com.example.help_desk.service;


import com.example.help_desk.entity.Ticket;
import com.example.help_desk.entity.TicketHistory;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Ticket updateTicketStatus(Long ticketId, String status);
    List<Ticket> getAllTickets();
    List<Ticket> searchTickets(String keyword);
    List<TicketHistory> getTicketHistory(Long ticketId);
}