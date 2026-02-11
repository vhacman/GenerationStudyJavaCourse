package com.generation.javaeat.api.dto;

import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.entities.Customer;
import com.generation.javaeat.model.repository.CityRepository;

@Service
public class CustomerDTOMapper
{
    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private DeliveryDTOMapper deliveryDTOMapper;

    public Customer fromDTO(CustomerDTO dto)
    {
        Customer c = new Customer();
        c.setId(dto.getId());
        c.setEmail(dto.getEmail());
        c.setPw(dto.getPw());
        c.setLegalName(dto.getLegalName());
        c.setAddress(dto.getAddress());

        if (dto.getCity() != null)
        {
            if (dto.getCity().getId() > 0)
            {
                Optional<City> cityOpt = cityRepo.findById(dto.getCity().getId());
                if (cityOpt.isPresent())
                    c.setCity(cityOpt.get());
            }
            else if (dto.getCity().getName() != null) 
                cityRepo.findByName(dto.getCity().getName()).ifPresent(c::setCity); 
        }

        return c;
    }

    public CustomerDTO toDTO(Customer customer)
    {
        CustomerDTO c = new CustomerDTO();
        c.setId(customer.getId());
        c.setEmail(customer.getEmail());
        c.setLegalName(customer.getLegalName());
        c.setAddress(customer.getAddress());
        if (customer.getCity() != null)
        {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(customer.getCity().getId());
            cityDTO.setName(customer.getCity().getName());
            cityDTO.setProvince(customer.getCity().getProvince());
            c.setCity(cityDTO);
        }
        if (customer.getDeliveries() != null) 
            c.setDeliveries(customer.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList()); 
        return c;
    }
}
