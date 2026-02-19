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
public class Restaurant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int    id;

    // Relazione many-to-one con City: molti Ristoranti possono essere nella stessa Città
    // FetchType.EAGER significa che quando carichi un Restaurant, carichi anche la sua City
    // @JoinColumn specifica il nome della colonna foreign key nella tabella
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    City   city;

    // Un ristorante può avere molte consegne (delivery)
    @OneToMany(
        mappedBy    = "restaurant",
        cascade     = CascadeType.ALL,
        orphanRemoval = true
    )
    List<Delivery> deliveries = new ArrayList<Delivery>();

    String name;
    String address;
    int    capacity;  // Numero massimo di consegne che il ristorante può gestire contemporaneamente
    int    restaurantPositionX;  // Coordinata X del ristorante
    int    restaurantPositionY;  // Coordinata Y del ristorante

    // Metodo helper che restituisce una stringa con i dati del ristorante
    // Utilizzato dal mapper per creare il campo "restaurantInfo" nel DTO
    public String getInfo()
    {
        return id + " " + name + ", " + address + " " + city.getName();
    }

    // Verifica se il ristorante può accettare nuove ordinazioni
    // Controlla quante consegne "OPEN" (aperte/in corso) ha e confronta con la capacità
    public boolean isAvailable()
    {
        return deliveries.stream()
            .filter(x -> x.getStatus().equals("OPEN"))
            .toList()
            .size() < this.capacity;
    }
}
