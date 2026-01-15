package com.generation.gbb.model.entities;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import com.generation.library.Entity;

/**
 * Rappresenta una stanza del B&B con caratteristiche fisiche e tariffarie.
 * Estende Entity per ereditare validazione e gestione ID.
 *
 * Relazione OOP → Inheritance (Ereditarietà):
 *      Room (subclass)  →  Entity (superclass)
 *      Room eredita id, isValid(), isMissing() da Entity
 *      Room estende la validazione con regole di business specifiche
 */
public class Room extends Entity
{
    private String  name;
    private String  description;
    private double  size;
    private int     floor;
    private double  price;

    public Room()
    {
    }

    /**
     * Parametrized constructor per inizializzare tutti gli attributi.
     *
     * @param id          Identificativo univoco della stanza
     * @param name        Nome della stanza (obbligatorio)
     * @param description Descrizione della stanza (obbligatorio)
     * @param size        Dimensione in mq (deve essere > 0)
     * @param floor       Piano della stanza (0-6)
     * @param price       Prezzo per notte in € (deve essere > 0)
     */
    public Room(int id, String name, String description, double size, int floor, double price)
    {
        /*
         * Relazione OOP → Constructor Chaining (Concatenamento Costruttori):
         *      Room.constructor  →  Entity.constructor (via super)
         *      super(id) invoca il costruttore della superclasse Entity
         *      Garantisce corretta inizializzazione dell'ereditarietà
         */
        super(id);
        this.name        = name;
        this.description = description;
        this.size        = size;
        this.floor       = floor;
        this.price       = price;
    }


    /**
     * Confronta questa stanza con un altro oggetto per uguaglianza.
     * Due stanze sono uguali se tutti i loro attributi corrispondono.
     *
     * @param obj Oggetto da confrontare con questa stanza
     * @return true se gli oggetti sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj)
    {
        /*
         * Pattern → Template Method per equals():
         *      1. Identity check     →  Ottimizzazione (stesso riferimento)
         *      2. Null check         →  Sicurezza (prevenzione NPE)
         *      3. Type check         →  Type safety (instanceof)
         *      4. Safe cast          →  Conversione garantita
         *      5. Field comparison   →  Uguaglianza semantica
         */
        if (this == obj)           return true;
        if (obj == null)           return false;
        if (!(obj instanceof Room)) return false;

        Room other = (Room) obj;

        return this.name.           equals(other.name)              &&
               this.description.    equals(other.description)       &&
               this.size == other.size                              &&
               this.floor == other.floor                            &&
               this.price == other.price;
    }


    /**
     * Genera un hash code per questa stanza basato sui suoi attributi.
     * Coerente con equals(): stanze uguali hanno lo stesso hash code.
     *
     * @return Hash code value per questa stanza
     */
    @Override
    public int hashCode()
    {
        /*
         * Relazione → equals() + hashCode() (Contratto):
         *      Se equals() restituisce true  →  hashCode() DEVE essere uguale
         *      Necessario per HashMap, HashSet, Hashtable
         *      Objects.hash() garantisce coerenza automatica
         */
        return Objects.hash(name, description, size, floor, price);
    }

    /**
     * Valida tutti gli attributi della stanza secondo le regole di business.
     * Controlla campi obbligatori mancanti e valori non validi.
     *
     * @return Lista di messaggi di errore di validazione.
     *         Lista vuota se la validazione passa.
     */
    @Override
    public List<String> getErrors()
    {
        /*
         * Pattern → Collecting Parameter:
         *      errors (accumulator)  →  raccoglie tutti gli errori
         *      Ogni validazione aggiunge messaggi se fallisce
         *      Ritorna collezione completa degli errori
         */
        List<String>    errors = new ArrayList<>();

        if (isMissing(name))            errors.add("Nome stanza obbligatorio");
        if (isMissing(description))     errors.add("Descrizione stanza obbligatoria");
        if (size <= 0)                  errors.add("Dimensione deve essere positiva (mq)");
        if (floor < 0 || floor > 6)     errors.add("Piano non può essere negativo o maggiore di 6");
        if (price <= 0)                 errors.add("Prezzo per notte deve essere positivo");

        return errors;
    }

    /**
     * Genera una rappresentazione stringa leggibile della stanza.
     * Include tutti gli attributi per logging e debug.
     *
     * @return Stringa formattata con tutti gli attributi
     */
    @Override
    public String toString()
    {
        return "Room{" +
               "name='"        + name        + '\'' +
               ", description='" + description + '\'' +
               ", size="       + size        +
               ", floor="      + floor       +
               ", price="      + price       +
               '}';
    }

    // ==================== GETTER E SETTER ====================

    public String  getName()                        { return name;                      }
    public String  getDescription()                 { return description;               }
    public double  getSize()                        { return size;                      }
    public int     getFloor()                       { return floor;                     }
    public double  getPrice()                       { return price;                     }

    public void    setName(String name)             { this.name = name;                 }
    public void    setDescription(String description) { this.description = description; }
    public void    setSize(double size)             { this.size = size;                 }
    public void    setFloor(int floor)              { this.floor = floor;               }
    public void    setPrice(double price)           { this.price = price;               }
}
