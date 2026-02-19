package com.generation.fooddelivery.api.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO per l'entità Feedback.
 */
@Getter
@Setter
public class FeedbackDTO
{
    private int    id;
    private String text;    // Testo del feedback
    private int    score;   // Voto da 1 a 5
    private int    riderId; // ID del rider a cui è relativo il feedback
}
