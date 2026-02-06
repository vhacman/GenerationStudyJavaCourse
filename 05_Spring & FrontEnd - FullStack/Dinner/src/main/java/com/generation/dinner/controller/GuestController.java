package com.generation.dinner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller MVC per il cliente del ristorante.
 * @Controller significa che questo oggetto restituisce pagine HTML (views),
 * a differenza di @RestController che restituisce JSON.
 * La stringa restituita dal metodo è il NOME del template Thymeleaf
 * da cercare nella cartella resources/templates/
 */
@Controller
public class GuestController
{
    /**
     * Mappa l'URL /dinnerservice/portal/newdinner alla pagina di inserimento ordine.
     * Spring cercherà il file templates/newDinner.html e lo invierà al browser.
     */
    @RequestMapping("/dinnerservice/portal/newdinner")
    public String insertDinner()
    {
        return "newDinner";
    }
}
