package com.example.airlines.service;

import com.example.airlines.model.Flight;
import com.example.airlines.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // Nowa metoda do zapisywania lotu
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}