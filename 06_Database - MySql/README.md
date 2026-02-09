<div align="center">

# Database — MySQL

Materiale didattico MySQL: DDL, DML e query avanzate con funzioni aggregate.

</div>

---

## Struttura

| Directory | Contenuto |
|-----------|-----------|
| `Mod:01-02-03-04/` | DDL e DML base |
| `Mod:05-06/` | Query avanzate |

---

## Script SQL

### Fondamentali (DDL & DML)

| File | Operazione | Descrizione |
|------|-----------|-------------|
| `01_create_table.sql` | `CREATE TABLE` | Creazione tabella PEOPLE con 8 campi |
| `02_insert_10_people.sql` | `INSERT` | Inserimento iniziale di 10 record |
| `03_update_roles.sql` | `UPDATE` | Aggiornamento ruoli e campi |
| `04_delete_minors.sql` | `DELETE` | Eliminazione condizionale (minorenni) |

### Query Avanzate

| File | Operazione | Descrizione |
|------|-----------|-------------|
| `05_insert_10_more.sql` | `INSERT` | Inserimento aggiuntivo di 10 record |
| `06_select_queries.sql` | `SELECT` | `WHERE`, `ORDER BY`, `LIKE`, `BETWEEN` |
| `07_aggregate_queries.sql` | Aggregate | `GROUP BY`, `AVG`, `MIN`, `MAX`, `COUNT`, `HAVING` |

---

## Schema: PEOPLE

| Campo | Tipo | Note |
|-------|------|------|
| `id` | `INT AUTO_INCREMENT` | Primary Key |
| `firstname` | `VARCHAR(50)` | |
| `lastname` | `VARCHAR(50)` | |
| `dob` | `DATE` | Data di nascita |
| `city` | `VARCHAR(100)` | |
| `role` | `VARCHAR(50)` | |
| `salary` | `DECIMAL(10,2)` | |
| `gender` | `CHAR(1)` | M / F |

---

## Concetti Coperti

| Categoria | Comandi / Funzioni |
|-----------|--------------------|
| **DDL** | `CREATE`, `ALTER`, `DROP` |
| **DML** | `INSERT`, `UPDATE`, `DELETE` |
| **Query** | `SELECT`, `WHERE`, `ORDER BY`, `LIKE`, `BETWEEN` |
| **Funzioni** | `CONCAT`, `COUNT`, `AVG`, `MIN`, `MAX` |
| **Aggregazione** | `GROUP BY`, `HAVING` |
| **Date** | `CURDATE()`, `DATEDIFF()` |

---

## Quick Start

```bash
# Avvio MySQL
mysql -u root -p

# Creazione database
CREATE DATABASE generation_db;
USE generation_db;

# Esecuzione script in ordine
SOURCE percorso/01_create_table.sql;
SOURCE percorso/02_insert_10_people.sql;
# ...
```

**Strumenti consigliati:** MySQL Workbench, DBeaver, phpMyAdmin, CLI

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna al README principale](../README.md)

</div>
