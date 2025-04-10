package com.example.airlines.controller;

import com.example.airlines.model.Flight;
import com.example.airlines.service.AirplaneService;
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

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public String getAllFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String destination,
            Model model
    ) {
        if ((departure != null && !departure.isEmpty()) || (destination != null && !destination.isEmpty())) {
            model.addAttribute("flights", flightService.getFlightsByDepartureAndDestination(departure, destination));
        } else {
            model.addAttribute("flights", flightService.getAllFlights());
        }
        return "flights/list";
    }

    @GetMapping("/{id}")
    public String getFlightById(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        return "flights/details";
    }

    @GetMapping("/add")
    public String showFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        return "flights/add";
    }

    @PostMapping("/add")
    public String addFlight(@ModelAttribute @Valid Flight flight, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            return "flights/add";
        }
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }
}
