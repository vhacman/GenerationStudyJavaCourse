package com.generation.javaeat.service.dto.mapper;

import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.service.dto.DeliveryDTO;

@Component
public class DeliveryDTOMapper
{
	RestaurantDTOMapper restaurantDTOMapper;
	CostumerDTOMapper costumerDTOMapper;
	RiderDTOMapper riderDTOMapper;

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
		if (delivery.getRestaurant() != null)
			dto.setRestaurantId(delivery.getRestaurant().getId());
		if (delivery.getCostumer() != null)
			dto.setCostumerId(delivery.getCostumer().getId());
		if (delivery.getRider() != null)
			dto.setRiderId(delivery.getRider().getId());
		return dto;
	}

	public DeliveryDTO toDTONoRelations(Delivery delivery)
	{
		if (delivery == null)
			return null;
		DeliveryDTO dto = new DeliveryDTO();
		dto.setId(delivery.getId());
		dto.setDescription(delivery.getDescription());
		dto.setStatus(delivery.getStatus());
		dto.setPrice(delivery.getPrice());
		dto.setDay(delivery.getDeliveryTimeOpen());
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
		return delivery;
	}

	public Delivery toEntityNoRelations(DeliveryDTO dto)
	{
		if (dto == null)
			return null;
		Delivery delivery = new Delivery();
		delivery.setId(dto.getId());
		delivery.setDescription(dto.getDescription());
		delivery.setStatus(dto.getStatus());
		delivery.setPrice(dto.getPrice());
		delivery.setDeliveryTimeOpen(dto.getDay());
		return delivery;
	}
}
