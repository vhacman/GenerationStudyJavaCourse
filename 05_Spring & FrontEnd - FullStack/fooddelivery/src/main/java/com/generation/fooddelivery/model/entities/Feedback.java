package com.generation.fooddelivery.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feedback
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int    id;

    String text;  // Testo del feedback (es: "Ottimo servizio, molto veloce!")
    int    score;  // Voto da 1 a 5

    // Relazione many-to-one: ogni Feedback è dato da un Rider
    // Un Rider può avere molti Feedback
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rider_id")
    Rider rider;
}
