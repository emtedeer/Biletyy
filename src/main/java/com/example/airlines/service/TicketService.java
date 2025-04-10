package com.example.airlines.service;

import com.example.airlines.model.Ticket;
import com.example.airlines.model.User;
import com.example.airlines.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    public void bookTicket(Ticket ticket) {
        ticket.setBookingDate(new Date());
        ticketRepository.save(ticket);
    }
}