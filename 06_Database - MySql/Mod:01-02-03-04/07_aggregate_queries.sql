-- Query aggregate sulla tabella PEOPLE.
-- Stato della tabella: dopo esecuzione di 01..05
-- (17 persone, minorenni già eliminati).

USE ex00;


-- ------------------------------------------------------------
-- QUERY 1
-- Stipendio medio raggruppato per professione (role).
-- ------------------------------------------------------------
-- Prima query aggregate: raggruppamento base.
-- GROUP BY role crea un gruppo per ogni valore distinto di role;
-- AVG(salary) calcola la media dello stipendio dentro ogni gruppo.
SELECT role,
       AVG(salary) AS stipendio_medio
FROM people
GROUP BY role;

-- Risultato atteso:
--   role             | stipendio_medio
--   FOREST GUARDIAN  | 2256.25          (18050 / 8)
--   RESEARCHER       | 2344.44          (21100 / 9)


-- ------------------------------------------------------------
-- QUERY 2
-- Stipendio medio raggruppato per professione E genere.
-- ------------------------------------------------------------
-- Qui faccio GROUP BY su due colonne: role e gender.
-- MySQL crea un gruppo per ogni combinazione distinta (es. RESEARCHER+M, RESEARCHER+F, ecc.)
-- e calcola la media dello stipendio in ciascuno di quei gruppi.
SELECT role,
       gender,
       AVG(salary) AS stipendio_medio
FROM people
GROUP BY role, gender;

-- Risultato atteso:
--   role             | gender | stipendio_medio
--   FOREST GUARDIAN  | F      | 2137.50    (Chiara 2600 + Valentina 1800 + Francesca 2200 + Silvia 1950) / 4
--   FOREST GUARDIAN  | M      | 2375.00    (Andrea 2300 + Paolo 2100 + Roberto 2350 + Matteo 2750) / 4
--   RESEARCHER       | F      | 2400.00    (Elena 2200 + Sara 2100 + Giulia 1900 + Alice 2800 + Monica 3000) / 5
--   RESEARCHER       | M      | 2275.00    (Marco 2500 + Luca 2400 + Giovanni 2450 + Daniele 1750) / 4


-- ------------------------------------------------------------
-- QUERY 3
-- Ripresa della query 2, escludendo gli under 20
-- (persone con età < 20 anni rispetto alla data corrente).
-- Nota: nei dati attuali nessuna persona è sotto i 20 anni;
-- il filtro è comunque corretto e applicabile in generale.
-- ------------------------------------------------------------
-- Ripresa della query 2 ma con un WHERE prima del GROUP BY.
-- Il WHERE viene valutato PRIMA del raggruppamento, quindi
-- filtra le righe che non soddisfano la condizione e poi raggruppa il resto.
-- In questo caso nessuna persona è sotto i 20, quindi il risultato è uguale a query 2.
SELECT role,
       gender,
       AVG(salary) AS stipendio_medio
FROM people
WHERE DATEDIFF(CURDATE(), dob) / 365.25 >= 20
GROUP BY role, gender;

-- Risultato atteso: identico a Query 2 (nessun under 20 nei dati)


-- ------------------------------------------------------------
-- QUERY 4
-- Ripresa della query 3, con stipendio massimo e minimo
-- per ciascun gruppo (role + gender).
-- ------------------------------------------------------------
-- Oltre alla media, aggiungo MIN e MAX per ogni gruppo.
-- Così vedo, per ogni combinazione role+gender, qual è lo stipendio
-- più basso e più alto oltre alla media.
SELECT role,
       gender,
       AVG(salary) AS stipendio_medio,
       MIN(salary) AS stipendio_minimo,
       MAX(salary) AS stipendio_massimo
FROM people
WHERE DATEDIFF(CURDATE(), dob) / 365.25 >= 20
GROUP BY role, gender;

-- Risultato atteso:
--   role             | gender | medio   | minimo  | massimo
--   FOREST GUARDIAN  | F      | 2137.50 | 1800.00 | 2600.00
--   FOREST GUARDIAN  | M      | 2375.00 | 2100.00 | 2750.00
--   RESEARCHER       | F      | 2400.00 | 1900.00 | 3000.00
--   RESEARCHER       | M      | 2275.00 | 1750.00 | 2500.00


-- ------------------------------------------------------------
-- QUERY 5
-- Ripresa della query 4, ordinata per professione (role)
-- in ordine alfabetico ascendente.
-- ------------------------------------------------------------
-- Stessa query 4, l'unica differenza è ORDER BY role alla fine.
-- Ordina i gruppi per nome della professione in ordine alfabetico:
-- FOREST GUARDIAN viene prima di RESEARCHER (F < R).
SELECT role,
       gender,
       AVG(salary) AS stipendio_medio,
       MIN(salary) AS stipendio_minimo,
       MAX(salary) AS stipendio_massimo
FROM people
WHERE DATEDIFF(CURDATE(), dob) / 365.25 >= 20
GROUP BY role, gender
ORDER BY role;

-- Risultato atteso (ordine: FOREST GUARDIAN poi RESEARCHER):
--   role             | gender | medio   | minimo  | massimo
--   FOREST GUARDIAN  | F      | 2137.50 | 1800.00 | 2600.00
--   FOREST GUARDIAN  | M      | 2375.00 | 2100.00 | 2750.00
--   RESEARCHER       | F      | 2400.00 | 1900.00 | 3000.00
--   RESEARCHER       | M      | 2275.00 | 1750.00 | 2500.00
