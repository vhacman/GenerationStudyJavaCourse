-- Inserimento di altre 10 persone con stipendio tra 1500 e 3000 euro.
--
-- Stato della tabella prima di questo inserimento (7 persone):
--   RESEARCHER     : 5  (id 1, 2, 3, 4, 5)
--   FOREST GUARDIAN: 2  (id 9, 10)
--
-- Obiettivo finale:
--   RESEARCHER      >= 5   (già soddisfatto)
--   FOREST GUARDIAN >= 8   (servono altri 6 FG)
--
-- Distribuzione dei nuovi 10:
--   6 x FOREST GUARDIAN  -> totale FG diventa 8
--   4 x RESEARCHER       -> totale R  diventa 9
--
-- Distribuzione per città:
--   RACCOON VILLE : 4 persone
--   LISBON        : 3 persone
--   NEW YORK      : 3 persone

USE ex00;

-- Inserisco altri 10 per raggiungere i totali richiesti dalle query successive.
-- Metto 6 FOREST GUARDIAN perché nel file 04 ne sono rimasti solo 2 (Andrea e Chiara)
-- e mi servono almeno 8; con questi 6 arrivo a 8.
-- GLI altri 4 sono RESEARCHER (arrivano a 9 totali).
-- Gli stipendi li metto tra 1500 e 3000 come da tracce.
-- AUTO_INCREMENT riparte da 11 perché gli id 1-10 sono già usati
-- (anche se 6,7,8 sono stati eliminati, MySQL non riusa gli id).
INSERT INTO people (firstname, lastname, dob, city, role, salary, gender) VALUES
('Paolo',    'Conti',      '1986-04-10', 'RACCOON VILLE', 'FOREST GUARDIAN', 2100.00, 'M'),  -- id 11
('Valentina','Greco',      '1991-08-17', 'LISBON',        'FOREST GUARDIAN', 1800.00, 'F'),  -- id 12
('Roberto',  'Mancini',    '1989-12-03', 'NEW YORK',      'FOREST GUARDIAN', 2350.00, 'M'),  -- id 13
('Francesca','Lenti',      '1997-02-28', 'RACCOON VILLE', 'FOREST GUARDIAN', 2200.00, 'F'),  -- id 14
('Matteo',   'Rossi',      '1982-06-19', 'LISBON',        'FOREST GUARDIAN', 2750.00, 'M'),  -- id 15
('Silvia',   'Neri',       '1996-10-05', 'NEW YORK',      'FOREST GUARDIAN', 1950.00, 'F'),  -- id 16
('Giovanni', 'Tan',        '1990-01-23', 'RACCOON VILLE', 'RESEARCHER',      2450.00, 'M'),  -- id 17
('Alice',    'Serra',      '1998-07-14', 'LISBON',        'RESEARCHER',      2800.00, 'F'),  -- id 18
('Daniele',  'Vitale',     '1987-03-30', 'NEW YORK',      'RESEARCHER',      1750.00, 'M'),  -- id 19
('Monica',   'Caputo',     '1998-11-09', 'RACCOON VILLE', 'RESEARCHER',      3000.00, 'F');  -- id 20

-- Verifico tutte le righe dalla tabella
SELECT * FROM people ORDER BY id;

-- Controllo i totali per ruolo: attendo 9 RESEARCHER e 8 FOREST GUARDIAN
SELECT role, COUNT(*) AS conteggio FROM people GROUP BY role ORDER BY role;

-- Controllo anche la distribuzione per città
SELECT city, COUNT(*) AS conteggio FROM people GROUP BY city ORDER BY city;
