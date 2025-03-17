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

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Metoda do wyszukiwania lotów według miasta odlotu i przylotu
    public List<Flight> getFlightsByDepartureAndDestination(String departure, String destination) {
        if (departure != null && !departure.isEmpty() && destination != null && !destination.isEmpty()) {
            return flightRepository.findByDepartureIgnoreCaseAndDestinationIgnoreCase(departure, destination);
        } else if (departure != null && !departure.isEmpty()) {
            return flightRepository.findByDepartureIgnoreCase(departure);
        } else if (destination != null && !destination.isEmpty()) {
            return flightRepository.findByDestinationIgnoreCase(destination);
        } else {
            return flightRepository.findAll();
        }
    }
}