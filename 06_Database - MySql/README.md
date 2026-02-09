# 🗄️ Database - MySQL

Materiale didattico per il corso di database MySQL, coprendo DDL, DML, query avanzate e concetti fondamentali di database relazionali.

## 📁 Struttura

```
06_Database - MySql/
├── Mod:01-02-03-04/    # Moduli 1-4: DDL e DML base
└── Mod:05-06/          # Moduli 5-6: Query avanzate e aggregazioni
```

## 📚 Contenuti

### Mod:01-02-03-04 - Fondamentali SQL

| File | Descrizione | Argomenti |
|------|-------------|-----------|
| `01_create_table.sql` | Creazione tabella PEOPLE | CREATE TABLE, tipi di dati |
| `02_insert_10_people.sql` | Inserimento dati iniziali | INSERT, VALUES |
| `03_update_roles.sql` | Aggiornamento records | UPDATE, SET, WHERE |
| `04_delete_minors.sql` | Eliminazione dati | DELETE, WHERE condizionale |

**Schema tabella PEOPLE:**
- `id` - Identificativo univoco
- `firstname` - Nome
- `lastname` - Cognome
- `dob` - Data di nascita
- `city` - Città
- `role` - Ruolo
- `salary` - Salario
- `gender` - Genere

### Mod:05-06 - Query Avanzate

| File | Descrizione | Argomenti |
|------|-------------|-----------|
| `05_insert_10_more.sql` | Inserimento dati aggiuntivi | INSERT multiplo |
| `06_select_queries.sql` | Query SELECT base | WHERE, ORDER BY, LIKE, CONCAT |
| `07_aggregate_queries.sql` | Query aggregate | GROUP BY, HAVING, AVG, MIN, MAX, COUNT |

## 🔍 Query Principali

### Query di Selezione
```sql
-- Seleziona tutti
SELECT * FROM people;

-- Filtra per città
SELECT * FROM people WHERE city = 'Milano';

-- Ricerca con LIKE
SELECT * FROM people WHERE firstname LIKE 'A%';

-- Ordinamento
SELECT * FROM people ORDER BY salary DESC;

-- Concatenazione campi
SELECT CONCAT(firstname, ' ', lastname) AS nome_completo FROM people;
```

### Query Aggregate
```sql
-- Media salario per ruolo
SELECT role, AVG(salary) FROM people GROUP BY role;

-- Massimo e minimo
SELECT role, MIN(salary), MAX(salary) FROM people GROUP BY role;

-- Conteggio
SELECT gender, COUNT(*) FROM people GROUP BY gender;
```

### Operazioni DML
```sql
-- Inserimento
INSERT INTO people (firstname, lastname, city, role, salary, gender)
VALUES ('Mario', 'Rossi', 'Roma', 'EXPLORER', 45000.00, 'M');

-- Aggiornamento
UPDATE people SET role = 'RESEARCHER' WHERE id <= 5;

-- Eliminazione
DELETE FROM people WHERE DATEDIFF(CURDATE(), dob) / 365 < 18;
```

## 📖 Concetti Coperti

| Argomento | Descrizione |
|-----------|-------------|
| **DDL** | CREATE TABLE, ALTER, DROP |
| **DML** | INSERT, UPDATE, DELETE |
| **SELECT** | WHERE, ORDER BY, LIKE, DISTINCT |
| **Funzioni** | CONCAT, COUNT, AVG, MIN, MAX, SUM |
| **Aggregazione** | GROUP BY, HAVING |
| **Date** | CURDATE(), DATEDIFF(), YEAR(), MONTH() |

## 🛠️ Strumenti Consigliati

- **MySQL Workbench** - GUI ufficiale
- **DBeaver** - Client multi-database
- **phpMyAdmin** - Web interface
- **MySQL Command Line** - Terminale

## 🚀 Prima Configurazione

1. **Avviare MySQL Server:**
   ```bash
   # Windows
   net start mysql

   # macOS
   brew services start mysql

   # Linux
   sudo systemctl start mysql
   ```

2. **Connettersi:**
   ```bash
   mysql -u root -p
   ```

3. **Creare database:**
   ```sql
   CREATE DATABASE generation_db;
   USE generation_db;
   ```

4. **Eseguire script:**
   ```sql
   SOURCE percorso/01_create_table.sql;
   ```

## 📊 Schema ER (Entità-Relazioni)

```
PEOPLE
├── id (PK) - INT AUTO_INCREMENT
├── firstname - VARCHAR(50)
├── lastname - VARCHAR(50)
├── dob - DATE
├── city - VARCHAR(100)
├── role - VARCHAR(50)
├── salary - DECIMAL(10,2)
└── gender - CHAR(1)
```

## 🎯 Obiettivi di Apprendimento

- Comprendere il modello relazionale
- Scrivere query SQL efficienti
- Gestire dati con DML
- Usare funzioni aggregate
- Ottimizzare query

## 📝 Note

- Gli script sono progressivi: eseguirli in ordine numerico
- Alcuni script assumono che i precedenti siano già stati eseguiti
- I dati di esempio sono fittizi e usati per scopi didattici

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
