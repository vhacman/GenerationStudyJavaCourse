package com.generation.pl.model.entities;

import java.time.LocalDate; 
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class Admin extends User
{
    // Costante che definisce ogni quante settimane va cambiata la password
    private static final int 		PASSWORD_CHANGE_WEEKS = 2;
    // Data dell'ultimo cambio password per verificare la scadenza
    protected LocalDate          	dateLastPasswordChange;
    /**
     * Costruttore vuoto per inizializzazione base
     */
    public Admin() {}
    /**
     * Costruttore con data ultimo cambio password
     * @param dateLastPasswordChange data dell'ultimo cambio password
     */
    public Admin(LocalDate dateLastPasswordChange)
    {
        super();
        this.dateLastPasswordChange = dateLastPasswordChange;
    }
    /**
     * Verifica se la password deve essere cambiata
     * @return true se sono passate più di 2 settimane dall'ultimo cambio, false altrimenti
     */
    public boolean needsPasswordChange()
    {
        // Se non c'è una data registrata la password deve essere cambiata
        if (dateLastPasswordChange == null)
            return true;
        // Calcola i giorni trascorsi dall'ultimo cambio password
        long    daysSinceLastChange = ChronoUnit.DAYS.between(dateLastPasswordChange, LocalDate.now());
        // Verifica se è scaduto il periodo di validità (2 settimane = 14 giorni)
        boolean passwordExpired     = daysSinceLastChange >= (PASSWORD_CHANGE_WEEKS * 7);
        return passwordExpired;
    }
        
    /**
     * Restituisce la lista degli errori di validazione per l'Admin
     * @return lista di stringhe contenenti i messaggi di errore
     */
    @Override
    public List<String> 	getErrors()
    {
        // Recupera gli errori della classe padre User
        List<String> res = super.getErrors();
        // Controlla se la data dell'ultimo cambio password è presente
        if (dateLastPasswordChange == null)
            res.add("Date of last password change is required");
        // Controlla se la password è scaduta
        else if (needsPasswordChange())
            res.add("Password must be changed every " + PASSWORD_CHANGE_WEEKS + " weeks");
        return res;
    }
    /**
     * Genera l'hash code per l'oggetto Admin
     * @return valore hash calcolato
     */
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = super.hashCode();
        result = prime * result + Objects.hash(dateLastPasswordChange);
        return result;
    }
    /**
     * Verifica l'uguaglianza tra due oggetti Admin
     * @param obj oggetto da confrontare
     * @return true se gli oggetti sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Admin other = (Admin) obj;
        return Objects.equals(dateLastPasswordChange, other.dateLastPasswordChange);
    }
    /**
     * Rappresentazione testuale dell'oggetto Admin
     * @return stringa con i dati dell'Admin
     */
    @Override
    public String toString()
    {
        return "Admin [dateLastPasswordChange=" + dateLastPasswordChange + "]";
    }
    
    // GETTER
    public LocalDate getDateLastPasswordChange() 								{ return dateLastPasswordChange; }

    // SETTER
    public void 	setDateLastPasswordChange(LocalDate dateLastPasswordChange) { this.dateLastPasswordChange = dateLastPasswordChange; }
    public void 	setDateLastPasswordChange(String dateLastPasswordChange) 	{ this.dateLastPasswordChange = LocalDate.parse(dateLastPasswordChange); }
 
}
