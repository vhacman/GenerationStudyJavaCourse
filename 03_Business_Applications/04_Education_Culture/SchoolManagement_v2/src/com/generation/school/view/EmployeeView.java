package com.generation.school.view;

import com.generation.school.model.entities.Employee;

/**
 * View Layer per Employee: gestisce la visualizzazione degli impiegati
 * Estende PersonView per ereditare la logica di rendering dei dati anagrafici base
 *
 * PersonView mi sta dicendo:
 * "Io per lavorare ho BISOGNO DI UN TEMPLATE"
 * "Mio figlio non mi sta fornendo un template? Io mi rifiuto"
 * "Io, PersonView, ho un costruttore esplicito e non ne ho di implicito"
 * "Se qualcuno vuole estendermi DEVE richiamare un costruttore esplicito"
 *
 * Questo è un esempio di COSTRUTTORE OBBLIGATORIO nella gerarchia di ereditarietà:
 * - La superclasse (PersonView) definisce un costruttore con parametri
 * - Java NON genera più il costruttore vuoto implicito
 * - La sottoclasse (EmployeeView) DEVE chiamare esplicitamente super(template)
 * - Altrimenti il codice non compila
 */
public class EmployeeView extends PersonView
{
    /**
     * Costruttore che inizializza la view con il percorso del template
     *
     * Template = percorso al file template (es. "template/templateEmployee.txt")
     *
     * @param template percorso del file template da utilizzare per il rendering
     */
    public EmployeeView(String template)
    {
        // Richiamo il costruttore della classe padre (PersonView)
        // Questo è OBBLIGATORIO perché PersonView non ha un costruttore vuoto
        // super() deve essere sempre la PRIMA istruzione del costruttore figlio
        super(template);
    }

    /**
     * Overload del metodo render per gestire gli attributi specifici di Employee
     * NON è un override perché il parametro è diverso (Employee invece di Person)
     *
     * Overload vs Override:
     * - Overload: stesso nome metodo, PARAMETRI DIVERSI (tipo, numero, ordine)
     *   Può avvenire nella stessa classe o in classi diverse, NON richiede ereditarietà
     *   Esempio: render(Person), render(Employee), render(Student) nella stessa o in classi diverse
     * - Override: stesso nome metodo, STESSI PARAMETRI, richiede ereditarietà
     *   La classe figlia sovrascrive il metodo della classe padre
     *
     * Questo metodo:
     * - Chiama super.render(employee) per processare i dati base di Person
     * - Aggiunge le sostituzioni specifiche di Employee (salary e role)
     *
     * @param employee l'impiegato da renderizzare
     * @return stringa con il template popolato con i dati dell'impiegato
     */
    public String render(Employee employee)
    {
        return super.render(employee)
                .replace("[salary]", employee.getSalary() + "")
                .replace("[role]", employee.getRole());
    }
}
