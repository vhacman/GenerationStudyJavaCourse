package com.generation.javaeat.api.dto;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.repository.CityRepository;

@Service
public class RestaurantDTOMapper
{

    @Autowired
    private CityRepository   cityRepo;

    @Autowired
    private DeliveryDTOMapper deliveryDTOMapper;

    public Restaurant fromDTO(RestaurantDTO dto)
    {
        Restaurant r = new Restaurant();
        r.setId(dto.getId());
        r.setName(dto.getName());
        r.setAddress(dto.getAddress());
        r.setCapacity(dto.getCapacity());
        r.setEmail(dto.getEmail());
        r.setPw(dto.getPw());

        if (dto.getCity() != null)
        {
            if (dto.getCity().getId() > 0)
            {
                Optional<City> cityOpt = cityRepo.findById(dto.getCity().getId());
                if (cityOpt.isPresent())
                    r.setCity(cityOpt.get());
            }
            else if (dto.getCity().getName() != null)
                cityRepo.findByName(dto.getCity().getName()).ifPresent(r::setCity);
        }
        return r;
    }

    public RestaurantDTO toDTO(Restaurant restaurant)
    {
        RestaurantDTO r = new RestaurantDTO();
        r.setId(restaurant.getId());
        r.setAddress(restaurant.getAddress());
        r.setEmail(restaurant.getEmail());
        r.setName(restaurant.getName());
        r.setCapacity(restaurant.getCapacity());
        if (restaurant.getCity() != null)
        {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(restaurant.getCity().getId());
            cityDTO.setName(restaurant.getCity().getName());
            cityDTO.setProvince(restaurant.getCity().getProvince());
            r.setCity(cityDTO);
        }
        if (restaurant.getDeliveries() != null) 
            r.setDeliveries(restaurant.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList()); 
        return r;
    }
}
