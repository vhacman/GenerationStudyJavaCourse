package com.generation.javaeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.RestaurantRepository;
import com.generation.javaeat.service.dto.RestaurantDTO;
import com.generation.javaeat.service.dto.mapper.RestaurantDTOMapper;

/**
 * Service per la gestione delle operazioni sui ristoranti.
 * 
 * CAMBI DALLE VECCHIE ENTITA':
 * - Aggiunto metodo findByIdWithDishes: restituisce il ristorante con i piatti del menu.
 *   Questo perche' ora Restaurant ha una relazione 1:M con Dish (menu del ristorante).
 * - I metodi toDTO ora includono automaticamente i piatti del menu grazie al mapper aggiornato.
 */
@Service
public class RestaurantService
{
	@Autowired
    private RestaurantRepository     restaurantRepo;
    @Autowired
    private RestaurantDTOMapper      restaurantDTOMapper;
    
    /**
     * Inserisce un nuovo ristorante nel database.
     * 
     * @param dto i dati del ristorante da inserire
     * @return il ristorante inserito convertito in DTO
     * @throws MyServiceException se i dati sono nulli o non validi
     */
    public RestaurantDTO insert(RestaurantDTO dto) throws MyServiceException
    {
    	if (dto == null)
            throw new MyServiceException("RestaurantDTO is null");
        if (dto.getName() == null || dto.getName().trim().isEmpty())
            throw new MyServiceException("Restaurant name is required");
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            throw new MyServiceException("Email is required");
        if (dto.getPw() == null || dto.getPw().trim().isEmpty())
            throw new MyServiceException("Password is required");
        if (dto.getAddress() == null || dto.getAddress().trim().isEmpty())
            throw new MyServiceException("Address is required");
        if (dto.getCapacity() < 0)
            throw new MyServiceException("Capacity cannot be negative");
        if (dto.getCity() == null)
            throw new MyServiceException("City is required");
        Restaurant restaurant = restaurantDTOMapper.toEntity(dto);
        restaurant = restaurantRepo.save(restaurant);
        return restaurantDTOMapper.toDTO(restaurant);
    }
    
    /**
     * Restituisce tutti i ristoranti presenti nel database.
     * 
     * @return lista di tutti i ristoranti convertiti in DTO
     */
    public List<RestaurantDTO> findAll()
    {
        return restaurantDTOMapper.toDTOList(restaurantRepo.findAll());
    }
    
    /**
     * Trova un ristorante per ID.
     * 
     * @param id l'ID del ristorante da cercare
     * @return il ristorante trovato convertito in DTO
     * @throws MyServiceException se il ristorante non viene trovato
     */
    public RestaurantDTO findById(int id) throws MyServiceException
    {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant == null)
            throw new MyServiceException("Restaurant not found with id: " + id);
        return restaurantDTOMapper.toDTO(restaurant);
    }
    
    /**
     * Trova i ristoranti che contengono una stringa nel nome.
     * 
     * @param name la stringa da cercare nel nome
     * @return lista di ristoranti che soddisfano il criterio
     * @throws MyServiceException se non viene trovato nessun ristorante
     */
    public List<RestaurantDTO> findByNameContaining(String name) throws MyServiceException
    {
        List<Restaurant> restaurants = restaurantRepo.findByNameContaining(name);
        if (restaurants.isEmpty())
            throw new MyServiceException("Restaurant not found with name: " + name);
        return restaurantDTOMapper.toDTOList(restaurants);
    }
    
    /**
     * Trova i ristoranti appartenenti a una specifica citta'.
     * 
     * @param cityId l'ID della citta'
     * @return lista di ristoranti nella citta' specificata
     * @throws MyServiceException se non viene trovato nessun ristorante
     */
    public List<RestaurantDTO> findByCityId(int cityId) throws MyServiceException
    {
        List<Restaurant> restaurants = restaurantRepo.findByCityId(cityId);
        if (restaurants.isEmpty())
            throw new MyServiceException("Restaurant not found with cityId: " + cityId);
        return restaurantDTOMapper.toDTOList(restaurants);
    }
    
    /**
     * Salva o aggiorna un ristorante.
     * 
     * @param dto i dati del ristorante da salvare
     * @return il ristorante salvato convertito in DTO
     * @throws MyServiceException se i dati sono nulli
     */
    public RestaurantDTO save(RestaurantDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("RestaurantDTO is null");
        return restaurantDTOMapper.toDTO(restaurantRepo.save(restaurantDTOMapper.toEntity(dto)));
    }
    
    /**
     * Elimina un ristorante per ID.
     * 
     * @param id l'ID del ristorante da eliminare
     * @throws MyServiceException se il ristorante non esiste
     */
    public void deleteById(int id) throws MyServiceException
    {
        if (!restaurantRepo.existsById(id))
            throw new MyServiceException("Restaurant not found with id: " + id);
        restaurantRepo.deleteById(id);
    }
    
}
