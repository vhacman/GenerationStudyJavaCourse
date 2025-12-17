package com.generation.lbb.model.entities;

/**
 * ENUM = Tipo speciale di classe che definisce un insieme FISSO e LIMITATO di costanti.
 * 
 * Vantaggi rispetto alle stringhe:
 * - Type safety: il compilatore controlla che usi solo valori validi
 * - Ogni costante può avere proprietà e metodi propri
 * - Più efficiente in memoria (gli oggetti sono creati una sola volta)
 */
public enum RoomType
{
    // ═══════════════════════════════════════════════════════════════
    // COSTANTI PREDEFINITE (istanze dell'enum)
    // ═══════════════════════════════════════════════════════════════
    // Questi sono GLI UNICI 4 oggetti RoomType che esisteranno MAI.
    // Vengono creati automaticamente quando la classe viene caricata.
    // Il numero tra parentesi viene passato al costruttore.
    
    BASIC(50),      // Camera base: 50€ a notte
    MIDDLE(70),     // Camera media: 70€ a notte
    SUPERIOR(100),  // Camera superiore: 100€ a notte
    SUITE(200);     // Suite di lusso: 200€ a notte
    
    // NOTA: il punto e virgola dopo l'ultima costante è OBBLIGATORIO
    // se dopo ci sono altri membri della classe (proprietà/metodi)

    // ═══════════════════════════════════════════════════════════════
    // PROPRIETÀ (ATTRIBUTI)
    // ═══════════════════════════════════════════════════════════════
    // Ogni costante enum può avere le proprie proprietà.
    // Qui ogni tipo di camera ha un costo per notte diverso.
    
    public int costPerNight;
    // ═══════════════════════════════════════════════════════════════
    // COSTRUTTORE
    // ═══════════════════════════════════════════════════════════════
    // Il costruttore è SEMPRE privato (anche se non lo scrivi esplicitamente).
    // Questo impedisce di creare nuove istanze dall'esterno:
    // new RoomType(150) ← IMPOSSIBILE, darebbe errore di compilazione!
    //
    // Il costruttore viene chiamato automaticamente per ogni costante
    // definita sopra, passando il valore tra parentesi.
    
    private RoomType(int costPerNight)
    {
        this.costPerNight = costPerNight;  // Inizializza la proprietà
    }
}

// ═══════════════════════════════════════════════════════════════════════
// PERCHÉ USARE ENUM INVECE DI STRINGHE?
// ═══════════════════════════════════════════════════════════════════════

// Con String (PERICOLOSO):
// String tipo = "BASIK";  // ← Errore di battitura, ma compila!
// if (tipo.equals("BASIC")) { ... }  // ← Non entra mai nel blocco, BUG!

// Con Enum (SICURO):
// RoomType tipo = RoomType.BASIK;  // ← ERRORE DI COMPILAZIONE immediato!
// RoomType tipo = RoomType.BASIC;  // ← Funziona, verificato dal compilatore

// ═══════════════════════════════════════════════════════════════════════
// RIEPILOGO
// ═══════════════════════════════════════════════════════════════════════
// - Un enum è un TIPO DI DATO, non una stringa
// - Gli oggetti enum sono creati UNA SOLA VOLTA e sono IMMUTABILI
// - Non si possono creare nuove istanze dall'esterno (costruttore privato)
// - Offrono type safety: errori rilevati in fase di compilazione
// - Ogni costante può avere proprietà e comportamenti propri



