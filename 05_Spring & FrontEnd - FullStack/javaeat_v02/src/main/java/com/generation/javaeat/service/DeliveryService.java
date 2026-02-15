package com.generation.javaeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.generation.javaeat.model.entities.Costumer;
import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.model.repository.CostumerRepository;
import com.generation.javaeat.model.repository.DeliveryRepository;
import com.generation.javaeat.model.repository.RestaurantRepository;
import com.generation.javaeat.model.repository.RiderRepository;
import com.generation.javaeat.service.dto.DeliveryDTO;
import com.generation.javaeat.service.dto.mapper.DeliveryDTOMapper;

/**
 * Service per la gestione delle operazioni sulle consegne.
 * 
 * CAMBI DALLE VECCHIE ENTITA':
 * - Aggiunto campo dishes: la relazione Delivery-Dish e' ora ManyToMany.
 *   Una consegna puo' contenere piu' piatti e uno stesso piatto puo' comparire in piu' consegne.
 * - Il DTO ora include una lista di DishDTO per rappresentare i piatti ordinati.
 * - NOTA: Quando si crea una nuova consegna, i piatti vanno aggiunti separatamente
 *   tramite il servizio di consegna o direttamente sul repository.
 */
@Service
public class DeliveryService
{
    @Autowired
    private DeliveryRepository   deliveryRepo;
    @Autowired
    private DeliveryDTOMapper    deliveryDTOMapper;
    @Autowired
    private RestaurantRepository restaurantRepo;
    @Autowired
    private CostumerRepository   costumerRepo;
    @Autowired
    private RiderRepository      riderRepo;

    /**
     * Inserisce una nuova consegna nel database.
     * 
     * @param dto i dati della consegna da inserire
     * @return la consegna inserita convertita in DTO
     * @throws MyServiceException se i dati sono nulli o non validi
     */
    public DeliveryDTO insert(DeliveryDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("DeliveryDTO is null");
        if (dto.getStatus() == null || dto.getStatus().trim().isEmpty())
            throw new MyServiceException("Status is required");
        if (dto.getPrice() < 0)
            throw new MyServiceException("Price cannot be negative");
        
        Delivery delivery = deliveryDTOMapper.toEntity(dto);
        
        if (dto.getRestaurantId() > 0) {
            Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId()).orElse(null);
            if (restaurant != null)
                delivery.setRestaurant(restaurant);
        }
        if (dto.getCostumerId() > 0) {
            Costumer costumer = costumerRepo.findById(dto.getCostumerId()).orElse(null);
            if (costumer != null)
                delivery.setCostumer(costumer);
        }
        if (dto.getRiderId() > 0) {
            Rider rider = riderRepo.findById(dto.getRiderId()).orElse(null);
            if (rider != null)
                delivery.setRider(rider);
        }
        
