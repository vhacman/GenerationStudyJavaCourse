package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.service.dto.CityDTO;

@Component
public class CityDTOMapper
{
	RestaurantDTOMapper restaurantDTOMapper;
	CostumerDTOMapper 	costumerDTOMapper;
	RiderDTOMapper 		riderDTOMapper;
	
	public CityDTO toDTO(City city)
	{
		if(city == null)
			return null;
		CityDTO dto = new CityDTO();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setProvince(city.getProvince());
		if (city.getRestaurants() != null)
			dto.setRestaurants(city.getRestaurants().stream().map(restaurantDTOMapper::toDTONoCity).toList());
		if (city.getCostumers() != null)
			dto.setCostumer(city.getCostumers().stream().map(costumerDTOMapper::toDTONoCity).toList());		
		if (city.getRiders() != null)
			dto.setRiders(city.getRiders().stream().map(riderDTOMapper::toDTONoCity).toList());		
		return dto;
	}	
	
	public City toEntity(CityDTO dto)
	{
		if (dto == null)
			return null;		
		City city = new City();
		city.setId(dto.getId());
		city.setName(dto.getName());
		city.setProvince(dto.getProvince());		
		if (dto.getRestaurants() != null)
			city.setRestaurants(dto.getRestaurants().stream().map(restaurantDTOMapper::toEntityNoRelations).toList());		
		if (dto.getCostumer() != null)
			city.setCostumers(dto.getCostumer().stream().map(costumerDTOMapper::toEntityNoRelations).toList());		
		if (dto.getRiders() != null)
			city.setRiders(dto.getRiders().stream().map(riderDTOMapper::toEntityNoRelations).toList());		
		return city;
	}
	
	public City toEntityNoRelations(CityDTO dto)
	{
		if (dto == null)
			return null;		
		City city = new City();
		city.setId(dto.getId());
		city.setName(dto.getName());
		city.setProvince(dto.getProvince());		
		return city;
	}
	
	public CityDTO toDTOnoRelations(City city)
	{
		if(city == null)
			return null;
		CityDTO dto = new CityDTO();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setProvince(city.getProvince());
		return dto;
		
	}
	
	public List<CityDTO> toDTOList(List<City> cities)
	{
        return cities.stream().map(city -> toDTO(city)).toList();
    }

    public List<City> toEntityList(List<CityDTO> dtos)
    {
        return dtos.stream().map(city -> toEntity(city)).toList();
    }

}
