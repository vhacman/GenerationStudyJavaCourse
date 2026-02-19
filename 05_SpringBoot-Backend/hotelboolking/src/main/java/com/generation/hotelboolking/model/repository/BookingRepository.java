package com.generation.hotelboolking.model.repository;

import java.time.LocalDate;
import java.util.List;

import com.generation.hotelboolking.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    /**
     * Query Method: trova tutte le prenotazioni con un determinato stato.
     * Spring Data JPA genera automaticamente la query SQL:
     * SELECT * FROM booking WHERE status = ?
     */
    List<Booking> findByStatus(String status);

    /**
     * Query Method: trova tutte le prenotazioni per tipo di camera.
     * Query generata: SELECT * FROM booking WHERE room_type = ?
     */
    List<Booking> findByRoomType(String roomType);

    /**
     * Query Method: cerca prenotazioni il cui nome ospite contiene una stringa.
     * Utile per ricerche parziali (es. cercare "Rossi" trova "Mario Rossi").
     * Query generata: SELECT * FROM booking WHERE guest_name LIKE %?%
     */
    List<Booking> findByGuestNameContaining(String guestName);

    /**
     * Query Method: trova prenotazioni con check-in in un intervallo di date.
     * Utile per vedere occupazione hotel in un periodo specifico.
     * Query generata: SELECT * FROM booking WHERE check_in_date BETWEEN ? AND ?
     */
    List<Booking> findByCheckInDateBetween(LocalDate start, LocalDate end);

    /**
     * BONUS - Query Method avanzata:
     * Trova prenotazioni attive (checked-in) per una specifica camera.
     * Combina due condizioni con AND.
     * Query generata: SELECT * FROM booking WHERE room_type = ? AND status = ?
     */
    List<Booking> findByRoomTypeAndStatus(String roomType, String status);
}
