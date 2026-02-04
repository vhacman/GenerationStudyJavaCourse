-- Eliminazione dei minorenni dalla tabella PEOPLE.
--
-- Criteri: età < 18 anni alla data odierna (2026-02-04).
-- La condizione DATEDIFF individua chi è nato dopo il
-- 2008-02-04, cioè chi ha ancora meno di 18 anni.
--
-- Persone eliminate:
--   id 6  -> Aria  Colombo  (nata  2010-06-20)
--   id 7  -> Ethan Ricci    (nato  2009-12-05)
--   id 8  -> Luna  Gallo    (nata  2011-02-18)

USE ex00;

-- Prima di eliminare, faccio un SELECT per vedere esattamente
-- quali righe verranno cancellate (buona pratica: mai fare un DELETE alla cieca)
SELECT id, firstname, lastname, dob, city, role
FROM people
WHERE DATEDIFF(CURDATE(), dob) / 365.25 < 18;
-- DATEDIFF(CURDATE(), dob) mi dà i giorni vissuti dalla nascita;
-- divido per 365.25 (anno medio) per ottenere l'età approssimativa in anni.
-- Se l'età è < 18 significa che è un minore.

-- Ora eliminazione dei minorenni con la stessa condizione del SELECT sopra
DELETE FROM people
WHERE DATEDIFF(CURDATE(), dob) / 365.25 < 18;

-- Verifico il risultato: dovrebbero restare 7 persone, tutte maggiorenni
SELECT * FROM people ORDER BY id;

-- Confermano con un conteggio rapido
SELECT COUNT(*) AS totale_persone FROM people;
