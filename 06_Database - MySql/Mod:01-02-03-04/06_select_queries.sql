-- Query di selezione sulla tabella PEOPLE.
-- Stato della tabella: dopo esecuzione di 01..05
-- (17 persone, minorenni già eliminati).

USE ex00;


-- ------------------------------------------------------------
-- QUERY 1
-- Nome e cognome di tutti i residenti di RACCOON VILLE
-- ------------------------------------------------------------
-- Filtro semplice: WHERE sulla città. Punto di partenza.
SELECT firstname, lastname
FROM people
WHERE city = 'RACCOON VILLE';

-- Risultato atteso (7 righe):
--   Elena      Rossi
--   Luca       Ferrari
--   Chiara     Barbieri
--   Paolo      Conti
--   Francesca  Lenti
--   Giovanni   Tan
--   Monica     Caputo


-- ------------------------------------------------------------
-- QUERY 2
-- Nome e cognome dei residenti di RACCOON VILLE con più di 30 anni
-- (età calcolata rispetto alla data corrente)
-- ------------------------------------------------------------
-- Ripreso il filtro sulla città dalla query 1, aggiungo il calcolo dell'età.
-- DATEDIFF(CURDATE(), dob) -> differenza in giorni tra oggi e la nascita
-- diviso 365.25 -> converti in anni (uso 365.25 per tenere conto degli anni bisestili)
SELECT firstname, lastname
FROM people
WHERE city = 'RACCOON VILLE'
  AND DATEDIFF(CURDATE(), dob) / 365.25 > 30;

-- Risultato atteso (5 righe):
--   Elena      Rossi       (35 anni)
--   Luca       Ferrari     (37 anni)
--   Chiara     Barbieri    (32 anni)
--   Paolo      Conti       (39 anni)
--   Giovanni   Tan         (36 anni)
-- Escluse: Francesca Lenti (28 anni), Monica Caputo (27 anni)


-- ------------------------------------------------------------
-- QUERY 3
-- Nome, cognome e professione dei residenti di RACCOON VILLE o LISBON,
-- con età fra i 20 e i 30 anni e stipendio maggiore di 2000 euro
-- ------------------------------------------------------------
-- Uso IN per includere due città in una sola condizione (più pulito di due OR).
-- BETWEEN 20 AND 30 per l'intervallo di età (inclusivo ai due estremi).
-- Aggiungo anche il filtro sullo stipendio con AND.
SELECT firstname, lastname, role
FROM people
WHERE city IN ('RACCOON VILLE', 'LISBON')
  AND DATEDIFF(CURDATE(), dob) / 365.25 BETWEEN 20 AND 30
  AND salary > 2000;

-- Risultato atteso (3 righe):
--   Francesca  Lenti   FOREST GUARDIAN   (28 anni, 2200 €)
--   Alice      Serra   RESEARCHER        (27 anni, 2800 €)
--   Monica     Caputo  RESEARCHER        (27 anni, 3000 €)


-- ------------------------------------------------------------
-- QUERY 4
-- Ripresa della query 3, ordinata per stipendio DISCENDENTE
-- ------------------------------------------------------------
-- Stessa query 3, ma ordino i risultati dal più alto stipendio al più basso
-- con ORDER BY salary DESC
SELECT firstname, lastname, role
FROM people
WHERE city IN ('RACCOON VILLE', 'LISBON')
  AND DATEDIFF(CURDATE(), dob) / 365.25 BETWEEN 20 AND 30
  AND salary > 2000
ORDER BY salary DESC;

-- Risultato atteso (3 righe, ordinate per salary):
--   Monica     Caputo  RESEARCHER        (3000 €)
--   Alice      Serra   RESEARCHER        (2800 €)
--   Francesca  Lenti   FOREST GUARDIAN   (2200 €)


-- ------------------------------------------------------------
-- QUERY 5
-- Ripresa della query 4, solo chi ha la lettera 'E' nel cognome
-- (LIKE è case-insensitive di default nel collation utf8_general_ci)
-- ------------------------------------------------------------
-- Aggiungo un filtro LIKE sul cognome.
-- '%e%' significa: qualsiasi carattere, poi una 'e', poi qualsiasi carattere
-- -> cerca la lettera 'e' in qualsiasi posizione del cognome.
-- Con la collation predefinita 'e' e 'E' sono equivalenti.
SELECT firstname, lastname, role
FROM people
WHERE city IN ('RACCOON VILLE', 'LISBON')
  AND DATEDIFF(CURDATE(), dob) / 365.25 BETWEEN 20 AND 30
  AND salary > 2000
  AND lastname LIKE '%e%'
ORDER BY salary DESC;

-- Risultato atteso (2 righe, Monica Caputo esclusa):
--   Alice      Serra   RESEARCHER        (2800 €)  -> S-e-rra
--   Francesca  Lenti   FOREST GUARDIAN   (2200 €)  -> L-e-nti


-- ------------------------------------------------------------
-- QUERY 6
-- Ripresa della query 5, con nome e cognome concatenati in un
-- unico campo "citizen" e con l'anno di nascita
-- ------------------------------------------------------------
-- CONCAT(firstname, ' ', lastname) unisce nome e cognome con uno spazio
-- e AS citizen gli dà un nome leggibile nel risultato.
-- YEAR(dob) mi estrae solo l'anno dalla data di nascita.
SELECT CONCAT(firstname, ' ', lastname) AS citizen,
       YEAR(dob)                        AS anno_nascita,
       role
FROM people
WHERE city IN ('RACCOON VILLE', 'LISBON')
  AND DATEDIFF(CURDATE(), dob) / 365.25 BETWEEN 20 AND 30
  AND salary > 2000
  AND lastname LIKE '%e%'
ORDER BY salary DESC;

-- Risultato atteso (2 righe):
--   citizen            | anno_nascita | role
--   Alice Serra        | 1998         | RESEARCHER
--   Francesca Lenti    | 1997         | FOREST GUARDIAN
