package com.example.airlines.controller;

import com.example.airlines.model.Ticket;
import com.example.airlines.service.FlightService;
import com.example.airlines.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired // Wstrzyknij FlightService
    private FlightService flightService;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "tickets/list";
    }

    @GetMapping("/book")
    public String showBookForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("flights", flightService.getAllFlights());
        return "tickets/book";
    }

    @PostMapping("/book")
    public String bookTicket(@ModelAttribute Ticket ticket) {
        ticketService.bookTicket(ticket);
        return "redirect:/tickets";
    }
}