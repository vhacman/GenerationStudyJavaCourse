package com.generation.javaeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.RestaurantRepository;
import com.generation.javaeat.service.dto.RestaurantDTO;
import com.generation.javaeat.service.dto.mapper.RestaurantDTOMapper;

@Service
public class RestaurantService
{
	@Autowired
    private RestaurantRepository     restaurantRepo;
    @Autowired
    private RestaurantDTOMapper     restaurantDTOMapper;
    
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
    
    public List<RestaurantDTO> findAll()
    {
        return restaurantDTOMapper.toDTOList(restaurantRepo.findAll());
    }
    
    public RestaurantDTO findById(int id) throws MyServiceException
    {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant == null)
            throw new MyServiceException("Restaurant not found with id: " + id);
        return restaurantDTOMapper.toDTO(restaurant);
    }
    
    public List<RestaurantDTO> findByNameContaining(String name) throws MyServiceException
    {
        List<Restaurant> restaurants = restaurantRepo.findByNameContaining(name);
        if (restaurants.isEmpty())
            throw new MyServiceException("Restaurant not found with name: " + name);
        return restaurantDTOMapper.toDTOList(restaurants);
    }
    
    public List<RestaurantDTO> findByCityId(int cityId) throws MyServiceException
    {
        List<Restaurant> restaurants = restaurantRepo.findByCityId(cityId);
        if (restaurants.isEmpty())
            throw new MyServiceException("Restaurant not found with cityId: " + cityId);
        return restaurantDTOMapper.toDTOList(restaurants);
    }
    
    public RestaurantDTO save(RestaurantDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("RestaurantDTO is null");
        return restaurantDTOMapper.toDTO(restaurantRepo.save(restaurantDTOMapper.toEntity(dto)));
    }
    
    public void deleteById(int id) throws MyServiceException
    {
        if (!restaurantRepo.existsById(id))
            throw new MyServiceException("Restaurant not found with id: " + id);
        restaurantRepo.deleteById(id);
    }
    
}
