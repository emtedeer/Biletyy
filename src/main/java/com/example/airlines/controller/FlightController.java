package com.example.airlines.controller;

import com.example.airlines.model.Flight;
import com.example.airlines.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights/list";
    }

    @GetMapping("/{id}")
    public String getFlightById(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        return "flights/details";
    }

    // Nowe metody do dodawania lotów
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/add";
    }

    @PostMapping("/add")
    public String addFlight(@ModelAttribute @Valid Flight flight, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "flights/add";
        }
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }
}