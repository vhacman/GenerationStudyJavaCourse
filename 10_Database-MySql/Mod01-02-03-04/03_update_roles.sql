-- Aggiornamento dei ruoli delle prime 10 persone per garantire:
--   - almeno 5 RESEARCHER
--   - almeno 8 FOREST GUARDIAN (sul totale finale, dopo il
--     secondo inserimento nel file 05)
--
-- Strategia sui primi 10:
--   id 1, 2, 3, 4, 5  -> RESEARCHER       (5 persone)
--   id 6, 7, 8, 9, 10 -> FOREST GUARDIAN  (5 persone)
--
-- Nota: i minorenni (id 6, 7, 8) ricevono anch'essi il ruolo
-- FOREST GUARDIAN ma verranno rimossi nel file 04.
-- Nel file 05 vengono inseriti altri 6 FOREST GUARDIAN per
-- raggiungere il totale di 8.

USE ex00;

-- Uso IN con una lista di id invece di fare 5 UPDATE separati:
-- così aggiorno tutti i RESEARCHER in un colpo solo
UPDATE people SET role = 'RESEARCHER' WHERE id IN (1, 2, 3, 4, 5);

-- Stesso discorso per i FOREST GUARDIAN;
-- nota: tra questi ci sono anche i 3 minorenni (id 6,7,8)
-- ma non importa, li eliminerò nel file 04
UPDATE people SET role = 'FOREST GUARDIAN' WHERE id IN (6, 7, 8, 9, 10);

-- Verifico che i ruoli siano cambiati come previsto
SELECT id, firstname, lastname, role, city FROM people ORDER BY id;

-- Faccio anche un conteggio rapido per ruolo per accertarmi
-- che siano 5 RESEARCHER e 5 FOREST GUARDIAN
SELECT role, COUNT(*) AS conteggio FROM people GROUP BY role;
