package com.generation.sanctionweb.api.dto;

import com.generation.sanctionweb.model.entities.Sanction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service per la conversione tra Entity Sanction e DTO SanctionDTO.
 * Implementa il pattern Mapper per la trasformazione bidirezionale dei dati.
 */
@Service
public class SanctionMapperDTO
{
    /**
     * Converte un'entity Sanction in un DTO SanctionDTO.
     * 
     * @param sanction entity da convertire
     * @return DTO con i dati della sanzione
     */
    public SanctionDTO toDTO(Sanction sanction)
    {
        SanctionDTO res = new SanctionDTO();
        res.setId(sanction.getId());
        res.setFirstName(sanction.getFirstName());
        res.setLastName(sanction.getLastName());
        res.setDate(sanction.getDate());
        res.setPaidOn(sanction.getPaidOn());
        res.setPrice(sanction.getPrice());
        res.setStatus(sanction.getStatus());
        res.setReason(sanction.getReason());
        /*
         * Gestisce l'associazione del cittadino nel DTO:
         * - Se presente: estrae ID e concatena Nome + Cognome.
         * - Se assente: imposta valori di default (ID 0 e "Unknown").
         */
        if (sanction.getCitizen() != null)
        {
            // Recupera l'ID e crea la stringa leggibile del nome completo
            res.setCitizen_id(sanction.getCitizen().getId());
            res.setCitizen(sanction.getCitizen().getFirstName() + " " + sanction.getCitizen().getLastName());
        }
        else
        {
            // Gestione del caso in cui la sanzione non abbia un cittadino associato
            res.setCitizen_id(0);
            res.setCitizen("Unknown");
        }
        return res;
    }

    /**
     * Converte una lista di entity Sanction in lista di DTO.
     * 
     * @param sanctions lista di entity da convertire
     * @return lista di DTO
     */
    public List<SanctionDTO> toDTO(List<Sanction> sanctions)
    {
        return sanctions.stream().map(this::toDTO).toList();
    }

    /**
     * Converte un DTO SanctionDTO in un'entity Sanction.
     * Nota: il cittadino non viene impostato per evitare dipendenze circolari.
     * 
     * @param dto DTO da convertire
     * @return entity Sanction
     */
    public Sanction toEntity(SanctionDTO dto)
    {
        Sanction sanction = new Sanction();
        sanction.setId(dto.getId());
        sanction.setDate(dto.getDate());
        sanction.setPaidOn(dto.getPaidOn());
        sanction.setStatus(dto.getStatus());
        sanction.setReason(dto.getReason());
        sanction.setPrice(dto.getPrice());
        return sanction;
    }
}
