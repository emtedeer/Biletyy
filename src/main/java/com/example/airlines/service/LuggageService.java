package com.example.airlines.service;

import com.example.airlines.model.Luggage;
import com.example.airlines.repository.LuggageRepository;
import org.springframework.stereotype.Service;

@Service
public class LuggageService {

    private final LuggageRepository luggageRepository;

    public LuggageService(LuggageRepository luggageRepository) {
        this.luggageRepository = luggageRepository;
    }

    public Luggage save(Luggage luggage) {
        return luggageRepository.save(luggage);
    }

}
