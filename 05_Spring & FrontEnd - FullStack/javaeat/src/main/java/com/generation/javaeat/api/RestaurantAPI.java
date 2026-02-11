package com.generation.javaeat.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javaeat.api.dto.RestaurantDTO;
import com.generation.javaeat.api.dto.RestaurantDTOMapper;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.CityRepository;
import com.generation.javaeat.model.repository.RestaurantRepository;

@RestController
@RequestMapping("/javaeat/api/restaurants")
public class RestaurantAPI
{
    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private RestaurantDTOMapper restaurantDTOMapper;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private MD5Hasher hasher;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        Optional<Restaurant> restaurantOpt = restaurantRepo.findById(id);

        if (restaurantOpt.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(restaurantDTOMapper.toDTO(restaurantOpt.get()));
    }

    @PostMapping
    public ResponseEntity<Object> newRestaurant(@RequestBody RestaurantDTO dto)
    {
        List<String> errors = new ArrayList<>(dto.getErrors());

        if (dto.getCity() == null || dto.getCity().getId() <= 0)
            errors.add("City must be specified with valid ID");

        if (dto.getCity() != null && dto.getCity().getId() > 0 && cityRepo.findById(dto.getCity().getId()).isEmpty())
            errors.add("City not found");

        if (!errors.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        Restaurant restaurant = restaurantDTOMapper.fromDTO(dto);

        String passwordHash = hasher.hash(restaurant.getPw());
        restaurant.setPw(passwordHash);

        restaurant = restaurantRepo.save(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDTOMapper.toDTO(restaurant));
    }
}
