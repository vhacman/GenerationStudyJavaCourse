package com.generation.sanctionweb.model.repository;

import com.generation.sanctionweb.model.entities.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'accesso ai dati dell'entità Sanction.
 * Estende JpaRepository per heredarare le operazioni CRUD standard.
 */
@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Integer>
{
    /*
     * ARCHITETTURA OOP - PATTERN E PRINCIPI:
     * 
     *     Repository Pattern  →  Separazione logica di business da accesso dati
     *     JpaRepository       →  Standardizzazione operazioni database
     *     Interfaccia         →  Implementazione fornita da Spring Data
     *     Lazy/Eager Loading  →  Gestito a livello di query
     */
}
