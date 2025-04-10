package com.example.airlines.controller;

import com.example.airlines.model.Luggage;
import com.example.airlines.model.Ticket;
import com.example.airlines.model.User;
import com.example.airlines.service.FlightService;
import com.example.airlines.service.LuggageService;
import com.example.airlines.service.TicketService;
import com.example.airlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private LuggageService luggageService;


    @GetMapping
    public String getAllTickets(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (user.getRole().equals("ADMIN")) {
            model.addAttribute("tickets", ticketService.getAllTickets());
        } else {
            model.addAttribute("tickets", ticketService.getTicketsByUser(user));
        }
        return "tickets/list";
    }

    @GetMapping("/book")
    public String showBookForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("flights", flightService.getAllFlights());
        return "tickets/book";
    }

    @PostMapping("/book")
    public String bookTicket(@ModelAttribute Ticket ticket,
                             @RequestParam("luggageType") String luggageType,
                             @RequestParam("weight") double weight,
                             Principal principal) {

        User user = userService.findByUsername(principal.getName());
        ticket.setUser(user);

        ticketService.bookTicket(ticket);

        Luggage luggage = new Luggage();
        luggage.setType(Luggage.LuggageType.valueOf(luggageType));
        luggage.setWeight(weight);
        luggage.setTicket(ticket);
        luggageService.save(luggage);

        return "redirect:/tickets";
    }

}