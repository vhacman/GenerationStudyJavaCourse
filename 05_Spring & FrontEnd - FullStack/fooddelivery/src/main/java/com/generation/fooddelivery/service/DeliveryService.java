package com.generation.fooddelivery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.fooddelivery.api.dto.DeliveryDTO;
import com.generation.fooddelivery.api.dto.mapper.DeliveryDTOMapper;
import com.generation.fooddelivery.model.entities.Customer;
import com.generation.fooddelivery.model.entities.Delivery;
import com.generation.fooddelivery.model.entities.Restaurant;
import com.generation.fooddelivery.model.entities.Rider;
import com.generation.fooddelivery.model.repository.CustomerRepository;
import com.generation.fooddelivery.model.repository.DeliveryRepository;
import com.generation.fooddelivery.model.repository.RestaurantRepository;
import com.generation.fooddelivery.model.repository.RiderRepository;

@Service
public class DeliveryService
{
    @Autowired
    DeliveryRepository deliveryRepo;

    @Autowired
    DeliveryDTOMapper deliveryMapper;

    @Autowired
    RestaurantRepository restaurantRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    RiderRepository riderRepo;

    public DeliveryDTO findById(int id) throws ServiceException
    {
        Optional<Delivery> deliveryOpt = deliveryRepo.findById(id);

        if (deliveryOpt.isEmpty())
            throw new ServiceException("Not found");

        return deliveryMapper.toDto(deliveryOpt.get());
    }

    public DeliveryDTO insert(DeliveryDTO dto) throws ServiceException
    {
        Delivery d = deliveryMapper.toEntity(dto);

        if (d.getStatus() == null || d.getStatus().isBlank())
            d.setStatus("OPEN");

        String email = dto.getCustomerEmail();
        String pw    = dto.getCustomerHash();

        Customer customer = customerRepo.login(email, pw);

        if (customer == null)
            throw new ServiceException("Forbidden", 403);

        if (!customer.canOrder())
            throw new ServiceException("Cannot order, already has a open delivery", 403);

        Restaurant restaurant = findRestaurant(dto.getRestaurantId());
        Rider rider = dto.getRiderId() > 0
            ? findRider(dto.getRiderId())
            : riderRepo.findNearest(
                restaurant.getRestaurantPositionX(),
                restaurant.getRestaurantPositionY(),
                200
            );

        if (restaurant == null)
            throw new ServiceException("Can't find restaurant", 404);

        if (rider == null)
            throw new ServiceException("No suitable rider", 404);

        if (!restaurant.isAvailable())
            throw new ServiceException("Restaurant temporarily unable to handle your request", 400);

        d.setCustomer(customer);
        d.setRestaurant(restaurant);
        d.setRider(rider);

        return deliveryMapper.toDto(deliveryRepo.save(d));
    }

    private Rider findRider(int riderId)
    {
        Optional<Rider> riderOpt = riderRepo.findById(riderId);
        return riderOpt.isPresent() ? riderOpt.get() : null;
    }

    private Restaurant findRestaurant(int restaurantId)
    {
        Optional<Restaurant> restOpt = restaurantRepo.findById(restaurantId);
        return restOpt.isPresent() ? restOpt.get() : null;
    }
}
