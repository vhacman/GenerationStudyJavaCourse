package com.generation.fooddelivery.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rider
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int    id;

    // Relazione one-to-many con Feedback: un Rider può avere più Feedback
    // mappedBy indica che la foreign key è nel campo "rider" dell'entità Feedback
    // cascade = ALL significa che se cancelli un Rider, vengono cancellati anche i suoi Feedback
    // orphanRemoval = true: se rimuovi un Feedback dalla lista, viene cancellato dal DB
    @OneToMany(
        mappedBy    = "rider",
        cascade     = CascadeType.ALL,
        orphanRemoval = true
    )
    List<Feedback> feedbacks = new ArrayList<Feedback>();

    // Relazione one-to-many con Delivery: un Rider può avere più consegne
    @OneToMany(
        mappedBy    = "rider",
        cascade     = CascadeType.ALL,
        orphanRemoval = true
    )
    List<Delivery> deliveries = new ArrayList<Delivery>();

    String nickname;
    String email;
    String password;
    String phone;
    int    riderPositionX;  // Coordinata X del rider (per calcolare distanza)
    int    riderPositionY;  // Coordinata Y del rider

    // Metodo helper che restituisce una stringa con i dati del rider
    // Utilizzato dal mapper per creare il campo "riderInfo" nel DTO
    public String getInfo()
    {
        return nickname + " " + email + " " + password + " " + phone;
    }
}
