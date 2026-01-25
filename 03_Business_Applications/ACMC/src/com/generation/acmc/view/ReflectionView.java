package com.generation.acmc.view;

import java.lang.reflect.Method;
import java.util.List;
import com.generation.library.Entity;
import com.generation.library.Template;

/**
 * ReflectionView: classe che ho creato per il rendering automatico delle Entity.
 *
 * PROBLEMA che risolvevo:
 * - Nel mio ViewController scrivevo manualmente ogni .replace() per ogni campo
 * - Codice ripetitivo 
 * - Ogni volta che aggiungevo un getter dovevo aggiornare manualmente tutte le view
 *
 * SOLUZIONE con Reflection:
 * - Uso la Reflection per iterare dinamicamente tutti i metodi get*() dell'Entity
 * - Mappo automaticamente ogni getter al suo placeholder nel template
 *   Es: getFirstName() → [firstname]
 * - Basta aggiungere un getter all'Entity e funziona automaticamente
 * SVANTAGGI:
 * - Non posso gestire facilmente parametri extra (es. oldLevel, newLevel)
 * - Gestione null limitata (usa solo toString())
 * - Più difficile da debuggare rispetto a replace manuali
 *
 * Ispirato a FlatView.java del corso, ma adattato al mio progetto ACMC.
 */
public class ReflectionView
{
	@SuppressWarnings("unused")
    private String filename; 
    private String content;   // Template caricato UNA VOLTA nel costruttore (caching)

    /**
     * Costruttore: carica il template UNA VOLTA SOLA.
     *
     * TEMPLATE CACHING:
     * - Carico il template nel costruttore e lo riutilizzo per ogni render()
     * - Evito di leggere il file dal disco ogni volta
     * - Performance migliori quando renderizzo molte entità (es. liste)
     */
    public ReflectionView(String filename)
    {
        this.filename = filename;
        this.content = Template.load(filename); 
    }

    /**
     * Renderizza una singola Entity sostituendo automaticamente i placeholder.
     * 
     * REFLECTION API:
     * La reflection è la capacità di Java di ispezionare e manipolare le classi a runtime.
     * Invece di scrivere member.getFirstName() direttamente, scopro dinamicamente
     * che esiste un metodo chiamato "getFirstName", lo invoco e ottengo il risultato.
     * 
     * PROCESSO:
     * 1. Prendo tutti i metodi pubblici dell'Entity tramite .getMethods()
     * 2. Filtro solo i getter (metodi che iniziano con "get" e senza parametri)
     * 3. Per ogni getter:
     *    - Estraggo il nome del campo (es. "getFirstName" -> "FirstName")
     *    - Converto in lowercase (es. "FirstName" -> "firstname")
     *    - Creo il placeholder (es. "firstname" -> "[firstname]")
     *    - Invoco il metodo sull'oggetto per ottenere il valore
     *    - Sostituisco nel template
     * 
     * @param entity l'entità da renderizzare
     * @return stringa con i placeholder sostituiti
     */
    public String render(Entity entity)
    {
        String res = content;  
        // REFLECTION: ottengo tutti i metodi dell'oggetto a runtime
        // invece di conoscerli a compile-time
        Method[] methods = entity.getClass().getMethods();
        
        for (Method method : methods)
        {
            // Filtro: voglio solo i getter (get* senza parametri)
            if (method.getName().startsWith("get") && method.getParameterCount() == 0)
            {
                try
                {
                    // COSTRUZIONE PLACEHOLDER:
                    // getName() restituisce il nome del metodo (es. "getFirstName")
                    // substring(3) rimuove "get" → "FirstName"
                    // toLowerCase() → "firstname"
                    // aggiungo le parentesi quadre → "[firstname]"
                    String placeholder = "[" + method.getName().substring(3) + "]";
                    placeholder = placeholder.toLowerCase();
                    
                    // METHOD.INVOKE():
                    // È il cuore della reflection. Eseguo dinamicamente il metodo sull'oggetto.
                    // Es: method.invoke(entity) esegue entity.getFirstName()
                    // Il valore di ritorno è Object perché Java non sa a priori quale tipo
                    // restituirà il metodo (potrebbe essere String, Integer, Boolean, ecc.)
                    Object value = method.invoke(entity);
                    
                    // Sostituisco solo se il valore non è null
                    // Uso toString() per convertire qualsiasi tipo in stringa
                    if (value != null)
                        res = res.replace(placeholder, value.toString());
                }
                catch (Exception exc)
                {
                    // Se qualcosa va storto, stampo l'errore ma continuo
                    // (alcuni metodi potrebbero lanciare eccezioni)
                    exc.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * Renderizza una lista di Entity, concatenando il risultato.
     *
     * OVERLOAD DEI METODI:
     * Ho due metodi render() con firme diverse (parametri diversi):
     * - render(Entity entity) → prende una singola entità
     * - render(List<? extends Entity> items) → prende una lista di entità
     * Java sceglie automaticamente quale versione chiamare in base al tipo
     * di parametro che passo.
     *
     * QUANDO LO USO:
     * - Per renderizzare le righe di una tabella
     * - Ogni Entity viene renderizzata con lo stesso template
     * - I risultati vengono concatenati in un'unica stringa
     *
     * @param items lista di entità da renderizzare
     * @return stringa con tutti gli elementi renderizzati concatenati
     */
    public String render(List<? extends Entity> items)
    {
        String res = "";
        for (Entity e : items)
            res += render(e);  // Chiamo l'altro render() per ogni elemento
        return res.toString();
    }
}
