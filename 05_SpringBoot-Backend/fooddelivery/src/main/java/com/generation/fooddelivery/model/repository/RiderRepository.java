package com.generation.fooddelivery.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.fooddelivery.model.entities.Rider;

/**
 * Repository per l'entità Rider.
 * JpaRepository fornisce metodi CRUD automatici: save, findById, findAll, delete, etc.
 */
public interface RiderRepository extends JpaRepository<Rider, Integer>
{
    // Trova un rider per email (restituisce una lista perché email potrebbe non essere univoca)
    List<Rider> findByEmail(String email);

    // Metodo di convenienza: verifica se esiste un rider con quella email
    default boolean exists(String email)
    {
        return !findByEmail(email).isEmpty();
    }

    // Query JPQL per trovare il rider più vicino al ristorante
    // Calcola la distanza euclidea quadrata tra rider e ristorante
    // Seleziona solo rider che NON hanno delivery "OPEN" (sono disponibili)
    // Ordina per distanza crescente e limita a 1 (il più vicino)
    @Query("""
        SELECT r FROM Rider r
        WHERE ((r.riderPositionX - :riderPositionX) * (r.riderPositionX - :riderPositionX)
             + (r.riderPositionY - :riderPositionY) * (r.riderPositionY - :riderPositionY)) <= :squareDistance
          AND NOT EXISTS (
              SELECT d FROM Delivery d
              WHERE d.rider = r AND d.status = 'OPEN'
          )
        ORDER BY ((r.riderPositionX - :riderPositionX) * (r.riderPositionX - :riderPositionX)
                + (r.riderPositionY - :riderPositionY) * (r.riderPositionY - :riderPositionY)) ASC
        LIMIT 1
        """)
    Rider findNearest(
        @Param("riderPositionX")  int riderPositionX,
        @Param("riderPositionY")  int riderPositionY,
        @Param("squareDistance")  int squareDistance
    );

    // Trova i rider che hanno effettuato almeno una consegna tra due date
    // Usa un JOIN tra Rider e le sue Delivery
    @Query("SELECT r FROM Rider r JOIN r.deliveries d WHERE d.deliveryTime BETWEEN :d1 AND :d2")
    List<Rider> findActiveBetween(
        @Param("d1") LocalDateTime d1,
        @Param("d2") LocalDateTime d2
    );
}
