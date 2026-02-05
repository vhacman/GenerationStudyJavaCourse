package com.generation.vault.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Produco pagine web per l'ospite
 */
@Controller
public class VaultController
{
    // Redirect alla risorsa statica requestmembership.html
    @RequestMapping("/vaultservice/portal/newRequest")
    public String insertTicket()
    {
        // Redirect alla pagina HTML statica
        return "redirect:/vault/requestmembership.html";
    }

    // Redirect alla risorsa statica viewpending.html
    @RequestMapping("/vaultservice/portal/viewPending")
    public String viewPending()
    {
        // Redirect alla pagina HTML statica
        return "redirect:/vault/viewpending.html";
    }
}
