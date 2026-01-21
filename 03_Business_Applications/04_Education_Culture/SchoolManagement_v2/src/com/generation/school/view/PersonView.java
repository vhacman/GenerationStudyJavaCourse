package com.generation.school.view;
import com.generation.library.Template;
import com.generation.school.model.entities.Person;

/**
 * View Layer per Person: gestisce la visualizzazione delle persone base
 * Si occupa di graficare Persone "lisce": non impiegati né studenti
 *
 * Principio MVC: questa classe sa solo MOSTRARE dati, non calcolarli
 * Separa completamente la presentazione dalla logica di business
 *
 * Classe BASE per le view specializzate (EmployeeView, StudentView, ecc.)
 * che estenderanno questa classe per aggiungere attributi specifici
 */
public class PersonView
{
    /**
     * Percorso del file template da utilizzare per il rendering
     * Attributo protected per permettere l'accesso alle classi figlie
     */
    protected String		template;

    // ==================== COSTRUTTORI ===================
    /**
     * Costruttore che inizializza la view con il percorso del template
     *
     * IMPORTANTE: questo è un costruttore ESPLICITO con parametri
     * - Java NON genera il costruttore vuoto implicito
     * - Le classi figlie DEVONO chiamare super(template) nel loro costruttore
     * - Altrimenti si verifica un errore di compilazione
     *
     * Template = percorso al file (es. "template/templatePerson.txt")
     *
     * @param template percorso del file template da utilizzare per il rendering
     */
    public PersonView(String template)
    {
        this.template = template;
    }

    // ==================== METODI DI RENDERING ====================
    /**
     * Renderizza una persona sostituendo i placeholder del template con i dati reali
     *
     * Processo:
     * Template.load(template) carica il contenuto del file template
     *
     * @param p la persona da renderizzare
     * @return stringa con il template popolato con i dati della persona
     */
    public String render(Person p)
    {
        return Template
                .load(template)
                .replace("[id]", p.getId()+"")
                .replace("[name]", p.getSurname())
                .replace("[surname]", p.getName())
                .replace("[dateOfBirth]", p.getDateOfBirth().toString())
                .replace("[age]", p.getAge()+"");
    }
}