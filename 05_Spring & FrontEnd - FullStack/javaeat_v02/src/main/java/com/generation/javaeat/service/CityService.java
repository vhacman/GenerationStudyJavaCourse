package com.generation.javaeat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.repository.CityRepository;
import com.generation.javaeat.service.dto.CityDTO;
import com.generation.javaeat.service.dto.mapper.CityDTOMapper;

/**
 * Service per la gestione delle operazioni sulle citta'.
 * 
 * CAMBI DALLE VECCHIE ENTITA':
 * - Rimosso metodo getRiders: la relazione City-Rider e' stata rimossa.
 *   Prima c'era un metodo per ottenere i rider di una citta', ora i rider
 *   operano in modo indipendente dalla localizzazione geografica.
 */
@Service
public class CityService
{
	@Autowired
    private CityRepository     cityRepo;
    @Autowired
    private CityDTOMapper      cityDTOMapper;
    
    
    /**
     * Inserisce una nuova citta' nel database.
     * 
     * @param dto i dati della citta' da inserire
     * @return la citta' inserita convertita in DTO
     * @throws MyServiceException se i dati sono nulli o non validi
     */
    public CityDTO insert(CityDTO dto) throws MyServiceException
    {
    	if (dto == null)
            throw new MyServiceException("CityDTO is null");
        if (dto.getName() == null || dto.getName().trim().isEmpty())
            throw new MyServiceException("City name is required");
        if (dto.getProvince() == null || dto.getProvince().trim().isEmpty())
            throw new MyServiceException("Province is required");
        City city = cityRepo.save(cityDTOMapper.toEntity(dto));
        return cityDTOMapper.toDTO(city);
    }
    
    /**
     * Restituisce tutte le citta' presenti nel database.
     * 
     * @return lista di tutte le citta' convertite in DTO
     */
    public List<CityDTO> findAll()
    {
        return cityDTOMapper.toDTOList(cityRepo.findAll());
    }
    
    /**
     * Trova una citta' per provincia.
     * 
     * @param province la provincia della citta'
     * @return la citta' trovata convertita in DTO
     * @throws MyServiceException se la provincia non viene trovata
     */
    public CityDTO findByProvince(String province) throws MyServiceException
    {
        List<City> cities = cityRepo.findByProvince(province);
        if (cities.isEmpty())
            throw new MyServiceException("Province not found: " + province);
        return cityDTOMapper.toDTO(cities.getFirst());
    }
    
    /**
     * Trova le citta' che contengono una stringa nel nome.
     * 
     * @param name la stringa da cercare nel nome
     * @return la citta' trovata convertita in DTO
     * @throws MyServiceException se non viene trovata nessuna citta'
     */
    public CityDTO findByNameContaining(String name) throws MyServiceException
    {
        List<City> cities = cityRepo.findByNameContaining(name);
        if (cities.isEmpty())
            throw new MyServiceException("City not found with name: " + name);
        return cityDTOMapper.toDTO(cities.getFirst());
    }
    
    /**
     * Trova una citta' per ID.
     * 
     * @param id l'ID della citta' da cercare
     * @return la citta' trovata convertita in DTO
     * @throws MyServiceException se la citta' non viene trovata
     */
    public CityDTO findById(int id) throws MyServiceException
    {
        City city = cityRepo.findById(id).orElse(null);
        if (city == null)
            throw new MyServiceException("City not found with id: " + id);
        return cityDTOMapper.toDTO(city);
    }
    
    /**
     * Salva o aggiorna una citta'.
     * 
     * @param city i dati della citta' da salvare
     * @return la città salvata convertita in DTO
     * @throws MyServiceException se i dati sono nulli
     */
    public CityDTO save(CityDTO city) throws MyServiceException
    {
        if (city == null)
            throw new MyServiceException("CityDTO is null");
        return cityDTOMapper.toDTO(cityRepo.save(cityDTOMapper.toEntity(city)));
    }
    
    /**
     * Elimina una città per ID.
     * 
     * @param id l'id della città da eliminare
     * @throws MyServiceException se la citta' non esiste
     */
    public void deleteById(int id) throws MyServiceException
    {
        if (!cityRepo.existsById(id))
            throw new MyServiceException("City not found with id: " + id);
        cityRepo.deleteById(id);
    }
    
    /**
     * Verifica se una citta' esiste per ID.
     * 
     * @param id l'ID della citta' da verificare
     * @return true se la citta' esiste, false altrimenti
     * @throws MyServiceException se si verifica un errore
     */
    public boolean existsById(int id)throws MyServiceException
    {
        return cityRepo.existsById(id);
    }
    
}
