package com.generation.rs.view;

import com.generation.library.Template;
import com.generation.rs.model.entities.Repair;

/**
 * Classe RepairView
 * 
 * Questa classe si occupa della presentazione dei dati all'utente.
 * Nel pattern MVC (Model-View-Controller), la View è responsabile
 * di visualizzare le informazioni e gestire l'interfaccia utente.
 * 
 * In questo caso, RepairView gestisce la visualizzazione delle riparazioni,
 * mostrando i dati in modo formattato e leggibile.
 */
public class RepairView
{

	String filename;
	
	public	RepairView(String filename)
	{
		this.filename = filename;
	}
	
    public String render(Repair repair) 
    {
        return Template
                .load(filename)                    
                .replace("[id]", "" + repair.id)      
                .replace("[client]", "" + repair.client)  
                .replace("[phone]", "" + repair.phone) 
                .replace("[fix]", "" + repair.fix)
                .replace("[materialPartsCost]", "" + repair.materialPartsCost)
                .replace("[hour]", "" + repair.hour)
                .replace("[exprice]", repair.getExtimatedPrice() + "")
                .replace("[price]", "" + repair.price); 
    }
}
