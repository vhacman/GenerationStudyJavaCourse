package com.generation.javaeat.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javaeat.api.dto.DeliveryDTO;
import com.generation.javaeat.api.dto.DeliveryDTOMapper;
import com.generation.javaeat.model.entities.Customer;
import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.CustomerRepository;
import com.generation.javaeat.model.repository.DeliveryRepository;
import com.generation.javaeat.model.repository.RiderRepository;
import com.generation.javaeat.model.repository.RestaurantRepository;

@RestController
@RequestMapping("/javaeat/api/deliveries")
public class DeliveryAPI
{
    @Autowired
    private DeliveryRepository deliveryRepo;
    @Autowired
    private DeliveryDTOMapper deliveryDTOMapper;
    @Autowired
    private RestaurantRepository restaurantRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private RiderRepository riderRepo;
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        Optional<Delivery> deliveryOpt = deliveryRepo.findById(id);
        if (deliveryOpt.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deliveryDTOMapper.toDTO(deliveryOpt.get()));
    }

    @GetMapping
    public ResponseEntity<Object> findAll()
    {
        List<DeliveryDTO> 		deliveries = deliveryRepo.findAll().stream().map(deliveryDTOMapper::toDTO).toList();
        return ResponseEntity.ok(deliveries);
    }

    @PostMapping
    public ResponseEntity<Object> newDelivery(@RequestBody DeliveryDTO dto)
    {
        List<String> errors = new ArrayList<>(dto.getErrors());
        if (dto.getRestaurantId() <= 0)
            errors.add("RestaurantId must be greater than 0");
        if (dto.getCustomerId() <= 0)
            errors.add("CustomerId must be greater than 0");
        if (dto.getRestaurantId() > 0 && restaurantRepo.findById(dto.getRestaurantId()).isEmpty())
            errors.add("Restaurant not found");
        if (dto.getCustomerId() > 0 && customerRepo.findById(dto.getCustomerId()).isEmpty())
            errors.add("Customer not found");
        if (!errors.isEmpty())
            return ResponseEntity.badRequest().body(errors);
        Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId()).orElse(null);
        Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);
        if (customer == null || restaurant == null)
        {
            errors.add("Customer or Restaurant not found");
            return ResponseEntity.badRequest().body(errors);
        }
        if (hasOpenDelivery(customer.getId()))
        {
            errors.add("Customer already has an open delivery");
            return ResponseEntity.badRequest().body(errors);
        }
        if (countOpenDeliveries(restaurant.getId()) >= restaurant.getCapacity())
        {
            errors.add("Restaurant has reached maximum capacity (" + restaurant.getCapacity() + ")");
            return ResponseEntity.badRequest().body(errors);
        }
        Rider rider = findAvailableRider();
        if (rider == null)
        {
            errors.add("No available riders found");
            return ResponseEntity.badRequest().body(errors);
        }
        Delivery delivery = new Delivery();
        delivery.setDescription(dto.getDescription());
        delivery.setPrice(dto.getPrice());
        delivery.setDeliveryTimeOpen(dto.getDay());
        delivery.setRestaurant(restaurant);
        delivery.setCustomer(customer);
        delivery.setRider(rider);
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery = deliveryRepo.save(delivery);
        return ResponseEntity.status(201).body(deliveryDTOMapper.toDTO(delivery));
    }

    private boolean hasOpenDelivery(int customerId)
    {
        return !deliveryRepo.findByCustomerIdAndStatus(customerId, Delivery.STATUS_OPEN).isEmpty();
    }

    private int countOpenDeliveries(int restaurantId)
    {
        return deliveryRepo.findByRestaurantIdAndStatus(restaurantId, Delivery.STATUS_OPEN).size();
    }

    private Rider findAvailableRider()
    {
        return riderRepo.findAll().stream().filter(r -> !hasOpenDelivery(r.getId())) .findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDelivery(@PathVariable("id") int id)
    {
        Optional<Delivery> deliveryOpt = deliveryRepo.findById(id);
        if (deliveryOpt.isEmpty())
            return ResponseEntity.notFound().build();
        deliveryRepo.deleteById(id);
        return ResponseEntity.ok("OK");
    }
}
