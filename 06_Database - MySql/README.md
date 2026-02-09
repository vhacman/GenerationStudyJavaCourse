# 🗄️ Database - MySQL

Materiale didattico MySQL: DDL, DML, query avanzate.

---

## 📂 Struttura

| Directory | Contenuto |
|-----------|------------|
| `Mod:01-02-03-04/` | DDL e DML base |
| `Mod:05-06/` | Query avanzate |

---

## 📚 Script SQL

### Fondamentali
| File | Descrizione |
|------|-------------|
| `01_create_table.sql` | CREATE TABLE PEOPLE |
| `02_insert_10_people.sql` | INSERT iniziale |
| `03_update_roles.sql` | UPDATE records |
| `04_delete_minors.sql` | DELETE condizionale |

### Query
| File | Descrizione |
|------|-------------|
| `05_insert_10_more.sql` | INSERT aggiuntivo |
| `06_select_queries.sql` | SELECT, WHERE, ORDER BY, LIKE |
| `07_aggregate_queries.sql` | GROUP BY, AVG, MIN, MAX, COUNT |

---

## 🗄️ Schema PEOPLE

| Campo | Tipo |
|-------|------|
| id | INT AUTO_INCREMENT |
| firstname | VARCHAR(50) |
| lastname | VARCHAR(50) |
| dob | DATE |
| city | VARCHAR(100) |
| role | VARCHAR(50) |
| salary | DECIMAL(10,2) |
| gender | CHAR(1) |

---

## 📖 Concetti Coperti

| Argomento | Descrizione |
|-----------|-------------|
| DDL | CREATE, ALTER, DROP |
| DML | INSERT, UPDATE, DELETE |
| SELECT | WHERE, ORDER BY, LIKE |
| Funzioni | CONCAT, COUNT, AVG, MIN, MAX |
| Aggregazione | GROUP BY, HAVING |
| Date | CURDATE, DATEDIFF |

---

## 🚀 Configurazione

```bash
# Avvio MySQL
mysql -u root -p

# Creazione database
CREATE DATABASE generation_db;
USE generation_db;

# Esecuzione script
SOURCE percorso/01_create_table.sql;
```

**Strumenti:** MySQL Workbench, DBeaver, phpMyAdmin, CLI

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
