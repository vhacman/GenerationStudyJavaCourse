package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.entities.Costumer;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.service.dto.CityDTO;
import com.generation.javaeat.service.dto.CostumerDTO;
import com.generation.javaeat.service.dto.RestaurantDTO;

/**
 * Mapper per la conversione tra entita' City e CityDTO.
 *
 * NON inietto RestaurantDTOMapper e CostumerDTOMapper perche' creerebbero
 * una dipendenza circolare con Spring:
 *   CityDTOMapper -> RestaurantDTOMapper -> CityDTOMapper (ciclo!)
 *   CityDTOMapper -> CostumerDTOMapper -> CityDTOMapper (ciclo!)
 *
 * Per evitare il ciclo, mappo Restaurant e Costumer direttamente qui dentro
 * con metodi private che escludono la City (dato che siamo gia' dentro la City).
 * In questo modo ogni mapper dipende solo verso il "basso" (DeliveryDTOMapper, DishDTOMapper)
 * e non si creano cicli.
 */
@Component
public class CityDTOMapper
{
	@Autowired
	DeliveryDTOMapper 	deliveryDTOMapper;
	@Autowired
	DishDTOMapper 		dishDTOMapper;

	public CityDTO toDTO(City city)
	{
		if(city == null)
			return null;
		CityDTO dto = new CityDTO();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setProvince(city.getProvince());
		// Uso metodi private interni per mappare i ristoranti e i clienti
		// senza iniettare RestaurantDTOMapper/CostumerDTOMapper (evito ciclo)
		if (city.getRestaurants() != null)
			dto.setRestaurants(city.getRestaurants().stream().map(this::restaurantToDTONoCity).toList());
		if (city.getCostumers() != null)
			dto.setCostumer(city.getCostumers().stream().map(this::costumerToDTONoCity).toList());
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
        return cities.stream().map(this::toDTO).toList();
    }

    public List<City> toEntityList(List<CityDTO> dtos)
    {
        return dtos.stream().map(this::toEntity).toList();
    }

	/**
	 * Mappo il Restaurant direttamente qui senza la City per evitare il ciclo.
	 * Non posso usare RestaurantDTOMapper perche' lui inietterebbe CityDTOMapper
	 * e Spring non riesce a creare i bean (dipendenza circolare).
	 * Escludo la City perche' siamo gia' dentro la City -> non ha senso rimettercela.
	 */
	private RestaurantDTO restaurantToDTONoCity(Restaurant restaurant)
	{
		if (restaurant == null)
			return null;
		RestaurantDTO dto = new RestaurantDTO();
		dto.setId		(restaurant.getId());
		dto.setName		(restaurant.getName());
		dto.setEmail	(restaurant.getEmail());
		dto.setPw		(restaurant.getPw());
		dto.setAddress	(restaurant.getAddress());
		dto.setCapacity	(restaurant.getCapacity());
		if (restaurant.getDeliveries() != null)
			dto.setDeliveries(restaurant.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		if (restaurant.getDishes() != null)
			dto.setDishes(restaurant.getDishes().stream().map(dishDTOMapper::toDTONoRelations).toList());
		return dto;
	}

	/**
	 * Stessa logica: mappo il Costumer direttamente qui senza la City.
	 * Evito di iniettare CostumerDTOMapper che causerebbe il ciclo.
	 */
	private CostumerDTO costumerToDTONoCity(Costumer costumer)
	{
		if (costumer == null)
			return null;
		CostumerDTO dto = new CostumerDTO();
		dto.setId(costumer.getId());
		dto.setEmail(costumer.getEmail());
		dto.setPw(costumer.getPw());
		dto.setLegalName(costumer.getLegalName());
		dto.setAddress(costumer.getAddress());
		if (costumer.getDeliveries() != null)
			dto.setDeliveries(costumer.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		return dto;
	}
}
