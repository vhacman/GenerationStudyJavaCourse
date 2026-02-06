package com.generation.emergency.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.emergency.model.entities.Hospital;

/**
 * SPRING DATA JPA - REPOSITORY PATTERN
 * Questa interfaccia estende JpaRepository e Spring farà AUTOMATICAMENTE:
 * 1. Creerà una classe che implementa questa interfaccia
 * 2. Creerà un oggetto Singleton di quella classe
 * 3. Lo registrerà nel Context (iniettabile con @Autowired)
 * 4. Implementerà automaticamente i metodi CRUD standard:
 *    - findAll(), findById(), save(), delete(), count(), etc.
 *
 * PARAMETRI GENERICI: JpaRepository<Hospital, Integer>
 * - Hospital: La classe Entity che rappresenta la tabella
 * - Integer: Il tipo della chiave primaria (id)
 */
public interface HospitalRepository extends JpaRepository<Hospital, Integer>
{
    /**
     * findFirstByOrderByQueueAsc():
     * - "findFirst": trova il primo risultato
     * - "OrderBy": ordina i risultati
     * - "Queue": in base al campo 'queue'
     * - "Asc": in ordine ascendente (dal più basso al più alto)
     * SQL EQUIVALENTE: SELECT * FROM hospital ORDER BY queue ASC LIMIT 1
     */
    Hospital findFirstByOrderByQueueAsc();

    /**
     * findByCity(String city):
     * - "findBy": cerca tutti i record che soddisfano la condizione
     * - "City": campo su cui filtrare
     * SQL EQUIVALENTE: SELECT * FROM hospital WHERE city = ?
     */
    List<Hospital> findByCity(String city);

    /**
     * findByAddressContaining(String part):
     * - "findBy": cerca tutti i record
     * - "Address": campo su cui applicare il filtro
     * - "Containing": l'indirizzo CONTIENE la stringa (LIKE %part%)
     * SQL EQUIVALENTE: SELECT * FROM hospital WHERE address LIKE %?%
     */
    List<Hospital> findByAddressContaining(String part);

    /**
     * ALTERNATIVA MANUALE (commentata):
     * Prima di Spring Data JPA dovevamo scrivere manualmente i metodi:
     */
    /*
    default Hospital findFastest()
    {
        List<Hospital> all = findAll();
        Comparator<Hospital> cmp = (h1, h2) -> (h1.getQueue() - h2.getQueue());
        all.sort(cmp);
        return all.get(0);
    }
    */
}