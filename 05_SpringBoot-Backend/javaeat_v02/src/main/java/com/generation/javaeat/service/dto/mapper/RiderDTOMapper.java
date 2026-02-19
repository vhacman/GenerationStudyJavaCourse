package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.service.dto.RiderDTO;

/**
 * Mapper per la conversione tra entita' Rider e RiderDTO.
 *
 * Inietto DeliveryDTOMapper perche' mi serve per convertire la lista
 * delle consegne del rider. Non c'e' ciclo perche' DeliveryDTOMapper
 * non inietta RiderDTOMapper (salva solo l'ID del rider).
 *
 * Grafo delle dipendenze (nessun ciclo):
 *   RiderDTOMapper -> DeliveryDTOMapper -> DishDTOMapper -> (nessuno)
 */
@Component
public class RiderDTOMapper
{
	@Autowired
	DeliveryDTOMapper deliveryDTOMapper;

	public RiderDTO toDTO(Rider rider)
	{
		if (rider == null)
			return null;
		RiderDTO dto = new RiderDTO();
		dto.setId(rider.getId());
		dto.setEmail(rider.getEmail());
		dto.setPw(rider.getPw());
		dto.setLegalName(rider.getLegalName());
		dto.setServiceCost(rider.getServiceCost());
		dto.setStatus(rider.getStatus());
		if (rider.getDeliveries() != null)
			dto.setDeliveries(rider.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		return dto;
	}

	public RiderDTO toDTONoRelations(Rider rider)
	{
		if (rider == null)
			return null;
		RiderDTO dto = new RiderDTO();
		dto.setId(rider.getId());
		dto.setEmail(rider.getEmail());
		dto.setPw(rider.getPw());
		dto.setLegalName(rider.getLegalName());
		dto.setServiceCost(rider.getServiceCost());
		dto.setStatus(rider.getStatus());
		return dto;
	}

	public Rider toEntity(RiderDTO dto)
	{
		if (dto == null)
			return null;
		Rider rider = new Rider();
		rider.setId(dto.getId());
		rider.setEmail(dto.getEmail());
		rider.setPw(dto.getPw());
		rider.setLegalName(dto.getLegalName());
		rider.setServiceCost(dto.getServiceCost());
		rider.setStatus(dto.getStatus());
		if (dto.getDeliveries() != null)
			rider.setDeliveries(dto.getDeliveries().stream().map(deliveryDTOMapper::toEntity).toList());
		return rider;
	}

	public Rider toEntityNoRelations(RiderDTO dto)
	{
		if (dto == null)
			return null;
		Rider rider = new Rider();
		rider.setId(dto.getId());
		rider.setEmail(dto.getEmail());
		rider.setPw(dto.getPw());
		rider.setLegalName(dto.getLegalName());
		rider.setServiceCost(dto.getServiceCost());
		rider.setStatus(dto.getStatus());
		return rider;
	}

	public List<RiderDTO> toDTOList(List<Rider> riders)
	{
		return riders.stream().map(this::toDTO).toList();
	}

	public List<Rider> toEntityList(List<RiderDTO> dtos)
	{
		return dtos.stream().map(this::toEntity).toList();
	}
}
