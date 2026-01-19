package com.generation.library.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: EREDITARIETÀ (Inheritance)
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: Meccanismo OOP che permette a una classe (sottoclasse) di
 *              ereditare campi e metodi da un'altra classe (superclasse).
 *
 * Sintassi: `public class Sottoclasse extends Superclasse`
 *
 * Scopo: Riuso del codice e creazione di gerarchie is-a.
 *        Car IS-A Entity → Car eredita id, isValid(), isMissing()
 *
 * Vantaggi:
 *   - Riuso: comportamento comune ereditato (no duplicazione)
 *   - Polimorfismo: sottoclassi trattate come superclasse
 *   - Estensibilità: nuove sottoclassi senza modificare codice esistente
 *
 * Override:
 *   - Ridefinire metodi ereditati con @Override
 *   - Obbligatorio per metodi astratti della superclasse
 *   - Permette specializzazione comportamento
 * ═══════════════════════════════════════════════════════════════════════
 */

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: VALIDAZIONE ENTITÀ (Entity Validation)
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: Processo di verifica che un oggetto rispetti regole di business
 *              prima di persistenza o elaborazione.
 *
 * Pattern utilizzato: Collect Errors
 *   - Raccogliere TUTTI gli errori invece di fallire al primo
 *   - Restituire List<String> con messaggi descrittivi
 *   - Permette UI di mostrare feedback completo all'utente
 *
 * Metodi chiave:
 *   - getErrors(): raccoglie errori specifici dell'entità
 *   - isValid(): verifica assenza errori (isEmpty())
 *
 * Regole di validazione comuni:
 *   - NOT NULL: campi obbligatori non vuoti
 *   - Range: valori numerici > 0, lunghezza stringa, ecc.
 *   - Format: email, telefono, codice fiscale, ecc.
 * ═══════════════════════════════════════════════════════════════════════
 */

public class Car extends Entity
{
    String  model;
    String  plate;
    double  price;

    public Car() {}
    
    public Car(String model, String plate, double price)
    {
        super();
        this.model = model;
        this.plate = plate;
        this.price = price;
    }

    @Override
    public List<String> getErrors()
    {
        List<String> res = new ArrayList<String>();

        if(isMissing(model))
            res.add("Missing model");
            
        if(isMissing(plate))
            res.add("Missing plate");
            
        if(price <= 0)
            res.add("Invalid price");

        return res;
    }

    @Override
    public String toString()
    {
        return "Car[model=" + model + ", plate=" + plate + ", price=" + price + "]";
    }

    public String getModel()                { return model; }
    public void setModel(String model)      { this.model = model; }
    
    public String getPlate()                { return plate; }
    public void setPlate(String plate)      { this.plate = plate; }
    
    public double getPrice()                { return price; }
    public void setPrice(double price)      { this.price = price; }
}
