package com.generation.javaeat.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.javaeat.model.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Delivery d WHERE d.restaurant.id = :restaurantId")
    boolean hasDeliveries(@Param("restaurantId") int restaurantId);

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

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.city WHERE r.id = :id")
    Optional<Restaurant> findByIdWithCity(@Param("id") Integer id);

}
