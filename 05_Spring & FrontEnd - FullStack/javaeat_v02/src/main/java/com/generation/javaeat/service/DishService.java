package com.generation.javaeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javaeat.model.entities.Dish;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.DishRepository;
import com.generation.javaeat.model.repository.RestaurantRepository;
import com.generation.javaeat.service.dto.DishDTO;
import com.generation.javaeat.service.dto.mapper.DishDTOMapper;

/**
 * Service per la gestione delle operazioni sui piatti.
 * 
 * CAMBI DALLE VECCHIE ENTITA':
 * - La relazione Dish-Delivery e' cambiata da ManyToOne a ManyToMany.
 *   Un piatto puo' ora comparire in piu' consegne diverse.
 *   Il metodo findByDeliveryId sfrutta questa nuova relazione per trovare
 *   tutti i piatti associati a una specifica consegna.
 */
@Service
public class DishService
{
    @Autowired
    private DishRepository   dishRepo;
    @Autowired
    private DishDTOMapper    dishDTOMapper;
    @Autowired
    private RestaurantRepository restaurantRepo;

    /**
     * Inserisce un nuovo piatto nel database.
     * 
     * @param dto i dati del piatto da inserire
     * @return il piatto inserito convertito in DTO
     * @throws MyServiceException se i dati sono nulli o non validi
     */
    public DishDTO insert(DishDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("DishDTO is null");
        if (dto.getName() == null || dto.getName().trim().isEmpty())
            throw new MyServiceException("Dish name is required");
        if (dto.getPrice() < 0)
            throw new MyServiceException("Price cannot be negative");
        if (dto.getRestaurantId() <= 0)
            throw new MyServiceException("Restaurant is required");
        
        Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId()).orElse(null);
        if (restaurant == null)
            throw new MyServiceException("Restaurant not found with id: " + dto.getRestaurantId());
        
        Dish dish = dishDTOMapper.toEntity(dto);
        dish.setRestaurant(restaurant);
        
        dish = dishRepo.save(dish);
        return dishDTOMapper.toDTO(dish);
    }

    /**
     * Restituisce tutti i piatti presenti nel database.
     * 
     * @return lista di tutti i piatti convertiti in DTO
     */
    public List<DishDTO> findAll()
    {
        return dishDTOMapper.toDTOList(dishRepo.findAll());
    }

    /**
     * Trova un piatto per ID.
     * 
     * @param id l'ID del piatto da cercare
     * @return il piatto trovato convertito in DTO
     * @throws MyServiceException se il piatto non viene trovato
     */
    public DishDTO findById(int id) throws MyServiceException
    {
        Dish dish = dishRepo.findById(id).orElse(null);
        if (dish == null)
            throw new MyServiceException("Dish not found with id: " + id);
        return dishDTOMapper.toDTO(dish);
    }

    /**
     * Trova i piatti appartenenti a un specifico ristorante.
     * 
     * @param restaurantId l'id del ristorante
     * @return lista di piatti del ristorante specificato
     * @throws MyServiceException se non viene trovato nessun piatto
     */
    public List<DishDTO> findByRestaurantId(int restaurantId) throws MyServiceException
    {
        List<Dish> dishes = dishRepo.findByRestaurantId(restaurantId);
        if (dishes.isEmpty())
            throw new MyServiceException("No dishes found for restaurant id: " + restaurantId);
        return dishDTOMapper.toDTOList(dishes);
    }

    /**
     * Trova i piatti associati a una specifica consegna.
     * 
     * CAMBI DALLA VECCHIA VERSIONE:
     * - Prima la relazione era ManyToOne: un piatto apparteneva a UNA sola consegna.
     * - Ora è ManyToMany: un piatto puo' comparire in PIU' consegne.
     *   Questo metodo sfrutta la nuova relazione per trovare tutti i piatti di una consegna.
     * 
     * @param deliveryId l'id della consegna
     * @return lista di piatti associati alla consegna
     * @throws MyServiceException se non viene trovato nessun piatto
     */
    public List<DishDTO> findByDeliveryId(int deliveryId) throws MyServiceException
    {
        List<Dish> dishes = dishRepo.findByDeliveries_Id(deliveryId);
        if (dishes.isEmpty())
            throw new MyServiceException("No dishes found for delivery id: " + deliveryId);
        return dishDTOMapper.toDTOList(dishes);
    }

    /**
     * Trova i piatti che contengono una stringa nel nome.
     * 
     * @param name la stringa da cercare nel nome
     * @return lista di piatti che soddisfano il criterio
     * @throws MyServiceException se non viene trovato nessun piatto
     */
    public List<DishDTO> findByNameContaining(String name) throws MyServiceException
    {
        List<Dish> dishes = dishRepo.findByNameContaining(name);
        if (dishes.isEmpty())
            throw new MyServiceException("No dishes found with name: " + name);
        return dishDTOMapper.toDTOList(dishes);
    }

    /**
     * Salva o aggiorna un piatto.
     * 
     * @param dto i dati del piatto da salvare
     * @return il piatto salvato convertito in DTO
     * @throws MyServiceException se i dati sono nulli
     */
    public DishDTO save(DishDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("DishDTO is null");
        return dishDTOMapper.toDTO(dishRepo.save(dishDTOMapper.toEntity(dto)));
    }

    /**
     * Elimina un piatto per ID.
     * 
     * @param id l'ID del piatto da eliminare
     * @throws MyServiceException se il piatto non esiste
     */
    public void deleteById(int id) throws MyServiceException
    {
        if (!dishRepo.existsById(id))
            throw new MyServiceException("Dish not found with id: " + id);
        dishRepo.deleteById(id);
    }
}
