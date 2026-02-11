package com.generation.javaeat.api.dto;

import org.springframework.stereotype.Service;
import com.generation.javaeat.model.entities.Rider;

@Service
public class RiderDTOMapper
{
    public Rider fromDTO(RiderDTO dto)
    {
        Rider r = new Rider();
        r.setId(dto.getId());
        r.setEmail(dto.getEmail());
        r.setLegalName(dto.getLegalName());
        r.setPw(dto.getPw());
        r.setServiceCost(dto.getServiceCost());
        return r;
    }

    public RiderDTO toDTO(Rider rider)
    {
        RiderDTO r = new RiderDTO();
        r.setId(rider.getId());
        r.setEmail(rider.getEmail());
        r.setLegalName(rider.getLegalName());
        r.setServiceCost(rider.getServiceCost());
        if (rider.getDeliveries() != null)
        {
            r.setDeliveries(rider.getDeliveries().stream().map(d -> {DeliveryDTO dDTO = new DeliveryDTO();
												                        dDTO.setId(d.getId());
												                        return dDTO;}).toList());
        }
        return r;
    }
}
