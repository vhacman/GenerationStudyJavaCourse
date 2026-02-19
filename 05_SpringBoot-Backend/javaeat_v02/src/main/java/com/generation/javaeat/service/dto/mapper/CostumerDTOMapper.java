package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.entities.Costumer;
import com.generation.javaeat.service.dto.CityDTO;
import com.generation.javaeat.service.dto.CostumerDTO;

/**
 * Mapper per la conversione tra entita' Costumer e CostumerDTO.
 *
 * NON inietto CityDTOMapper perche' creerebbe una dipendenza circolare:
 *   CostumerDTOMapper -> CityDTOMapper -> CostumerDTOMapper (ciclo!)
 *
 * La City la mappo con un metodo private interno (cityToDTONoRelations)
 * che copia solo id, name, province. Quando chiedo un Costumer mi basta
 * sapere in quale citta' abita, non serve avere tutti i ristoranti e clienti
 * di quella citta'.
 */
@Component
public class CostumerDTOMapper
{
	@Autowired
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
		// Mappo la City inline per evitare il ciclo con CityDTOMapper
		if (costumer.getCity() != null)
			dto.setCity(cityToDTONoRelations(costumer.getCity()));
		if (costumer.getDeliveries() != null)
			dto.setDeliveries(costumer.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		return dto;
	}

	/**
	 * Converte senza la City - usato da CityDTOMapper quando mappa i clienti
	 * di una citta', per evitare di rimettere la City dentro il Costumer.
	 */
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
		// Creo la City inline senza usare CityDTOMapper (evito ciclo)
		if (dto.getCity() != null)
		{
			City city = new City();
			city.setId(dto.getCity().getId());
			city.setName(dto.getCity().getName());
			city.setProvince(dto.getCity().getProvince());
			costumer.setCity(city);
		}
		if (dto.getDeliveries() != null)
			costumer.setDeliveries(dto.getDeliveries().stream().map(deliveryDTOMapper::toEntity).toList());
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

	/**
	 * Mappo la City con solo i dati base (id, name, province) senza le relazioni.
	 * Non uso CityDTOMapper per evitare il ciclo:
	 * CostumerDTOMapper -> CityDTOMapper -> CostumerDTOMapper
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
