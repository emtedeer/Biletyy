package com.example.airlines.repository;

import com.example.airlines.model.Flight;
import com.example.airlines.model.Ticket;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Bean
    InitializingBean init() {
        return () -> {
            // Dodaj przykładowe loty, jeśli baza jest pusta
            if (flightRepository.findAll().isEmpty()) {
                Flight flight1 = new Flight();
                flight1.setFlightNumber("FL123");
                flight1.setDeparture("Warsaw");
                flight1.setDestination("London");
                flight1.setDepartureTime(LocalDateTime.of(2023, 10, 1, 10, 0));
                flight1.setArrivalTime(LocalDateTime.of(2023, 10, 1, 12, 0));
                flightRepository.save(flight1);

                Flight flight2 = new Flight();
                flight2.setFlightNumber("FL456");
                flight2.setDeparture("Berlin");
                flight2.setDestination("Paris");
                flight2.setDepartureTime(LocalDateTime.of(2023, 10, 2, 14, 0));
                flight2.setArrivalTime(LocalDateTime.of(2023, 10, 2, 16, 0));
                flightRepository.save(flight2);
            }

            // Dodaj przykładowe bilety, jeśli baza jest pusta
            if (ticketRepository.findAll().isEmpty()) {
                Flight flight1 = flightRepository.findByFlightNumber("FL123").orElseThrow();

                Ticket ticket1 = new Ticket();
                ticket1.setPassengerName("John Doe");
                ticket1.setSeatNumber("A1");
                ticket1.setFlight(flight1);
                ticketRepository.save(ticket1);

                Flight flight2 = flightRepository.findByFlightNumber("FL456").orElseThrow();

                Ticket ticket2 = new Ticket();
                ticket2.setPassengerName("Jane Smith");
                ticket2.setSeatNumber("B2");
                ticket2.setFlight(flight2);
                ticketRepository.save(ticket2);
            }
        };
    }
}