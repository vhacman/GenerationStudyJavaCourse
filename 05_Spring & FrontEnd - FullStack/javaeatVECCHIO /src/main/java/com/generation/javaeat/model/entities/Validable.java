package com.generation.javaeat.model.entities;

import java.util.List;

/**
 * L'interfaccia Validable definisce un contratto per le entità che necessitano
 * di validazione dei propri dati. L'utilizzo di un'interfaccia dedicata alla
 * validazione permette di centralizzare la logica di controllo e garantire
 * che tutte le entità implementino un comportamento coerente quando sottoposte
 * a verifica. L'interfaccia è un concetto fondamentale della programmazione
 * orientata agli oggetti che permette di definire comportamenti comuni senza
 * imporre una gerarchia di ereditarietà.
 */
public interface Validable
{

    /**
     * Il metodo getErrors restituisce una lista di stringhe contenenti tutti
     * gli errori di validazione riscontrati nell'entità. Una lista vuota
     * indica che l'entità è valida secondo i criteri definiti. L'approccio
     * di restituire tutti gli errori contemporaneamente, anziché fermarsi
     * al primo errore rilevato, offre all'utente una panoramica completa
     * dei problemi da risolvere.
     *
     * @return Una lista di messaggi di errore, vuota se l'entità è valida.
     */
    List<String> getErrors();


    /**
     * Il metodo isValid fornisce una verifica sintetica della validità
     * dell'entità, restituendo true se non sono presenti errori. L'utilizzo
     * del modificatore default nell'interfaccia permette di fornire
     * un'implementazione predefinita che può essere sovrascritta dalle
     * classi che implementano l'interfaccia se necessario.
     *
     * @return true se la lista degli errori è vuota, false altrimenti.
     */
    default boolean isValid()
    {
        return getErrors().isEmpty();
    }
    
    default boolean emailIsValid(String email)
    {
        return email != null && email.matches("[\\w.-]+@[\\w.-]+\\.\\w{2,}") && !email.isEmpty();
    }
}
