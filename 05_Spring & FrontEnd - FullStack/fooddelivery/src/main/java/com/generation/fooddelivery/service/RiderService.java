package com.generation.fooddelivery.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.fooddelivery.api.dto.RiderDTO;
import com.generation.fooddelivery.api.dto.mapper.RiderDTOMapper;
import com.generation.fooddelivery.model.entities.Rider;
import com.generation.fooddelivery.model.repository.RiderRepository;

/**
 * Service per la gestione dei Rider.
 * Un Service in Spring è dove risiede la logica di business.
 * Il Controller (API) si limita a chiamare i metodi del Service.
 */
@Service
public class RiderService
{
    @Autowired
    RiderDTOMapper riderMapper;

    @Autowired
    RiderRepository riderRepo;

    /**
     * Salva un nuovo rider.
     * @param dto DTO del rider da salvare
     * @return DTO del rider salvato
     * @throws ServiceException se esiste già un rider con quella email
     */
    public RiderDTO save(RiderDTO dto) throws ServiceException
    {
        Rider rider = riderMapper.toEntity(dto);

        if (riderRepo.exists(rider.getEmail()))
            throw new ServiceException("Rider already exists");

        rider = riderRepo.save(rider);

        return riderMapper.toDto(rider);
    }

    /**
     * Trova un rider per ID.
     * @param id ID del rider
     * @return DTO del rider
     * @throws ServiceException se non trovato
     */
    public RiderDTO findByID(int id) throws ServiceException
    {
        Optional<Rider> riderOpt = riderRepo.findById(id);

        if (riderOpt.isEmpty())
            throw new ServiceException("Rider not found");

        return riderMapper.toDto(riderOpt.get());
    }

    /**
     * Trova i rider attivi (che hanno effettuato consegne) tra due date.
     * @param d1 Data inizio
     * @param d2 Data fine
     * @return Lista di DTO dei rider attivi
     */
    public List<RiderDTO> findActiveBetween(LocalDateTime d1, LocalDateTime d2)
    {
        return riderMapper.toDto(riderRepo.findActiveBetween(d1, d2));
    }
}
