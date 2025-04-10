package com.example.airlines.controller;

import com.example.airlines.service.AirplaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping
    public String listAirplanes(Model model) {
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        return "airplanes/list";
    }

    @GetMapping("/{id}")
    public String airplaneDetails(@PathVariable Long id, Model model) {
        model.addAttribute("airplane", airplaneService.getById(id));
        return "airplanes/details";
    }
}
