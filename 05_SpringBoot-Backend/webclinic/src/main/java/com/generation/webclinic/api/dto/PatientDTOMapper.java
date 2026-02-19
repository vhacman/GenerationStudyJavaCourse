package com.generation.webclinic.api.dto;


import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.generation.webclinic.model.entities.Patient;

@Service
public class PatientDTOMapper
{
    public PatientDTO toDTO(Patient patient)
    {

        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDob(patient.getDob());
        dto.setCity(patient.getCity());
        dto.setGender(patient.getGender());
        dto.setHistory(patient.getHistory());
        dto.setAddress(patient.getAddress());
        dto.setSsn(patient.getSsn());
        dto.setEmail(patient.getEmail());
        dto.setErrors(new ArrayList<String>());
        return dto;
    }

    public PatientDTO toFullDTO(Patient patient)
    {

        PatientDTO dto = toDTO(patient);
        dto.setErrors(patient.getErrors());
        return dto;
    }





}
