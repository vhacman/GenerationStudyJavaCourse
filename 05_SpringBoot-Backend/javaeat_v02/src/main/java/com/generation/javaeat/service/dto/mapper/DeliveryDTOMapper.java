package com.generation.javaeat.service.dto.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.service.dto.DeliveryDTO;

/**
 * Mapper per la conversione tra entita' Delivery e DeliveryDTO.
 *
 * Inietto solo DishDTOMapper perche' e' l'unico che mi serve davvero:
 * per Restaurant, Costumer e Rider mi basta salvare l'ID nel DTO,
 * non ho bisogno di convertire l'intero oggetto.
 *
 * In questo modo evito di iniettare RestaurantDTOMapper, CostumerDTOMapper
 * e RiderDTOMapper che creerebbero dipendenze circolari
 * (es. RiderDTOMapper -> DeliveryDTOMapper -> RiderDTOMapper).
 */
@Component
public class DeliveryDTOMapper
{
	@Autowired
	DishDTOMapper 			dishDTOMapper;

	public DeliveryDTO toDTO(Delivery delivery)
	{
		if (delivery == null)
			return null;
		DeliveryDTO dto = new DeliveryDTO();
		dto.setId(delivery.getId());
		dto.setDescription(delivery.getDescription());
		dto.setStatus(delivery.getStatus());
		dto.setPrice(delivery.getPrice());
		dto.setDay(delivery.getDeliveryTimeOpen());
		// Per Restaurant, Costumer e Rider salvo solo l'ID
		// non serve iniettare i loro mapper (evito cicli)
		if (delivery.getRestaurant() != null)
			dto.setRestaurantId(delivery.getRestaurant().getId());
		if (delivery.getCostumer() != null)
			dto.setCostumerId(delivery.getCostumer().getId());
		if (delivery.getRider() != null)
			dto.setRiderId(delivery.getRider().getId());
		// Per i Dish invece uso il mapper perche' nel DTO voglio i dati del piatto
		if (delivery.getDishes() != null)
			dto.setDishes(delivery.getDishes().stream().map(dishDTOMapper::toDTONoRelations).toList());
		return dto;
	}

	public Delivery toEntity(DeliveryDTO dto)
	{
		if (dto == null)
			return null;
		Delivery delivery = new Delivery();
		delivery.setId(dto.getId());
		delivery.setDescription(dto.getDescription());
		delivery.setStatus(dto.getStatus());
		delivery.setPrice(dto.getPrice());
		delivery.setDeliveryTimeOpen(dto.getDay());
		// Le relazioni con Restaurant, Costumer e Rider vengono gestite dal Service
		// perche' nel DTO ho solo gli ID, non gli oggetti completi
		return delivery;
	}

	public List<DeliveryDTO> toDTOList(List<Delivery> deliveries)
	{
		return deliveries.stream().map(this::toDTO).toList();
	}

	public List<Delivery> toEntityList(List<DeliveryDTO> dtos)
	{
		return dtos.stream().map(this::toEntity).toList();
	}
}
