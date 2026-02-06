package com.generation.emergency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CONTROLLER vs REST_CONTROLLER:
 * - @Controller: Restituisce pagine HTML (views)
 * - @RestController: Restituisce dati JSON/XML (API REST)
 */
@Controller
public class OperatorController
{
    /**
     * @RequestMapping: Associa questo metodo all'URL specificato
     * Quando l'utente naviga su "/emergency/operator" viene invocato questo metodo
     */
    @RequestMapping("/emergency/operator")
    public String operatorHomePage()
    {
        /** IMPORTANTE: La stringa restituita NON è il contenuto HTML,
         *  ma il NOME del file da cercare nella cartella "templates/"
         *  Spring Boot cercherà automaticamente: templates/operatorhome.html */
        return "operatorhome";
    }
}