        delivery = deliveryRepo.save(delivery);
        return deliveryDTOMapper.toDTO(delivery);
    }

    /**
     * Restituisce tutte le consegne presenti nel database.
     * 
     * @return lista di tutte le consegne convertite in DTO
     */
    public List<DeliveryDTO> findAll()
    {
        return deliveryDTOMapper.toDTOList(deliveryRepo.findAll());
    }

    /**
     * Trova una consegna per ID.
     * 
     * @param id l'ID della consegna da cercare
     * @return la consegna trovata convertita in DTO
     * @throws MyServiceException se la consegna non viene trovata
     */
    public DeliveryDTO findById(int id) throws MyServiceException
    {
        Delivery delivery = deliveryRepo.findById(id).orElse(null);
        if (delivery == null)
            throw new MyServiceException("Delivery not found with id: " + id);
        return deliveryDTOMapper.toDTO(delivery);
    }

    /**
     * Trova le consegne associate a un specifico ristorante.
     * 
     * @param restaurantId l'ID del ristorante
     * @return lista di consegne del ristorante
     * @throws MyServiceException se non viene trovata nessuna consegna
     */
    public List<DeliveryDTO> findByRestaurantId(int restaurantId) throws MyServiceException
    {
        List<Delivery> deliveries = deliveryRepo.findByRestaurantId(restaurantId);
        if (deliveries.isEmpty())
            throw new MyServiceException("No deliveries found for restaurant id: " + restaurantId);
        return deliveryDTOMapper.toDTOList(deliveries);
    }

    /**
     * Trova le consegne associate a un specifico cliente.
     * 
     * @param costumerId l'id del cliente
     * @return lista di consegne del cliente
     * @throws MyServiceException se non viene trovata nessuna consegna
     */
    public List<DeliveryDTO> findByCostumerId(int costumerId) throws MyServiceException
    {
        List<Delivery> deliveries = deliveryRepo.findByCostumerId(costumerId);
        if (deliveries.isEmpty())
            throw new MyServiceException("No deliveries found for costumer id: " + costumerId);
        return deliveryDTOMapper.toDTOList(deliveries);
    }

    /**
     * Trova le consegne assegnate a un specifico rider.
     * 
     * @param riderId l'id del rider
     * @return lista di consegne del rider
     * @throws MyServiceException se non viene trovata nessuna consegna
     */
    public List<DeliveryDTO> findByRiderId(int riderId) throws MyServiceException
    {
        List<Delivery> deliveries = deliveryRepo.findByRiderId(riderId);
        if (deliveries.isEmpty())
            throw new MyServiceException("No deliveries found for rider id: " + riderId);
        return deliveryDTOMapper.toDTOList(deliveries);
    }

    /**
     * Trova le consegne di un cliente con uno specifico stato.
     * 
     * @param costumerId l'ID del cliente
     * @param status lo stato della consegna
     * @return lista di consegne che soddisfano i criteri
     * @throws MyServiceException se non viene trovata nessuna consegna
     */
    public List<DeliveryDTO> findByCostumerIdAndStatus(int costumerId, String status) throws MyServiceException
    {
        List<Delivery> deliveries = deliveryRepo.findByCostumerIdAndStatus(costumerId, status);
        if (deliveries.isEmpty())
            throw new MyServiceException("No deliveries found for costumer id: " + costumerId + " with status: " + status);
        return deliveryDTOMapper.toDTOList(deliveries);
    }

    /**
     * Trova le consegne di un ristorante con uno specifico stato.
     * 
     * @param restaurantId l'ID del ristorante
     * @param status lo stato della consegna
     * @return lista di consegne che soddisfano i criteri
     * @throws MyServiceException se non viene trovata nessuna consegna
     */
    public List<DeliveryDTO> findByRestaurantIdAndStatus(int restaurantId, String status) throws MyServiceException
    {
        List<Delivery> deliveries = deliveryRepo.findByRestaurantIdAndStatus(restaurantId, status);
        if (deliveries.isEmpty())
            throw new MyServiceException("No deliveries found for restaurant id: " + restaurantId + " with status: " + status);
        return deliveryDTOMapper.toDTOList(deliveries);
    }

    /**
     * Trova le consegne di un rider con uno specifico stato.
     * 
     * @param riderId l'ID del rider
     * @param status lo stato della consegna
     * @return lista di consegne che soddisfano i criteri
     * @throws MyServiceException se non viene trovata nessuna consegna
     */
    public List<DeliveryDTO> findByRiderIdAndStatus(int riderId, String status) throws MyServiceException
    {
        List<Delivery> deliveries = deliveryRepo.findByRiderIdAndStatus(riderId, status);
        if (deliveries.isEmpty())
            throw new MyServiceException("No deliveries found for rider id: " + riderId + " with status: " + status);
        return deliveryDTOMapper.toDTOList(deliveries);
    }

    /**
     * Conta le consegne di un cliente.
     * 
     * @param costumerId l'ID del cliente
     * @return numero di consegne del cliente
     */
    public int countByCostumerId(int costumerId)
    {
        return deliveryRepo.countByCostumerId(costumerId);
    }

    /**
     * Conta le consegne di un ristorante.
     * 
     * @param restaurantId l'ID del ristorante
     * @return numero di consegne del ristorante
     */
    public int countByRestaurantId(int restaurantId)
    {
        return deliveryRepo.countByRestaurantId(restaurantId);
    }

    /**
     * Conta le consegne di un rider.
     * 
     * @param riderId l'ID del rider
     * @return numero di consegne del rider
     */
    public int countByRiderId(int riderId)
    {
        return deliveryRepo.countByRiderId(riderId);
    }

    /**
     * Conta le consegne con uno specifico stato.
     * 
     * @param status lo stato della consegna
     * @return numero di consegne con lo stato specificato
     */
    public int countByStatus(String status)
    {
        return deliveryRepo.countByStatus(status);
    }

    /**
     * Salva o aggiorna una consegna.
     * 
     * @param dto i dati della consegna da salvare
     * @return la consegna salvata convertita in DTO
     * @throws MyServiceException se i dati sono nulli
     */
    public DeliveryDTO save(DeliveryDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("DeliveryDTO is null");
        return deliveryDTOMapper.toDTO(deliveryRepo.save(deliveryDTOMapper.toEntity(dto)));
    }

    /**
     * Elimina una consegna per ID.
     * 
     * @param id l'ID della consegna da eliminare
     * @throws MyServiceException se la consegna non esiste
     */
    public void deleteById(int id) throws MyServiceException
    {
        if (!deliveryRepo.existsById(id))
            throw new MyServiceException("Delivery not found with id: " + id);
        deliveryRepo.deleteById(id);
    }
}
