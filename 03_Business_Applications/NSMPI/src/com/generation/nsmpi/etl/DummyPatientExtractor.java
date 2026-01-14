package com.generation.nsmpi.etl;

import java.util.ArrayList;
import java.util.List;

import com.generation.nsmpi.model.entities.Patient;

/**
 * ESTRATTORE FARLOCCO
 */
public class DummyPatientExtractor implements PatientExtractor
{

    @Override
    public List<Patient> getPatientsFromFile(String filename) 
    {
        // ME NE FREGO DEL FILE, PRODUCO UNA LISTA CON DEI PAZIENTI FINTI
        
        List<Patient> res = new ArrayList<Patient>();
        
        //                 ID,   NOME,         COGNOME,     NASCITA,     SEX,  STORIA CLINICA,                                  ALLERGIE,                  H(m),  W(kg)
        res.add(new Patient(1,  "Vlad",       "Tepes",     "1920-01-01", "M", "Brutto carattere",                               "Allergia al sangue",      1.82,  75.0));
        res.add(new Patient(2,  "Leonardo",   "Da Vinci",  "1452-04-15", "M", "Sindrome del genio, dorme poco",                 "Polvere, Noia",           1.75,  70.0));
        res.add(new Patient(3,  "Marie",      "Curie",     "1867-11-07", "F", "Esposizione prolungata a materiali radianti",    "Nessuna",                 1.63,  55.0));
        res.add(new Patient(4,  "Sherlock",   "Holmes",    "1854-01-06", "M", "Iperattività cerebrale, uso di tabacco",         "Mediocrità",              1.83,  78.0));
        res.add(new Patient(5,  "Ada",        "Lovelace",  "1815-12-10", "F", "Primi segni di logica computazionale",           "Inchiostro",              1.68,  58.0));
        res.add(new Patient(6,  "Dante",      "Alighieri", "1265-05-21", "M", "Allucinazioni mistiche e smarrimenti...",        "Guelfi Neri",             1.70,  68.0));
        res.add(new Patient(7,  "Frida",      "Kahlo",     "1907-07-06", "F", "Dolori cronici alla colonna vertebrale",         "Nessuna",                 1.60,  52.0));
        res.add(new Patient(8,  "Mario",      "Rossi",     "1985-03-12", "M", "Sindrome metabolica leggera",                    "Graminacee, Lattosio",    1.78,  95.0));
        res.add(new Patient(9,  "Giulia",     "Bianchi",   "1992-11-23", "F", "Recente operazione al menisco",                  "Nichel, Arachidi",        1.65,  62.0));
        res.add(new Patient(10, "Luca",       "Verdi",     "1970-05-30", "M", "Ipertensione arteriosa",                         "Pelo di gatto",           1.80,  88.0));
        res.add(new Patient(11, "Elena",      "Neri",      "2001-08-14", "F", "Miopia elevata e stress da esami",               "Pellicillina",            1.72,  60.0));
        res.add(new Patient(12, "Alessandro", "Gialli",    "1964-12-01", "M", "Controllo di routine post-influenzale",          "Fragole, Crostacei",      1.76,  82.0));
        res.add(new Patient(13, "Sofia",      "Viola",     "1998-02-28", "F", "Anemia sideropenica",                             "Polline, Acari",          1.58,  48.0));
        
        return res;
    }
}