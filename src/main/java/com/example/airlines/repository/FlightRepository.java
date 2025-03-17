package com.example.airlines.repository;

import com.example.airlines.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByDepartureIgnoreCase(String departure);
    List<Flight> findByDestinationIgnoreCase(String destination);
    List<Flight> findByDepartureIgnoreCaseAndDestinationIgnoreCase(String departure, String destination);
}