package com.generation.pl.view;

import java.util.List;
import com.generation.library.Entity;
import com.generation.library.Template;
import java.lang.reflect.Method; 

public class ReflectionView
{
	//dice al compilatore java di ignorare warnings relativo al codice inutilizzato
	// per l'elemento annotato. 
    @SuppressWarnings("unused")
	private String filename;
    private String content;   // Template caricato UNA VOLTA nel costruttore (caching)

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
     * Invece di scrivere entity.getFirstName() direttamente, scopri dinamicamente
     * che esiste un metodo chiamato "getFirstName", lo invochi e ottieni il risultato.
     *
     * PROCESSO:
     * 1. Prendi tutti i metodi pubblici dell'Entity tramite .getMethods()
     * 2. Filtra solo i getter (metodi che iniziano con "get" e senza parametri)
     * 3. Per ogni getter:
     *    - Estrai il nome del campo (es. "getFirstName" -> "FirstName")
     *    - Converti in lowercase (es. "FirstName" -> "firstname")
     *    - Crea il placeholder (es. "firstname" -> "[firstname]")
     *    - Invoca il metodo sull'oggetto per ottenere il valore
     *    - Sostituisci nel template
     *
     * @param entity l'entità da renderizzare
     * @return stringa con i placeholder sostituiti
     */
    public String render(Entity entity)
    {
        String 		res = content; 
        Method[] 	methods = entity.getClass().getMethods();
        
        for(Method method : methods)
        {
        	if(method.getName().startsWith("get") && method.getParameterCount() == 0)
        	{
        		try
        		{
        			String fieldName = method.getName().substring(3).toLowerCase();
        			String placeholder = "[" + fieldName + "]";
        			Object value = method.invoke(entity);        			
        			if(value != null)
        				res = res.replace(placeholder, value.toString());
        			
        		}
        		catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return res;
    }

    /**
     * Renderizza una lista di Entity, concatenando il risultato.
     *
     * OVERLOAD DEI METODI:
     * Hai due metodi render() con firme diverse (parametri diversi):
     * - render(Entity entity) → prende una singola entità
     * - render(List<? extends Entity> items) → prende una lista di entità
     * Java sceglie automaticamente quale versione chiamare in base al tipo
     * di parametro che passi.
     *
     * QUANDO LO USI:
     * - Per renderizzare le righe di una tabella
     * - Ogni Entity viene renderizzata con lo stesso template
     * - I risultati vengono concatenati in un'unica stringa
     *
     * @param items lista di entità da renderizzare
     * @return stringa con tutti gli elementi renderizzati concatenati
     */
    public String render(List<? extends Entity> items)
    {
       StringBuilder res = new StringBuilder();
       for (Entity item : items)
    	   res.append(render(item));
       return res.toString();
    }
    
    /**
     * 
     * APPEND()
     * 
     * Il metodo append() aggiunge il valore passato come parametro alla fine della
     * sequenza corrente e restituisce lo stesso oggetto StringBuilder aggiornato. 
     * Questo significa che aumenta la lunghezza della sequenza senza creare nuovi oggetti.
     * Nel codice specifico, res.append(render(item)) prende la stringa risultante dal rendering di ogni entità e
     * la concatena al StringBuilder, costruendo progressivamente l'output finale. 
     * Alla fine, res.toString() converte il StringBuilder in una String normale da restituire.
     */
}
