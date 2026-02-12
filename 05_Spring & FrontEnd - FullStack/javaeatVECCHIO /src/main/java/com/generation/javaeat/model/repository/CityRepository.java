package com.generation.javaeat.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.javaeat.model.entities.City;

/**
 * L'interfaccia CityRepository estende JpaRepository, che fornisce tutte le operazioni
 * CRUD (Create, Read, Update, Delete) di base per l'entità City. Spring Data JPA
 * analizza i metodi definiti nell'interfaccia e genera automaticamente le query
 * SQL corrispondenti seguendo convenzioni di naming predefinite. Questo approccio
 * è noto come "query derivation" e permette di evitare la scrittura manuale di
 * query JDBC o JPQL, aumentando la produttività e riducendo la probabilità di errori.
 *
 * L'ereditarietà da JpaRepository<City, Integer> indica che l'entità gestita è City
 * e che la sua chiave primaria è di tipo Integer.
 */
public interface CityRepository extends JpaRepository<City, Integer>
{

    /**
     * Il metodo findByProvince cerca tutte le città associate a una determinata provincia.
     * Spring Data JPA interpreta il nome del metodo e genera automaticamente una query
     * che seleziona le entità City dove il campo province corrisponde al parametro
     * fornito. Questo pattern elimina la necessità di scrivere query manuali per
     * operazioni di ricerca comuni.
     *
     * @param province La provincia da cercare, case-sensitive secondo la configurazione
     *                 del database.
     * @return Una lista di città che appartengono alla provincia specificata.
     */
    List<City> findByProvince(String province);


    /**
     * Il metodo findByNameContaining implementa una ricerca con pattern matching.
     * L'utilizzo della parola chiave "Containing" indica che si cerca un match parziale,
     * corrispondente a una clausola SQL LIKE con wildcards su entrambi i lati.
     * Questo permette di trovare tutte le città il cui nome contiene la stringa
     * specificata, indipendentemente dalla posizione nel nome stesso.
     *
     * @param name La porzione di nome da cercare.
     * @return Una lista di città il cui nome contiene la stringa specificata.
     */
    List<City> findByNameContaining(String name);


    /**
     * Il metodo findByName cerca una città esattamente per nome, restituendo un Optional.
     * L'uso di Optional come tipo di ritorno è una best practice che rende esplicito
     * il fatto che la ricerca potrebbe non trovare risultati, forzando il chiamante
     * a gestire entrambi i casi (presenza e assenza) in modo esplicito.
     *
     * @param Il nome esatto della città da cercare.
     * @return Un Optional contenente la città se trovata, oppure un Optional vuoto.
     */
    Optional<City> findByName(String name);


}
