package com.generation.school.view;

import com.generation.school.model.entities.Student;

/**
 * View Layer per Student: gestisce la visualizzazione degli studenti
 * Estende PersonView per ereditare la logica di rendering dei dati anagrafici base
 * Questo è un esempio di COSTRUTTORE OBBLIGATORIO nella gerarchia di ereditarietà:
 * - La superclasse (PersonView) definisce un costruttore con parametri
 * - Java NON genera più il costruttore vuoto implicito
 * - La sottoclasse (StudentView) DEVE chiamare esplicitamente super(template)
 * - Altrimenti il codice non compila
 */
public class StudentView extends PersonView
{
    /**
     * Costruttore che inizializza la view con il percorso del template
     *
     * Template = percorso al file template (es. "template/templateStudent.txt")
     *
     * @param template percorso del file template da utilizzare per il rendering
     */
    public StudentView(String template)
    {
        // Richiamo il costruttore della classe padre (PersonView)
        // Questo è OBBLIGATORIO perché PersonView non ha un costruttore vuoto
        // super() deve essere sempre la PRIMA istruzione del costruttore figlio
        super(template);
    }

    /**
     * Overload del metodo render per gestire gli attributi specifici di Student
     * NON è un override perché il parametro è diverso (Student invece di Person)

     * Questo metodo:
     * - Chiama super.render(student) per processare i dati base di Person
     * - Aggiunge le sostituzioni specifiche di Student (year e section)
     * @param student lo studente da renderizzare
     * @return stringa con il template popolato con i dati dello studente
     */
    public String render(Student student)
    {
        return super.render(student)
                .replace("[year]", student.getYear() + "")
                .replace("[section]", student.getSection());
    }
}
