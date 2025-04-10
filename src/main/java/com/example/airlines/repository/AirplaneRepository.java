package com.example.airlines.repository;

import com.example.airlines.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
