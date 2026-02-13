package com.generation.demo;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo che illustra operazioni di mappatura (transformation) su array.
 * Mostra come trasformare array di stringhe in formati diversi.
 */
public class Demo002Vettori
{
    /**
     * Metodo principale che esegue le operazioni di mappatura.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Array di nomi completi (fisso, non modificabile)
        String[] fullnames =
            {
                "Ferdinando Primerano",
                "Chiara Foniciello",
                "Veronica Perrella",
                "Alessio Petrosino"
            };
        List<String> iniziali = trasformaInIniziali(fullnames);
        System.out.println(iniziali); // Output: [F.P., C.F., V.P., A.P.]
        List<String> inizialeCognome = trasformaInInizialeECognome(fullnames);
        System.out.println(inizialeCognome);
        // Output: [F.Primerano, C.Foniciello, V.Perrella, A.Petrosino]
    }

    /**
     * Trasforma un array di nomi completi in una lista di iniziali.
     * Esempio: "Ferdinando Primerano" → "F.P."
     *
     * @param fullnames array di nomi completi nel formato "Nome Cognome"
     * @return lista di stringhe con formato "N.C."
     */
    private static List<String> trasformaInIniziali(String[] fullnames)
    {
        // MAPPATURA: trasformo ogni elemento dell'array in un formato diverso
        // Creo una lista vuota dove metterò i risultati
        List<String> res = new ArrayList<String>();
        // Ciclo enhanced for: per ogni nome completo nell'array
        for(String fullname : fullnames)
        {
            // fullname = "Ferdinando Primerano"
            // split(" ") divide la stringa usando lo spazio come separatore
            String[] parts = fullname.split(" ");
            // parts[0] => "Ferdinando"   parts[1] => "Primerano"
            String firstName = parts[0];  // Ferdinando
            String lastName = parts[1];   // Primerano
            // charAt(0) prende il primo carattere della stringa
            // Creo formato: F.P.
            String anon = firstName.charAt(0) + "." + lastName.charAt(0) + ".";
            // Aggiungo il risultato alla lista
            res.add(anon);
        }
        // RECAP: ho mappato un array di stringhe a una lista di stringhe
        // Partito da array lungo 4 → ottenuto lista lunga 4
        // Ma il contenuto è stato trasformato (formato diverso)
        return res;
    }

    /**
     * Trasforma un array di nomi completi in una lista con iniziale del nome e cognome completo.
     * Esempio: "Ferdinando Primerano" → "F.Primerano"
     *
     * @param fullnames array di nomi completi nel formato "Nome Cognome"
     * @return lista di stringhe con formato "N.Cognome"
     */
    private static List<String> trasformaInInizialeECognome(String[] fullnames)
    {
        List<String> resFirstInitalLastname = new ArrayList<>();
        for(String fullname : fullnames)
        {
            // Stesso procedimento: divido il nome completo
            String[] parts = fullname.split(" ");
            String firstName = parts[0];   // Ferdinando
            String lastName = parts[1];    // Primerano
            // Formato diverso: prima iniziale + punto + cognome intero
            String formatted = firstName.charAt(0) + "." + lastName;
            resFirstInitalLastname.add(formatted);
        }
        return resFirstInitalLastname;
    }
}
