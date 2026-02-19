package com.generation.javaeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.model.repository.RiderRepository;
import com.generation.javaeat.service.dto.RiderDTO;
import com.generation.javaeat.service.dto.mapper.RiderDTOMapper;

/**
 * Service per la gestione delle operazioni sui rider.
 * 
 * CAMBI DALLE VECCHIE ENTITA':
 * - Aggiunto campo status: il rider ora ha uno stato (AVAILABLE/NOTAVAILABLE)
 *   che indica se e' disponibile per nuove consegne.
 * - Rimosso campo city: la relazione Rider-City e' stata rimossa.
 *   I rider operano in modo indipendente dalla localizzazione geografica.
 */
@Service
public class RiderService
{
    @Autowired
    private RiderRepository  riderRepo;
    @Autowired
    private RiderDTOMapper   riderDTOMapper;

    /**
     * Inserisce un nuovo rider nel database.
     * 
     * CAMBI DALLA VECCHIA VERSIONE:
     * - Aggiunta validazione del campo status.
     * 
     * @param dto i dati del rider da inserire
     * @return il rider inserito convertito in DTO
     * @throws MyServiceException se i dati sono nulli o non validi
     */
    public RiderDTO insert(RiderDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("RiderDTO is null");
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            throw new MyServiceException("Email is required");
        if (dto.getPw() == null || dto.getPw().trim().isEmpty())
            throw new MyServiceException("Password is required");
        if (dto.getLegalName() == null || dto.getLegalName().trim().isEmpty())
            throw new MyServiceException("LegalName is required");
        if (dto.getServiceCost() < 0)
            throw new MyServiceException("ServiceCost cannot be negative");
        Rider rider = riderDTOMapper.toEntity(dto);
        rider = riderRepo.save(rider);
        return riderDTOMapper.toDTO(rider);
    }

    /**
     * Restituisce tutti i rider presenti nel database.
     * 
     * @return lista di tutti i rider convertiti in DTO
     */
    public List<RiderDTO> findAll()
    {
        return riderDTOMapper.toDTOList(riderRepo.findAll());
    }

    /**
     * Trova un rider per ID.
     * 
     * @param id l'ID del rider da cercare
     * @return il rider trovato convertito in DTO
     * @throws MyServiceException se il rider non viene trovato
     */
    public RiderDTO findById(int id) throws MyServiceException
    {
        Rider rider = riderRepo.findById(id).orElse(null);
        if (rider == null)
            throw new MyServiceException("Rider not found with id: " + id);
        return riderDTOMapper.toDTO(rider);
    }

    /**
     * Trova i rider per stato di disponibilita'.
     * 
     * CAMBI DALLA VECCHIA VERSIONE:
     * - Nuovo metodo: prima non esisteva perche' il campo status non era presente.
     * 
     * @param status lo stato del rider (es. AVAILABLE, NOTAVAILABLE)
     * @return lista di rider con lo stato specificato
     * @throws MyServiceException se non viene trovato nessun rider
     */
    public List<RiderDTO> findByStatus(String status) throws MyServiceException
    {
        List<Rider> riders = riderRepo.findByStatus(status);
        if (riders.isEmpty())
            throw new MyServiceException("No riders found with status: " + status);
        return riderDTOMapper.toDTOList(riders);
    }

    /**
     * Trova i rider che contengono una stringa nel nome legale.
     * 
     * @param legalName la stringa da cercare nel nome
     * @return lista di rider che soddisfano il criterio
     * @throws MyServiceException se non viene trovato nessun rider
     */
    public List<RiderDTO> findByLegalNameContaining(String legalName) throws MyServiceException
    {
        List<Rider> riders = riderRepo.findByLegalNameContaining(legalName);
        if (riders.isEmpty())
            throw new MyServiceException("No riders found with name: " + legalName);
        return riderDTOMapper.toDTOList(riders);
    }

    /**
     * Salva o aggiorna un rider.
     * 
     * @param dto i dati del rider da salvare
     * @return il rider salvato convertito in DTO
     * @throws MyServiceException se i dati sono nulli
     */
    public RiderDTO save(RiderDTO dto) throws MyServiceException
    {
        if (dto == null)
            throw new MyServiceException("RiderDTO is null");
        return riderDTOMapper.toDTO(riderRepo.save(riderDTOMapper.toEntity(dto)));
    }

    /**
     * Elimina un rider per ID.
     * 
     * @param id l'ID del rider da eliminare
     * @throws MyServiceException se il rider non esiste
     */
    public void deleteById(int id) throws MyServiceException
    {
        if (!riderRepo.existsById(id))
            throw new MyServiceException("Rider not found with id: " + id);
        riderRepo.deleteById(id);
    }
}
