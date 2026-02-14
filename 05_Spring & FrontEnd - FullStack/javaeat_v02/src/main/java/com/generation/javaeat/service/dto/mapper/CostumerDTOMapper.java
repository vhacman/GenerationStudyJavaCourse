package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.Costumer;
import com.generation.javaeat.service.dto.CostumerDTO;

@Component
public class CostumerDTOMapper
{
	CityDTOMapper cityDTOMapper;
	DeliveryDTOMapper deliveryDTOMapper;
	
	public CostumerDTO toDTO(Costumer costumer)
	{
		if (costumer == null)
			return null;
		CostumerDTO dto = new CostumerDTO();
		dto.setId(costumer.getId());
		dto.setEmail(costumer.getEmail());
		dto.setPw(costumer.getPw());
		dto.setLegalName(costumer.getLegalName());
		dto.setAddress(costumer.getAddress());
		if (costumer.getCity() != null)
			dto.setCity(cityDTOMapper.toDTO(costumer.getCity()));
		if (costumer.getDeliveries() != null)
			dto.setDeliveries(costumer.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		dto.setRiderId(costumer.getRiderId());
		return dto;
	}
	
	public CostumerDTO toDTONoCity(Costumer costumer)
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
		dto.setRiderId(costumer.getRiderId());
		return dto;
	}
	
	public CostumerDTO toDTONoRelations(Costumer costumer)
	{
		if (costumer == null)
			return null;
		CostumerDTO dto = new CostumerDTO();
		dto.setId(costumer.getId());
		dto.setEmail(costumer.getEmail());
		dto.setPw(costumer.getPw());
		dto.setLegalName(costumer.getLegalName());
		dto.setAddress(costumer.getAddress());
		return dto;
	}
	
	public Costumer toEntity(CostumerDTO dto)
	{
		if (dto == null)
			return null;
		Costumer costumer = new Costumer();
		costumer.setId(dto.getId());
		costumer.setEmail(dto.getEmail());
		costumer.setPw(dto.getPw());
		costumer.setLegalName(dto.getLegalName());
		costumer.setAddress(dto.getAddress());
		if (dto.getCity() != null)
			costumer.setCity(cityDTOMapper.toEntityNoRelations(dto.getCity()));
		if (dto.getDeliveries() != null)
			costumer.setDeliveries(dto.getDeliveries().stream().map(deliveryDTOMapper::toEntityNoRelations).toList());
		costumer.setRiderId(dto.getRiderId());
		return costumer;
	}
	
	public Costumer toEntityNoRelations(CostumerDTO dto)
	{
		if (dto == null)
			return null;
		Costumer costumer = new Costumer();
		costumer.setId(dto.getId());
		costumer.setEmail(dto.getEmail());
		costumer.setPw(dto.getPw());
		costumer.setLegalName(dto.getLegalName());
		costumer.setAddress(dto.getAddress());
		return costumer;
	}
	
	public List<CostumerDTO> toDTOList(List<Costumer> costumers)
	{
		return costumers.stream().map(this::toDTO).toList();
	}
	
	public List<Costumer> toEntityList(List<CostumerDTO> dtos)
	{
		return dtos.stream().map(this::toEntity).toList();
	}
}
