package com.generation.fooddelivery.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int    id;

    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    int    x;  // Coordinata X del cliente (per calcolare distanza)
    int    y;  // Coordinata Y del cliente

    // Relazione one-to-many: un Cliente può avere più Delivery
    @OneToMany(
        mappedBy    = "customer",
        cascade     = CascadeType.ALL,
        orphanRemoval = true
    )
    List<Delivery> deliveries = new ArrayList<Delivery>();

    // Relazione many-to-one: molti Clienti possono essere nella stessa Città
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    City   city;

    // Verifica se il cliente può fare un nuovo ordine
    // Un cliente può avere solo una delivery "OPEN" alla volta
    public boolean canOrder()
    {
        return deliveries.stream()
				.filter(x -> x.getStatus().equals("OPEN"))
				.toList().isEmpty();
    }

    // Metodo helper che restituisce una stringa con i dati del cliente
    public String getInfo()
    {
        return firstName + " " + lastName + " " + address + " " + city.getName();
    }
}
