package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.service.dto.RiderDTO;

@Component
public class RiderDTOMapper
{
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
		if (rider.getDeliveries() != null)
			dto.setDeliveries(rider.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		return dto;
	}

	public RiderDTO toDTONoCity(Rider rider)
	{
		if (rider == null)
			return null;
		RiderDTO dto = new RiderDTO();
		dto.setId(rider.getId());
		dto.setEmail(rider.getEmail());
		dto.setPw(rider.getPw());
		dto.setLegalName(rider.getLegalName());
		dto.setServiceCost(rider.getServiceCost());
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
