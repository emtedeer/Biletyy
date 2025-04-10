package com.example.airlines.repository;

import com.example.airlines.model.Flight;
import com.example.airlines.model.Ticket;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Bean
    InitializingBean init() {
        return () -> {
            // przykładowe loty, jeśli baza jest pusta
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
        };
    }
}