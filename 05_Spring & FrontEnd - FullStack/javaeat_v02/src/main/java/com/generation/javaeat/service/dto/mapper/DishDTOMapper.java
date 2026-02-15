package com.generation.javaeat.service.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.model.entities.Dish;
import com.generation.javaeat.service.dto.DishDTO;

/**
 * Mapper per la conversione tra entita' Dish e DishDTO.
 * 
 * CAMBI DALLE VECCHIE ENTITA':
 * - Cambiato campo deliveryId (singolo) in deliveryIds (lista): la relazione Dish-Delivery
 *   e' passata da ManyToOne a ManyToMany.
 *   Prima: un piatto apparteneva a UNA sola consegna (errato semanticamente)
 *   Ora: un piatto puo' comparire in PIU' consegne (corretto: stesso piatto, ordini diversi)
 * - Il ristorante resta come relazione ManyToOne (un piatto appartiene a un ristorante)
 */
@Component
public class DishDTOMapper
{
	/**
	 * Converte un'entita' Dish in un DishDTO.
	 * Include il ristorante proprietario e la lista delle consegne che includono questo piatto.
	 * 
	 * CAMBI DALLA VECCHIA VERSIONE:
	 * - Cambiato: da dto.setDeliveryId(dish.getDelivery().getId()) (singolo)
	 *   a dto.setDeliveryIds(dish.getDeliveries().stream().map(Delivery::getId).toList()) (lista)
	 *   Perche' ora la relazione e' ManyToMany, non piu' ManyToOne.
	 * 
	 * @param dish l'entita' Dish da convertire (puo' essere null)
	 * @return il DishDTO con tutte le informazioni
	 */
	public DishDTO toDTO(Dish dish)
	{
		if (dish == null)
			return null;
		DishDTO dto = new DishDTO();
		dto.setId(dish.getId());
		dto.setPrice(dish.getPrice());
		dto.setName(dish.getName());
		dto.setDescription(dish.getDescription());
		if (dish.getRestaurant() != null)
			dto.setRestaurantId(dish.getRestaurant().getId());
		if (dish.getDeliveries() != null)
			dto.setDeliveryIds(dish.getDeliveries().stream().map(Delivery::getId).toList());
		return dto;
	}

	/**
	 * Converte un Dish in DTO senza nessuna relazione.
	 * Solo i dati base del piatto: id, prezzo, nome, descrizione.
	 * 
	 * @param dish l'entita' Dish da convertire (puo' essere null)
	 * @return il DishDTO con solo i dati base
	 */
	public DishDTO toDTONoRelations(Dish dish)
	{
		if (dish == null)
			return null;
		DishDTO dto = new DishDTO();
		dto.setId(dish.getId());
		dto.setPrice(dish.getPrice());
		dto.setName(dish.getName());
		dto.setDescription(dish.getDescription());
		return dto;
	}

	/**
	 * Converte un DishDTO in un'entita' Dish.
	 * NOTA: le relazioni con Restaurant e Deliveries vengono gestite a livello di service/repository.
	 * 
	 * @param dto il DishDTO contenente i dati (puo' essere null)
	 * @return l'entita' Dish corrispondente
	 */
	public Dish toEntity(DishDTO dto)
	{
		if (dto == null)
			return null;
		Dish dish = new Dish();
		dish.setId(dto.getId());
		dish.setPrice(dto.getPrice());
		dish.setName(dto.getName());
		dish.setDescription(dto.getDescription());
		return dish;
	}

	/**
	 * Converte un DishDTO in entita' Dish SENZA relazioni.
	 * Solo i dati base vengono mappati.
	 * 
	 * @param dto il DishDTO con solo i dati base (puo' essere null)
	 * @return l'entita' Dish corrispondente
	 */
	public Dish toEntityNoRelations(DishDTO dto)
	{
		if (dto == null)
			return null;
		Dish dish = new Dish();
		dish.setId(dto.getId());
		dish.setPrice(dto.getPrice());
		dish.setName(dto.getName());
		dish.setDescription(dto.getDescription());
		return dish;
	}

	/**
	 * Converte una lista di Dish in lista di DishDTO.
	 * 
	 * @param dishes lista di entita' Dish (puo' essere null o vuota)
	 * @return lista di DishDTO corrispondenti
	 */
	public List<DishDTO> toDTOList(List<Dish> dishes)
	{
		return dishes.stream().map(this::toDTO).toList();
	}

	/**
	 * Converte una lista di DishDTO in lista di entita' Dish.
	 * 
	 * @param dtos lista di DishDTO (puo' essere null o vuota)
	 * @return lista di entita' Dish corrispondenti
	 */
	public List<Dish> toEntityList(List<DishDTO> dtos)
	{
		return dtos.stream().map(this::toEntity).toList();
	}
}
