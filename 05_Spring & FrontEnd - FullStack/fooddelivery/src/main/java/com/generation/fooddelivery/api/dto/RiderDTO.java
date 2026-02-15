package com.generation.fooddelivery.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) per l'entità Rider.
 * Utilizzato per trasferire dati del rider tra API e client.
 * Non espone direttamente l'entità per non mostrare dati sensibili come la password.
 */
@Getter
@Setter
public class RiderDTO
{
    int               id;
    String            nickname;
    String            phone;
    String            email;
    String            password;  // Hash della password (non viene esposto nelle risposte API)
    int               riderPositionX;
    int               riderPositionY;

    List<FeedbackDTO> feedbacks;   // Lista dei feedback ricevuti dal rider
    List<DeliveryDTO> deliveries;  // Lista delle consegne effettuate dal rider
}
