package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.service.dto.CityDTO;
import com.generation.javaeat.service.dto.RestaurantDTO;

/**
 * Mapper per la conversione tra entita' Restaurant e RestaurantDTO.
 *
 * NON inietto CityDTOMapper perche' creerebbe una dipendenza circolare:
 *   RestaurantDTOMapper -> CityDTOMapper -> RestaurantDTOMapper (ciclo!)
 *
 * La City la mappo con un metodo private interno (cityToDTONoRelations)
 * che copia solo id, name, province - senza le relazioni della citta'.
 * Questo e' sufficiente perche' quando chiedo un Restaurant voglio sapere
 * IN QUALE citta' si trova, non tutti i ristoranti e clienti di quella citta'.
 */
@Component
public class RestaurantDTOMapper
{
	@Autowired
	DeliveryDTOMapper 	deliveryDTOMapper;
	@Autowired
	DishDTOMapper 		dishDTOMapper;

	public RestaurantDTO toDTO(Restaurant restaurant)
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
		// Mappo la City inline per evitare il ciclo con CityDTOMapper
		if (restaurant.getCity() != null)
			dto.setCity(cityToDTONoRelations(restaurant.getCity()));
		if (restaurant.getDeliveries() != null)
			dto.setDeliveries(restaurant.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		if (restaurant.getDishes() != null)
			dto.setDishes(restaurant.getDishes().stream().map(dishDTOMapper::toDTONoRelations).toList());
		return dto;
	}

	/**
	 * Converte senza la City - usato da CityDTOMapper quando mappa i ristoranti
	 * di una citta', per evitare di rimettere la City dentro il Restaurant.
	 */
	public RestaurantDTO toDTONoCity(Restaurant restaurant)
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

	public RestaurantDTO toDTONoRelations(Restaurant restaurant)
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
		return dto;
	}

	public Restaurant toEntity(RestaurantDTO dto)
	{
		if (dto == null)
			return null;
		Restaurant restaurant = new Restaurant();
		restaurant.setId		(dto.getId());
		restaurant.setName		(dto.getName());
		restaurant.setEmail		(dto.getEmail());
		restaurant.setPw		(dto.getPw());
		restaurant.setAddress	(dto.getAddress());
		restaurant.setCapacity	(dto.getCapacity());
		// Creo la City inline senza usare CityDTOMapper (evito ciclo)
		if (dto.getCity() != null)
		{
			City city = new City();
			city.setId(dto.getCity().getId());
			city.setName(dto.getCity().getName());
			city.setProvince(dto.getCity().getProvince());
			restaurant.setCity(city);
		}
		if (dto.getDeliveries() != null)
			restaurant.setDeliveries(dto.getDeliveries().stream().map(deliveryDTOMapper::toEntity).toList());
		return restaurant;
	}

	public Restaurant toEntityNoRelations(RestaurantDTO dto)
	{
		if (dto == null)
			return null;
		Restaurant restaurant = new Restaurant();
		restaurant.setId		(dto.getId());
		restaurant.setName		(dto.getName());
		restaurant.setEmail		(dto.getEmail());
		restaurant.setPw		(dto.getPw());
		restaurant.setAddress	(dto.getAddress());
		restaurant.setCapacity	(dto.getCapacity());
		return restaurant;
	}

	public List<RestaurantDTO> toDTOList(List<Restaurant> restaurants)
	{
		return restaurants.stream().map(this::toDTO).toList();
	}

	public List<Restaurant> toEntityList(List<RestaurantDTO> dtos)
	{
		return dtos.stream().map(this::toEntity).toList();
	}

	/**
	 * Mappo la City con solo i dati base (id, name, province) senza le relazioni.
	 * Non uso CityDTOMapper per evitare il ciclo:
	 * RestaurantDTOMapper -> CityDTOMapper -> RestaurantDTOMapper
	 */
	private CityDTO cityToDTONoRelations(City city)
	{
		if (city == null)
			return null;
		CityDTO dto = new CityDTO();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setProvince(city.getProvince());
		return dto;
	}
}
