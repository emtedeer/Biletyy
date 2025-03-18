package com.example.airlines.repository;

import com.example.airlines.model.Ticket;
import com.example.airlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
}