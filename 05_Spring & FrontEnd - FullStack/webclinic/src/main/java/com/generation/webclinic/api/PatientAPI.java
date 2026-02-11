package com.generation.webclinic.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.webclinic.api.dto.PatientDTOMapper;
import com.generation.webclinic.model.entities.Patient;
import com.generation.webclinic.model.repository.PatientRepository;

@RestController
@RequestMapping("/webclinic/api/patients")
public class PatientAPI
{
    @Autowired
    PatientRepository patientRepo;
    @Autowired
    PatientDTOMapper mapper;

    /*
     * dati del paziente formattati per essere inviati
     * modificati come pare a me
     * posso scegliere cosa inviare e cosa no
     * non è detto che io restituisca qualcosa
     * se l'id non c'è io NON MANDO UN CORPO
     * io mando solo uno status, 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findPatient(@PathVariable("id") int id)
    {
        Optional<Patient> patientOpt = patientRepo.findById(id);

        if(patientOpt.isEmpty())
            return ResponseEntity.notFound().build();
        // questo codice si legge: prendi una response entity vuota
        // imposta status = 404 e poi finisci di produrla (build)

        // prendo il contenuto
        Patient patient = patientOpt.get();


        return ResponseEntity.ok(mapper.toDTO(patient));
        // crea una response, dagli codice 200, e dagli come corpo il patient
        // che verrà jsonizzato

    }


}