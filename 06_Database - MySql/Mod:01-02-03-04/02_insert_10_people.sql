-- Inserimento delle prime 10 persone.
--
-- Distribuzione per città:
--   RACCOON VILLE : id 1, 4, 7, 10   (4 persone)
--   LISBON        : id 2, 5, 8       (3 persone)
--   NEW YORK      : id 3, 6, 9       (3 persone)
--
-- Minorenni (età < 18 anni al 2026-02-04):
--   id 6  -> Aria  Colombo  (nata  2010-06-20) -> 15 anni
--   id 7  -> Ethan Ricci    (nato  2009-12-05) -> 16 anni
--   id 8  -> Luna  Gallo    (nata  2011-02-18) -> 14 anni
--
-- Tutti i ruoli sono impostati a EXPLORER (default);
-- verranno aggiornati nel file 03_update_roles.sql

USE ex00;

-- Inserisco tutte e 10 le persone in una singola istruzione INSERT
-- (più efficiente che fare 10 INSERT separati).
-- Metto tre minorenni (id 6, 7, 8) con stipendio 0 perché non lavorano;
-- li eliminerò poi nel file 04.
-- Il ruolo lo lascio EXPLORER per tutti: lo cambierò nel file 03.
INSERT INTO people (firstname, lastname, dob, city, role, salary, gender) VALUES
('Elena',   'Rossi',     '1990-03-15', 'RACCOON VILLE', 'EXPLORER', 2200.00, 'F'),  -- id 1
('Marco',   'Bianchi',   '1985-07-22', 'LISBON',        'EXPLORER', 2500.00, 'M'),  -- id 2
('Sara',    'Verdi',     '1992-11-08', 'NEW YORK',      'EXPLORER', 2100.00, 'F'),  -- id 3
('Luca',    'Ferrari',   '1988-05-30', 'RACCOON VILLE', 'EXPLORER', 2400.00, 'M'),  -- id 4
('Giulia',  'Moretti',   '1995-01-14', 'LISBON',        'EXPLORER', 1900.00, 'F'),  -- id 5
('Aria',    'Colombo',   '2010-06-20', 'NEW YORK',      'EXPLORER', 0.00,    'F'),  -- id 6  ** MINORE **
('Ethan',   'Ricci',     '2009-12-05', 'RACCOON VILLE', 'EXPLORER', 0.00,    'M'),  -- id 7  ** MINORE **
('Luna',    'Gallo',     '2011-02-18', 'LISBON',        'EXPLORER', 0.00,    'F'),  -- id 8  ** MINORE **
('Andrea',  'Esposito',  '1987-09-11', 'NEW YORK',      'EXPLORER', 2300.00, 'M'),  -- id 9
('Chiara',  'Barbieri',  '1993-04-27', 'RACCOON VILLE', 'EXPLORER', 2600.00, 'F');  -- id 10

-- Controllo che tutte e 10 le righe siano state inserite correttamente
SELECT * FROM people ORDER BY id;
