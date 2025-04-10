package com.example.airlines.model;

import jakarta.persistence.*;

@Entity
public class Luggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LuggageType type;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public enum LuggageType {
        CABIN,
        CHECKED
    }

    public String getTypeName() {
        return switch (this.type) {
            case CABIN -> "Podręczny";
            case CHECKED -> "Rejestrowany";
        };
    }


    public Long getId() {
        return id;
    }

    public LuggageType getType() {
        return type;
    }

    public void setType(LuggageType type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
