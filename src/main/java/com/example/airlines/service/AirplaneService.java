package com.example.airlines.service;

import com.example.airlines.model.Airplane;
import com.example.airlines.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    public Airplane getById(Long id) {
        return airplaneRepository.findById(id).orElse(null);
    }
}
