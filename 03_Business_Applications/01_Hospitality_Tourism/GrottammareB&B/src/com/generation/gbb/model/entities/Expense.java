package com.generation.gbb.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Rappresenta una spesa del B&B con data, descrizione, valore e categoria.
 * Estende Entity per ereditare validazione e gestione ID.
 *
 * Relazione OOP → Composition (Composizione):
 *      Expense  →  ExpenseCategory (enum)
 *      Expense "ha un" ExpenseCategory (relazione has-a)
 *      ExpenseCategory definisce i tipi di spesa ammessi
 */
public class Expense extends Entity
{
    private LocalDate        date;
    private String           description;
    private double           value;
    private ExpenseCategory  category;

    public Expense()
    {
    }

    /**
     * Costruttore parametrizzato con LocalDate.
     *
     * @param id          Identificativo univoco della spesa
     * @param date        Data della spesa (obbligatoria)
     * @param description Descrizione della spesa (obbligatoria)
     * @param value       Valore monetario (deve essere > 0)
     * @param category    Categoria della spesa (obbligatoria: FOOD, SERVICES, SALARIES)
     */
    public Expense(int id, LocalDate date, String description, int value, ExpenseCategory category)
    {
        super(id);
        this.date        = date;
        this.description = description;
        this.value       = value;
        this.category    = category;
    }

    /**
     * Costruttore parametrizzato con String date (parsing automatico).
     *
     * @param id          Identificativo univoco della spesa
     * @param date        Data in formato ISO (YYYY-MM-DD)
     * @param description Descrizione della spesa (obbligatoria)
     * @param value       Valore monetario (deve essere > 0)
     * @param category    Categoria della spesa (obbligatoria)
     */
    public Expense(int id, String date, String description, int value, ExpenseCategory category)
    {
        super(id);
        this.date        = LocalDate.parse(date);
        this.description = description;
        this.value       = value;
        this.category    = category;
    }

    /**
     * Valida tutti gli attributi della spesa secondo le regole di business.
     *
     * @return Lista di errori di validazione (vuota se valida)
     */
    @Override
    public List<String> getErrors()
    {
        /*
         * Pattern → Guard Clauses (Clausole di Guardia):
         *      Ogni condizione aggiunge un errore se fallisce
         *      Early validation previene stati inconsistenti
         *      Collecting Parameter accumula tutti gli errori
         */
        List<String> errors = new ArrayList<>();

        if (date == null)               errors.add("Data spesa obbligatoria");
        if (isMissing(description))     errors.add("Descrizione spesa obbligatoria");
        if (value <= 0)                 errors.add("Valore spesa deve essere positivo");
        if (category == null)           errors.add("Categoria spesa obbligatoria");

        return errors;
    }

    /**
     * Confronta questa spesa con un altro oggetto per uguaglianza.
     *
     * @param obj Oggetto da confrontare
     * @return true se uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)                return true;
        if (obj == null)                return true;
        if (!(obj instanceof Expense))  return false;

        Expense other = (Expense) obj;

        return this.date.equals(other.date)                 &&
               this.description.equals(other.description)   &&
               this.value == other.value                    &&
               this.category == other.category;
    }


    /**
     * Genera rappresentazione stringa della spesa per debug e logging.
     *
     * @return Stringa formattata con tutti gli attributi
     */
    @Override
    public String toString()
    {
        return "Expense{" +
               "id="          + getId()      +
               ", date="      + date         +
               ", description='" + description + '\'' +
               ", value="     + value        +
               ", category="  + category     +
               ", valid="     + isValid()    +
               '}';
    }

    // ==================== GETTER E SETTER ====================

    public LocalDate       getDate()                       { return date;                       }
    public String          getDescription()                { return description;                }
    public double          getValue()                      { return value;                      }
    public ExpenseCategory getCategory()                   { return category;                   }

    public void            setDate(LocalDate date)         { this.date = date;                  }
    public void            setDate(String date)            { this.date = LocalDate.parse(date); }
    public void            setDescription(String description) { this.description = description; }
    public void            setValue(double value)          			{ this.value = value;                }
    public void            setCategory(ExpenseCategory category) 	{ this.category = category;   }
}
