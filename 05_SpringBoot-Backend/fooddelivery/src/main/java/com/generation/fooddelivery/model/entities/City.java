package com.generation.fooddelivery.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int    id;

    String name;      // Nome della città (es: "Milano")
    String province;  // Provincia (es: "MI")
}
