package com.example.airlines.service;

import com.example.airlines.model.Ticket;
import com.example.airlines.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket bookTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}