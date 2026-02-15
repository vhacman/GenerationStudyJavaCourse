package com.generation.javaeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javaeat.model.entities.Costumer;
import com.generation.javaeat.model.repository.CostumerRepository;
import com.generation.javaeat.service.dto.CostumerDTO;
import com.generation.javaeat.service.dto.mapper.CostumerDTOMapper;

/**
 * Service per la gestione delle operazioni sui clienti.
 * CAMBI DALLE VECCHIE ENTITA':
 * - Rimosso campo riderId: la relazione diretta Costumer-Rider è stata rimossa.
 *   Prima il cliente aveva un riferimento diretto al rider assegnato, ora questa
 *   relazione passa attraverso Delivery (una consegna ha un rider).
 */
@Service
public class CostumerService
{
    @Autowired
    private CostumerRepository   costumerRepo;
    @Autowired
    private CostumerDTOMapper    costumerDTOMapper;

    /**
     * Inserisce un nuovo cliente nel database.
     * 
     * @param dto i dati del cliente da inserire
     * @return il cliente inserito convertito in DTO
     * @throws MyServiceException se i dati sono nulli o non validi
     */
    public CostumerDTO insert(CostumerDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("CostumerDTO is null");
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            throw new MyServiceException("Email is required");
        if (dto.getPw() == null || dto.getPw().trim().isEmpty())
            throw new MyServiceException("Password is required");
        if (dto.getLegalName() == null || dto.getLegalName().trim().isEmpty())
            throw new MyServiceException("LegalName is required");
        if (dto.getAddress() == null || dto.getAddress().trim().isEmpty())
            throw new MyServiceException("Address is required");
        Costumer costumer = costumerRepo.save(costumerDTOMapper.toEntity(dto));
        return costumerDTOMapper.toDTO(costumer);
    }

    /**
     * Restituisce tutti i clienti presenti nel database.
     * 
     * @return lista di tutti i clienti convertiti in DTO
     */
    public List<CostumerDTO> findAll()
    {
        return costumerDTOMapper.toDTOList(costumerRepo.findAll());
    }

    /**
     * Trova un cliente per ID.
     * 
     * @param id l'ID del cliente da cercare
     * @return il cliente trovato convertito in DTO
     * @throws MyServiceException se il cliente non viene trovato
     */
    public CostumerDTO findById(int id) throws MyServiceException
    {
        Costumer costumer = costumerRepo.findById(id).orElse(null);
        if (costumer == null)
            throw new MyServiceException("Costumer not found with id: " + id);
        return costumerDTOMapper.toDTO(costumer);
    }

    /**
     * Trova i clienti appartenenti a una specifica citta'.
     * 
     * @param cityId l'ID della citta'
     * @return lista di clienti nella citta' specificata
     * @throws MyServiceException se non viene trovato nessun cliente
     */
    public List<CostumerDTO> findByCityId(int cityId) throws MyServiceException
    {
        List<Costumer> costumers = costumerRepo.findByCityId(cityId);
        if (costumers.isEmpty())
            throw new MyServiceException("No costumers found for city id: " + cityId);
        return costumerDTOMapper.toDTOList(costumers);
    }

    /**
     * Trova i clienti che contengono una stringa nel nome legale.
     * 
     * @param legalName la stringa da cercare nel nome
     * @return lista di clienti che soddisfano il criterio
     * @throws MyServiceException se non viene trovato nessun cliente
     */
    public List<CostumerDTO> findByLegalNameContaining(String legalName) throws MyServiceException
    {
        List<Costumer> costumers = costumerRepo.findByLegalNameContaining(legalName);
        if (costumers.isEmpty())
            throw new MyServiceException("No costumers found with name: " + legalName);
        return costumerDTOMapper.toDTOList(costumers);
    }

    /**
     * Salva o aggiorna un cliente.
     * 
     * @param dto i dati del cliente da salvare
     * @return il cliente salvato convertito in DTO
     * @throws MyServiceException se i dati sono nulli
     */
    public CostumerDTO save(CostumerDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("CostumerDTO is null");
        return costumerDTOMapper.toDTO(costumerRepo.save(costumerDTOMapper.toEntity(dto)));
    }

    /**
     * Elimina un cliente per ID.
     * 
     * @param id l'ID del cliente da eliminare
     * @throws MyServiceException se il cliente non esiste
     */
    public void deleteById(int id) throws MyServiceException
    {
        if (!costumerRepo.existsById(id))
            throw new MyServiceException("Costumer not found with id: " + id);
        costumerRepo.deleteById(id);
    }
}
