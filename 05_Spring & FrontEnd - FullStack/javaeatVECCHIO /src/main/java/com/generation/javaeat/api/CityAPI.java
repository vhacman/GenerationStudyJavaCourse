package com.generation.javaeat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javaeat.api.dto.CityDTO;
import com.generation.javaeat.api.dto.CityDTOMapper;
import com.generation.javaeat.model.entities.City;
import com.generation.javaeat.model.repository.CityRepository;

/**
 * La classe CityAPI implementa un controller REST che gestisce le operazioni CRUD
 * sulle entità City attraverso endpoint HTTP. L'annotazione @RestController combina
 * le funzionalità di @Controller e @ResponseBody, indicando che i metodi di questa
 * classe restituiscono direttamente dati nel corpo della risposta HTTP anziché
 * nomi di view per il rendering. L'annotazione @RequestMapping definisce il path
 * base "/javaeat/api/cities" per tutti gli endpoint definiti in questo controller.
 *
 * Il pattern architetturale implementato segue il modello MVC (Model-View-Controller)
 * dove il controller gestisce le richieste HTTP, interagisce con il repository
 * per accedere ai dati e utilizza il mapper per convertire tra entità e DTO.
 */
@RestController
@RequestMapping("/javaeat/api/cities")
public class CityAPI
{

    @Autowired
    private CityRepository     cityRepo;
    @Autowired
    private CityDTOMapper      cityDTOMapper;


    /**
     * L'endpoint findAll gestisce richieste GET al path base e restituisce
     * tutte le città presenti nel database. Il metodo utilizza il repository
     * per recuperare tutte le entità e le converte in DTO prima di restituirle
     * al client.
     *
     * @return Una lista di CityDTO contenenti tutte le città.
     */
    @GetMapping
    public List<CityDTO> findAll()
    {
        return cityDTOMapper.toDTO(cityRepo.findAll());
    }


    /**
     * L'endpoint findByProvince gestisce richieste GET al path "/byprovince/{province}"
     * e restituisce tutte le città appartenenti alla provincia specificata.
     * La ricerca è case-sensitive secondo la configurazione del database.
     *
     * @param province La provincia da cercare.
     * @return Una lista di CityDTO per le città della provincia specificata.
     */
    @GetMapping("/byprovince/{province}")
    public List<CityDTO> findByProvince(@PathVariable("province") String province)
    {
        return cityDTOMapper.toDTO(cityRepo.findByProvince(province));
    }


    /**
     * L'endpoint findByName gestisce richieste GET al path "/byname/{name}"
     * e restituisce tutte le città il cui nome contiene la stringa specificata.
     * La ricerca utilizza l'operatore SQL LIKE con wildcards su entrambi i lati.
     *
     * @param name La porzione di nome da cercare.
     * @return Una lista di CityDTO per le città trovate.
     */
    @GetMapping("/byname/{name}")
    public List<CityDTO> findByName(@PathVariable("name") String name)
    {
        return cityDTOMapper.toDTO(cityRepo.findByNameContaining(name));
    }


    /**
     * L'endpoint findById gestisce richieste GET al path "/{id}" e restituisce
     * la città corrispondente all'ID specificato. Il tipo di ritorno ResponseEntity
     * permette di restituire codici HTTP diversi in base al risultato: 200 OK
     * se la città viene trovata, 404 Not Found altrimenti.
     *
     * @param id L'identificativo della città da cercare.
     * @return ResponseEntity contenente il CityDTO o un codice 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        Optional<City> cityOpt = cityRepo.findById(id);
        if (cityOpt.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cityDTOMapper.toDTO(cityOpt.get()));
    }


    /**
     * L'endpoint newCity gestisce richieste POST al path base per la creazione
     * di una nuova città. Prima del salvataggio, viene verificato che non esista
     * già una città con lo stesso nome. In caso di duplicato, viene restituito
     * un codice 400 Bad Request con un messaggio di errore.
     *
     * @param cityDTO Il CityDTO contenente i dati della nuova città.
     * @return ResponseEntity con il CityDTO creato o un messaggio di errore.
     */
    @PostMapping
    public ResponseEntity<Object> newCity(@RequestBody CityDTO cityDTO)
    {
        City city = cityDTOMapper.fromDTO(cityDTO);
        if (cityRepo.findByNameContaining(city.getName()).size() > 0)
        {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "Already present");
            return ResponseEntity.badRequest().body(errors);
        }
        city = cityRepo.save(city);
        return ResponseEntity.ok(cityDTOMapper.toDTO(city));
    }


    /**
     * L'endpoint changeProvince gestisce richieste PATCH al path "/{id}/changeprovince/{province}"
     * per la modifica parziale dell'entità. L'HTTP method PATCH è specificamente
     * progettato per aggiornamenti parziali, modificando solo il campo specificato.
     *
     * @param id L'identificativo della città da modificare.
     * @param province La nuova provincia da assegnare.
     * @return ResponseEntity con il CityDTO aggiornato o 404 se non trovata.
     */
    @PatchMapping("/{id}/changeprovince/{province}")
    public ResponseEntity<Object> changeProvince(@PathVariable("id") int id,
                                                  @PathVariable("province") String province)
    {
        Optional<City> cityOpt = cityRepo.findById(id);
        if (cityOpt.isEmpty())
            return ResponseEntity.notFound().build();
        City city = cityOpt.get();
        city.setProvince(province);
        city = cityRepo.save(city);
        return ResponseEntity.ok(cityDTOMapper.toDTO(city));
    }


    /**
     * L'endpoint deleteCity gestisce richieste DELETE al path "/{id}" per
     * l'eliminazione di una città dal database. Se l'ID non corrisponde a
     * nessuna città esistente, viene restituito un codice 404.
     *
     * @param id L'identificativo della città da eliminare.
     * @return ResponseEntity con conferma di eliminazione o 404.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable("id") int id)
    {
        Optional<City> cityOpt = cityRepo.findById(id);

        if (cityOpt.isEmpty())
            return ResponseEntity.notFound().build();
        cityRepo.deleteById(id);
        return ResponseEntity.ok("OK");
    }
}
