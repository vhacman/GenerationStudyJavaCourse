package com.generation.javaeat.model.entities;

import java.util.List;

/**
 * Contratto per le entita' che supportano la validazione.
 * Fornisce metodi per validare lo stato dell'entita' e controllare il formato email.
 */
public interface Validable
{
    /**
     * Valida l'entita' e restituisce una lista di messaggi di errore.
     * @return lista di messaggi di errore; vuota se l'entita' e' valida
     */
    List<String> getErrors();

    /**
     * Controlla se l'entita' e' valida.
     * @return true se non ci sono errori di validazione
     */
    default boolean isValid()
    {
        return getErrors().isEmpty();
    }

    /**
     * Valida il formato dell'email usando un pattern standard.
     * @param email l'indirizzo email da validare
     * @return true se il formato dell'email e' valido
     */
    default boolean emailIsValid(String email)
    {
        return email != null
            && email.matches("[\\w.-]+@[\\w.-]+\\.\\w{2,}")
            && !email.isEmpty();
    }
}
