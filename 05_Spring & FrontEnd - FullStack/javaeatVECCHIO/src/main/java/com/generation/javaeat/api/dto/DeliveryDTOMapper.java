package com.generation.javaeat.api.dto;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.Customer;
import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.CustomerRepository;
import com.generation.javaeat.model.repository.RiderRepository;
import com.generation.javaeat.model.repository.RestaurantRepository;

@Service
public class DeliveryDTOMapper
{
    @Autowired
    private RestaurantRepository restaurantRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private RiderRepository riderRepo;

    public Delivery fromDTO(DeliveryDTO dto)
    {
        Delivery d = new Delivery();
        d.setId(dto.getId());
        d.setDescription(dto.getDescription());
        d.setStatus(dto.getStatus());
        d.setPrice(dto.getPrice());
        d.setDeliveryTimeOpen(dto.getDay());
        if (dto.getRestaurantId() > 0)
        {
            Optional<Restaurant> restOpt = restaurantRepo.findById(dto.getRestaurantId());
            restOpt.ifPresent(d::setRestaurant);
        }
        if (dto.getCustomerId() > 0)
        {
            Optional<Customer> custOpt = customerRepo.findById(dto.getCustomerId());
            custOpt.ifPresent(d::setCustomer);
        }
        if (dto.getRiderId() > 0)
        {
            Optional<Rider> riderOpt = riderRepo.findById(dto.getRiderId());
            riderOpt.ifPresent(d::setRider);
        }
        return d;
    }

    public DeliveryDTO toDTO(Delivery delivery)
    {
        DeliveryDTO d = new DeliveryDTO();
        d.setId(delivery.getId());
        d.setDescription(delivery.getDescription());
        d.setStatus(delivery.getStatus());
        d.setPrice(delivery.getPrice());
        d.setDay(delivery.getDeliveryTimeOpen());
        if (delivery.getRestaurant() != null)
            d.setRestaurantId(delivery.getRestaurant().getId());
        if (delivery.getCustomer() != null)
            d.setCustomerId(delivery.getCustomer().getId());
        if (delivery.getRider() != null)
            d.setRiderId(delivery.getRider().getId());
        return d;
    }
}
