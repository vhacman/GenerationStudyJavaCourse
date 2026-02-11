package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Restaurant;

/**
 * L'interfaccia RestaurantRepository estende JpaRepository fornendo l'accesso
 * ai dati dell'entità Restaurant attraverso operazioni CRUD predefinite e metodi
 * di query derivati. L'ereditarietà da JpaRepository<Restaurant, Integer>
 * indica che l'entità gestita è Restaurant e che la chiave primaria è di tipo
 * Integer. Spring Data JPA genera automaticamente l'implementazione dell'interfaccia
 * a runtime, iniettandola come bean gestito dal container Spring.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{

    /**
     * Il metodo findByNameContaining implementa una ricerca case-sensitive sulle
     * città il cui nome contiene la stringa passata come parametro. La generazione
     * automatica della query da parte di Spring Data JPA traduce questo metodo
     * in una query SQL con l'operatore LIKE, permettendo ricerche flessibili
     * senza necessità di scrivere JPQL o SQL manuale.
     *
     * @param name La porzione di nome da cercare all'interno del nome del ristorante.
     * @return Una lista di ristoranti che contengono la stringa specificata nel nome.
     */
    List<Restaurant> findByNameContaining(String name);


    /**
     * Il metodo findByCityId cerca tutti i ristoranti associati a una specifica città,
     * sfruttando la relazione definita nel modello entità. Questo permette di
     * recuperare facilmente tutti i ristoranti presenti in una determinata località,
     * operazione comune in applicazioni di ricerca e filtraggio geografico.
     *
     * @param cityId L'identificativo numerico della città.
     * @return Una lista di ristoranti associati alla città specificata.
     */
    List<Restaurant> findByCityId(int cityId);

}
