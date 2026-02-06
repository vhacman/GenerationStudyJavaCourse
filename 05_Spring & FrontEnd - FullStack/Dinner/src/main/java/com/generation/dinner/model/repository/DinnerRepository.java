package com.generation.dinner.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.dinner.model.entities.Dinner;

/**
 * Repository interface per l'entità Dinner.
 * Estende JpaRepository per ereditare le operazioni CRUD standard e la gestione della paginazione.
 */
@Repository
public interface DinnerRepository extends JpaRepository<Dinner, Integer>
{ 
    /**
     * Recupera una lista di cene filtrate in base allo stato specificato.
     * Utilizza la Query Derivation di Spring Data: "findBy" + "Status".
     * * @param status Lo stato della cena (es. "COMPLETED", "PENDING").
     * @return Una lista di oggetti Dinner che corrispondono allo stato fornito.
     */
    List<Dinner> findByStatus(String status);

    
}