package com.generation.hotelboolking.api;

import com.generation.hotelboolking.model.entities.Booking;
import com.generation.hotelboolking.model.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotelservice/api/booking")
public class BookingAPI
{
    @Autowired
    BookingRepository repo;

    /**
     * Crea una nuova prenotazione nel sistema.
     *
     * Imposta lo stato iniziale a "pending", calcola automaticamente il numero
     * di notti, il costo totale delle camere e l'importo finale. Valida i dati
     * della prenotazione prima del salvataggio.
     *
     * @param booking l'oggetto prenotazione da creare con i dati forniti dal client
     * @return ResponseEntity contenente la prenotazione salvata (200 OK) oppure
     *         gli errori di validazione (400 Bad Request)
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking)
    {
        booking.setStatus("pending");
        booking.calculateNumberOfNights();
        booking.calculateTotalRoomCost();
        booking.calculateTotalAmount();
        if (!booking.isValid())
            return ResponseEntity.badRequest().body(booking);
        repo.save(booking);
        return ResponseEntity.ok(booking);
    }

    /**
     * Recupera tutte le prenotazioni.
     *
     * @return ResponseEntity contenente la lista di tutte le prenotazioni (200 OK)
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings()
    {
        return ResponseEntity.ok(repo.findAll());
    }

    /**
     * Recupera una prenotazione tramite il suo identificativo.
     *
     * @param id l'identificativo univoco della prenotazione da recuperare
     * @return ResponseEntity contenente la prenotazione trovata (200 OK) oppure
     *         risposta vuota se non esiste (404 Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id)
    {
        Optional<Booking> result = repo.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        /*
         * Mappa l'Optional a ResponseEntity: ok se presente, notFound se vuoto
         * return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
         *
         * result - È un Optional<Booking> che può contenere una prenotazione oppure essere vuoto.
         * .map(ResponseEntity::ok) - Metodo di trasformazione dell'Optional:
         *     Se result contiene un Booking: applica la funzione ResponseEntity::ok
         *      (method reference equivalente a booking -> ResponseEntity.ok(booking)),
         *       producendo Optional<ResponseEntity<Booking>> con valore
         *     Se result è vuoto: non applica nulla, resta Optional.empty()
         *     È una trasformazione condizionale: lavora solo su Optional pieni
         *
         * ResponseEntity::ok - Method reference che punta al metodo statico ResponseEntity.ok(T body):
         *     Versione compatta di booking -> ResponseEntity.ok(booking)
         *     Crea una risposta HTTP 200 con il booking nel body
         * .orElseGet(() -> ...) - Fornisce un valore di default lazy (valutato solo quando necessario):
         *     Se l'Optional ha valore: restituisce il ResponseEntity.ok(booking) creato dal map
         *     Se l'Optional è vuoto: esegue la lambda e restituisce ResponseEntity.notFound().build()
         * () -> ResponseEntity.notFound().build() - Lambda expression (Supplier) che:
         *     Non prende parametri ()
         *     Costruisce un ResponseEntity con status 404 e body vuoto
         *     Viene eseguita solo se l'Optional è vuoto (lazy evaluation)
         */
    }

    /**
     * Recupera tutte le prenotazioni con uno specifico stato.
     *
     * @param status lo stato delle prenotazioni da recuperare (es. "pending", "confirmed", "cancelled")
     * @return ResponseEntity contenente la lista delle prenotazioni con lo stato specificato (200 OK)
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable String status)
    {
        return ResponseEntity.ok(repo.findByStatus(status));
    }

    /**
     * Recupera tutte le prenotazioni per uno specifico tipo di camera.
     *
     * @param roomType il tipo di camera da cercare (es. "single", "double", "suite")
     * @return ResponseEntity contenente la lista delle prenotazioni per il tipo di camera specificato (200 OK)
     */
    @GetMapping("/room/{roomType}")
    public ResponseEntity<List<Booking>> getBookingsByRoomType(@PathVariable String roomType)
    {
        return ResponseEntity.ok(repo.findByRoomType(roomType));
    }

    /**
     * Cerca prenotazioni in base al nome dell'ospite.
     *
     * @param guest il nome o parte del nome dell'ospite da cercare
     * @return ResponseEntity contenente la lista delle prenotazioni che corrispondono al criterio di ricerca (200 OK)
     */
    @GetMapping("/search")
    public ResponseEntity<List<Booking>> searchBookingsByGuest(@RequestParam("guest") String guest)
    {
        return ResponseEntity.ok(repo.findByGuestNameContaining(guest));
    }

    /**
     * Conferma una prenotazione esistente.
     *
     * Modifica lo stato della prenotazione da "pending" a "confirmed".
     * La conferma è possibile solo per prenotazioni in stato "pending".
     *
     * @param id l'identificativo univoco della prenotazione da confermare
     * @return ResponseEntity contenente la prenotazione confermata (200 OK),
     *         errore se la prenotazione non è in stato "pending" (400 Bad Request),
     *         oppure risposta vuota se non esiste (404 Not Found)
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable int id)
    {
        Optional<Booking> result = repo.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Booking booking = result.get();
        if (!booking.getStatus().equals("pending"))
            return ResponseEntity.badRequest().body(booking);
        booking.setStatus("confirmed");
        repo.save(booking);
        return ResponseEntity.ok(booking);
    }

    /**
     * Effettua il check-in di una prenotazione.
     *
     * Modifica lo stato della prenotazione da "confirmed" a "checked-in".
     * Il check-in è possibile solo se la prenotazione è in stato "confirmed" e
     * la data di check-in è arrivata o passata.
     *
     * @param id l'identificativo univoco della prenotazione per cui effettuare il check-in
     * @return ResponseEntity contenente la prenotazione con check-in effettuato (200 OK),
     *         errore se la prenotazione non è in stato "confirmed" (400 Bad Request),
     *         errore se la data di check-in non è ancora arrivata (400 Bad Request),
     *         oppure risposta vuota se non esiste (404 Not Found)
     */
    @PutMapping("/{id}/checkin")
    public ResponseEntity<?> checkInBooking(@PathVariable int id)
    {
        Optional<Booking> result = repo.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Booking booking = result.get();
        if (!booking.getStatus().equals("confirmed"))
            return ResponseEntity.badRequest().body("La prenotazione deve essere in stato confirmed per effettuare il check-in");
        if (booking.getCheckInDate().isAfter(LocalDate.now()))
            return ResponseEntity.badRequest().body("La data di check-in non è ancora arrivata");
        booking.setStatus("checked-in");
        repo.save(booking);
        return ResponseEntity.ok(booking);
    }

    /**
     * Effettua il check-out di una prenotazione.
     *
     * Modifica lo stato della prenotazione da "checked-in" a "checked-out".
     * Il check-out è possibile solo se la prenotazione è in stato "checked-in".
     *
     * @param id l'identificativo univoco della prenotazione per cui effettuare il check-out
     * @return ResponseEntity contenente la prenotazione con check-out effettuato (200 OK),
     *         errore se la prenotazione non è in stato "checked-in" (400 Bad Request),
     *         oppure risposta vuota se non esiste (404 Not Found)
     */
    @PutMapping("/{id}/checkout")
    public ResponseEntity<?> checkOutBooking(@PathVariable int id)
    {
        Optional<Booking> result = repo.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Booking booking = result.get();
        if (!booking.getStatus().equals("checked-in"))
            return ResponseEntity.badRequest().body("La prenotazione deve essere in stato checked-in per effettuare il check-out");
        if (booking.getCheckOutDate().isAfter(LocalDate.now()))
            return ResponseEntity.badRequest().body("La data di check-out non è ancora arrivata");
        booking.setStatus("checked-out");
        repo.save(booking);
        return ResponseEntity.ok(booking);
    }

    /**
     * Cancella una prenotazione esistente.
     *
     * Modifica lo stato della prenotazione a "cancelled".
     * La cancellazione è possibile solo se la prenotazione non è già in stato
     * "checked-in" o "checked-out".
     *
     * @param id l'identificativo univoco della prenotazione da cancellare
     * @return ResponseEntity contenente la prenotazione cancellata (200 OK),
     *         errore se la prenotazione è in stato "checked-in" o "checked-out" (400 Bad Request),
     *         oppure risposta vuota se non esiste (404 Not Found)
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable int id)
    {
        Optional<Booking> result = repo.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Booking booking = result.get();
        if (booking.getStatus().equals("checked-in") || booking.getStatus().equals("checked-out"))
            return ResponseEntity.badRequest().body("Non è possibile cancellare una prenotazione in stato " + booking.getStatus());
        booking.setStatus("cancelled");
        repo.save(booking);
        return ResponseEntity.ok(booking);
    }
}