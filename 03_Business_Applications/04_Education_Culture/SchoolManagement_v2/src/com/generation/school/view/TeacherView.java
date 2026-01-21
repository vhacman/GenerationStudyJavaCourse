package com.generation.school.view;

import com.generation.school.model.entities.Teacher;

/**
 * View Layer per Teacher: gestisce la visualizzazione degli insegnanti
 * Estende PersonView per ereditare la logica di rendering dei dati anagrafici base

 * Questo è un esempio di COSTRUTTORE OBBLIGATORIO nella gerarchia di ereditarietà:
 * - La superclasse (PersonView) definisce un costruttore con parametri
 * - Java NON genera più il costruttore vuoto implicito
 * - La sottoclasse (TeacherView) DEVE chiamare esplicitamente super(template)
 * - Altrimenti il codice non compila
 */
public class TeacherView extends PersonView
{
    /**
     * Costruttore che inizializza la view con il percorso del template
     *
     * Template = percorso al file template (es. "template/templateTeacher.txt")
     *
     * @param template percorso del file template da utilizzare per il rendering
     */
    public TeacherView(String template)
    {
        // Richiamo il costruttore della classe padre (PersonView)
        // Questo è OBBLIGATORIO perché PersonView non ha un costruttore vuoto
        // super() deve essere sempre la PRIMA istruzione del costruttore figlio
        super(template);
    }

    /**
     * Overload del metodo render per gestire gli attributi specifici di Teacher
     * NON è un override perché il parametro è diverso (Teacher invece di Person)
     * Questo metodo:
     * - Chiama super.render(teacher) per processare i dati base di Person
     * - Aggiunge le sostituzioni specifiche di Teacher (subject e years)
     *
     * @param teacher l'insegnante da renderizzare
     * @return stringa con il template popolato con i dati dell'insegnante
     */
    public String render(Teacher teacher)
    {
        return super.render(teacher)
                .replace("[subject]", teacher.getSubject())
                .replace("[years]", teacher.getYears() + "");
    }
}
