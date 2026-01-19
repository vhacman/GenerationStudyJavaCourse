package com.generation.gbb.cache;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * TEORIA: Generic Types e Type Bounds
 * ===================================
 * Definizione: I Generics permettono di creare classi parametriche con type safety a compile-time.
 * 
 * Type Bound (<X extends Entity>):
 *   - Vincola il tipo X a essere Entity o una sua sottoclasse
 *   - Garantisce l'accesso ai metodi di Entity (es. getId())
 *   - Implementa il principio di ASTRAZIONE: lavoriamo su un contratto (Entity) non su implementazioni concrete
 */
public class PartialCache<X extends Entity>
{
    // Quanti elementi tengo in memoria
    int             maxSize;
    List<X>         content = new ArrayList<X>();


    /**
     * Costruisce una cache parziale con dimensione massima specificata.
     * La cache mantiene in memoria solo gli ultimi N elementi aggiunti.
     * 
     * @param size la dimensione massima della cache (numero massimo di elementi)
     */
    public PartialCache(int size)
    {
        /*
         * Relazione tra COSTRUTTORE e INVARIANTE DI CLASSE:
         *   Invariante  →  Proprietà che deve rimanere vera per tutta la vita dell'oggetto
         *   maxSize     →  Definisce il limite superiore della collezione content
         * 
         * Questo costruttore stabilisce l'invariante fondamentale: content.size() ≤ maxSize
         * L'incapsulamento garantisce che questa regola sia rispettata in tutti i metodi.
         */
        this.maxSize = size;
    }


    public boolean contains(X x)
    {
    	return content.contains(x);
    }
    
    public void	removeElement(X x)
    {
    	content.remove(x);
    }
    /**
     * Aggiunge un elemento alla cache. Se la cache è piena, rimuove automaticamente
     * l'elemento più vecchio (politica FIFO - First In First Out).
     *
     * OTTIMIZZAZIONE: Se l'elemento è già presente, non viene aggiunto nuovamente
     * per evitare duplicati e mantenere la cache pulita.
     *
     * @param x l'elemento da aggiungere alla cache
     */
    public void addElement(X x)
    {
        /*
         * Relazione tra ALGORITMO e PATTERN:
         *   Pattern utilizzato  →  Circular Buffer (Buffer Circolare)
         *   FIFO Policy         →  First In, First Out
         *
         * Implementazione:
         *   1. Verifica se l'elemento esiste già (evita duplicati)
         *   2. Aggiungi sempre in coda (add)
         *   3. Se superi maxSize, rimuovi dalla testa (remove(0))
         *
         * Principio di INCAPSULAMENTO:
         *   - Il client non deve gestire la logica di overflow
         *   - La responsabilità del mantenimento dell'invariante è interna alla classe
         */

        // OTTIMIZZAZIONE: Evita duplicati controllando con contains()
        if(contains(x))
            return;

        // Ho aggiunto un nuovo elemento
        content.add(x);

        if(content.size() > maxSize)
            content.remove(0);
    }


    /**
     * Cerca un elemento nella cache tramite il suo identificatore.
     * 
     * @param id l'identificatore univoco dell'elemento da cercare
     * @return l'elemento con l'id specificato, oppure null se non trovato
     */
    public X findById(int id)
    {
        /*
         * Relazione tra ASTRAZIONE e CONTRATTO:
         *   Entity (interfaccia)  →  Definisce il contratto getId()
         *   X extends Entity      →  Garantisce che ogni X implementi getId()
         *   PartialCache          →  Può invocare getId() senza conoscere il tipo concreto
         * 
         * Principio di POLIMORFISMO:
         *   - x.getId() viene risolto a runtime in base al tipo effettivo di X
         *   - La cache lavora su astrazioni, non su implementazioni concrete
         * 
         * Complessità: O(n) - scansione lineare della collezione
         */
        for(X x : content)
            if(x.getId() == id)
                return x;
                
        return null;
    }


    // Getters e Setters
    
    /**
     * Restituisce la dimensione massima della cache.
     * 
     * @return il numero massimo di elementi che la cache può contenere
     */
    public int getMaxSize()                 { return maxSize;           }
    
    /**
     * Imposta la dimensione massima della cache.
     * ATTENZIONE: modificare maxSize non ridimensiona automaticamente il contenuto esistente.
     * 
     * @param maxSize il nuovo limite massimo di elementi
     */
    public void setMaxSize(int maxSize)     { this.maxSize = maxSize;   }
    
    /**
     * Restituisce la lista degli elementi attualmente in cache.
     * 
     * @return la collezione di elementi memorizzati
     */
    public List<X> getContent()             { return content;           }
    
    /**
     * Sostituisce il contenuto della cache con una nuova lista.
     * 
     * @param content la nuova lista di elementi da memorizzare
     */
    public void setContent(List<X> content) { this.content = content;   }
}
