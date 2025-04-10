package com.example.airlines.repository;

import com.example.airlines.model.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuggageRepository extends JpaRepository<Luggage, Long> {
}
