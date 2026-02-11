package com.generation.javaeat.api.dto;

import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Service; 
import com.generation.javaeat.model.entities.City;
/**
 * La classe CityDTOMapper implementa la logica di conversione tra l'entità City
 * e il corrispondente DTO CityDTO. La separazione tra entità e DTO è una pratica
 * comune nelle applicazioni enterprise che permette di mantenere il modello di dominio
 * indipendente dalla rappresentazione utilizzata nelle comunicazioni di rete.
 * Questa classe è annotata come @Service, indicandone il ruolo di componente
 * business che può essere iniettato come dipendenza in altri componenti Spring.
 *
 * I metodi di conversione implementati permettono di trasformare oggetti da un
 * formato all'altro in modo bidirezionale, garantendo che i dati possano fluire
 * correttamente tra il database, il backend e il frontend dell'applicazione.
 */
    @Service
    public class CityDTOMapper
    {
        private RestaurantDTOMapper restaurantDTOMapper;

        public CityDTOMapper(RestaurantDTOMapper restaurantDTOMapper)
        {
            this.restaurantDTOMapper = restaurantDTOMapper;
        }

        public CityDTO toDTO(City city)
        {
            if (city == null) return null;
            CityDTO dto = new CityDTO();
            dto.setId(city.getId());
            dto.setName(city.getName());
            dto.setProvince(city.getProvince());
            if (city.getRestaurants() != null)
                dto.setRestaurants(city.getRestaurants().stream().map(restaurantDTOMapper::toDTO).toList());
            return dto;
        }

        public City fromDTO(CityDTO dto)
        {
            if (dto == null) return null;
            City city = new City();
            city.setId(dto.getId());
            city.setName(dto.getName());
            city.setProvince(dto.getProvince());
            return city;
        }

        public List<CityDTO> toDTO(List<City> cities)
        {
            if(cities != null) 
                return cities.stream().map(this::toDTO).toList(); 
            return new ArrayList<>();
        }

        public List<City> fromDTO(List<CityDTO> citiesDTO)
        {
            if(citiesDTO != null) 
                return citiesDTO.stream().map(this::fromDTO).toList(); 
            return new ArrayList<>();
        }
    }
