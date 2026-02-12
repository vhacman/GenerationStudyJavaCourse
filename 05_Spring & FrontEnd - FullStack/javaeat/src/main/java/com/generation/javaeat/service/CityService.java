package com.generation.javaeat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.repository.CityRepository;
import com.generation.javaeat.service.dto.CityDTO;
import com.generation.javaeat.service.dto.mapper.CityDTOMapper;

@Service
public class CityService
{
	@Autowired
    private CityRepository     cityRepo;
    @Autowired
    private CityDTOMapper      cityDTOMapper;
    
    
    public CityDTO insert(CityDTO dto) throws MyServiceException
    {
    	if (dto == null)
            throw new MyServiceException("CityDTO is null");
        if (dto.getName() == null || dto.getName().trim().isEmpty())
            throw new MyServiceException("City name is required");
        if (dto.getProvince() == null || dto.getProvince().trim().isEmpty())
            throw new MyServiceException("Province is required");
        City city = cityDTOMapper.toEntity(dto);
        city = cityRepo.save(city);
        return cityDTOMapper.toDTO(city);
    }
    
    public List<CityDTO> findAll()
    {
        return cityDTOMapper.toDTOList(cityRepo.findAll());
    }
    
    public CityDTO findByProvince(String province) throws MyServiceException
    {
        List<City> cities = cityRepo.findByProvince(province);
        if (cities.isEmpty())
            throw new MyServiceException("Province not found: " + province);
        return cityDTOMapper.toDTO(cities.get(0));
    }
    
    public CityDTO findByNameContaining(String name) throws MyServiceException
    {
        List<City> cities = cityRepo.findByNameContaining(name);
        if (cities.isEmpty())
            throw new MyServiceException("City not found with name: " + name);
        return cityDTOMapper.toDTO(cities.get(0));
    }
    
    public CityDTO findById(int id) throws MyServiceException
    {
        City city = cityRepo.findById(id).orElse(null);
        if (city == null)
            throw new MyServiceException("City not found with id: " + id);
        return cityDTOMapper.toDTO(city);
    }
    
    public CityDTO save(CityDTO city) throws MyServiceException
    {
        if (city == null)
            throw new MyServiceException("CityDTO is null");
        return cityDTOMapper.toDTO(cityRepo.save(cityDTOMapper.toEntity(city)));
    }
    
    public void deleteById(int id) throws MyServiceException
    {
        if (!cityRepo.existsById(id))
            throw new MyServiceException("City not found with id: " + id);
        cityRepo.deleteById(id);
    }
    
    public boolean existsById(int id)throws MyServiceException
    {
        return cityRepo.existsById(id);
    }
    
}
