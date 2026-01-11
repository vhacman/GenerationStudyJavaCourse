# 🚂 Brianza Trains Objects

**Percorso:** `03_Business_Applications/BrianzaTrainsObjects`

## 🎯 Panoramica

**Brianza Trains Objects** è un sistema di biglietteria ferroviaria **orientato agli oggetti** che gestisce biglietti con **prezzi basati sulla distanza** per diverse classi di servizio. Include validazione completa, test unitari JUnit e generazione report HTML, applicando i principi OOP ai sistemi di trasporto.

## ✨ Caratteristiche Principali

| Feature | Descrizione |
|---------|-------------|
| 🎫 **Creazione Biglietti** | ID univoci + distanze validate (> 0 km) |
| 👑 **Classi di Servizio** | Prima e Seconda Classe con moltiplicatori distinti |
| 💰 **Calcolo Prezzi** | Formula: `base × distanza × classe_multiplier` |
| 🧪 **Test JUnit** | Verifica logica prezzi e validazioni |
| 📄 **Anteprima Testuale** | Visualizzazione formattata biglietto |
| 🌐 **Export HTML** | Report biglietti confermati |

## 🛠️ Tecnologie Utilizzate

```
Core: Java OOP, Encapsulation, Validation
Testing: JUnit 5 Framework  
Output: HTML Rendering + Console Preview
Design: Single Responsibility Principle
```

## 📚 Obiettivi di Apprendimento

| Obiettivo | Beneficio |
|-----------|-----------|
| **Entità Validate** | Oggetti robusti con controlli |
| **TDD** | Test prima del codice |
| **Calcoli Finanziari** | Logica prezzi reali |
| **Report HTML** | Template da Java |

## 📁 Struttura Progetto

```
src/main/java/com/generation/brianza/
├── Ticket.java           # Entità principale
├── TicketService.java    # Logica business
└── TicketPreview.java    # Output formattato

src/test/java/com/generation/brianza/
└── TicketTest.java       # Test unitari
```

## 💻 Come Eseguire

```bash
# Compilazione
javac -d bin src/**/*.java

# Test JUnit
java -cp "bin:lib/*" org.junit.runner.JUnitCore com.generation.brianza.TicketTest

# Demo
java -cp bin com.generation.brianza.TicketDemo
```

## 🧮 Formula Prezzi

```
PREZZO = €0.25/km × Distanza × Moltiplicatore
✓ Seconda Classe: × 1.0
✓ Prima Classe: × 1.35

Es: Milano-Lecco (50km, 1ª Cl.) = €16.88
```

## 🚀 Prossimi Passi

1. **Orari treni** con Ticket temporali
2. **Sconti** famiglie/bambini 
3. **Prenotazioni multiple**
4. **Database** persistenza dati

***

**👨‍💻 Autore:** Hacman Viorica Gabriela  
**🐙 GitHub:** [@vhacman](https://github.com/vhacman)  
**📧** hacmanvioricagabriela@gmail.com

**⭐** Lascia una ⭐ se utile!

