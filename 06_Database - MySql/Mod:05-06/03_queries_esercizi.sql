-- Query per l'esercizio sulle auto e proprietari
-- Mod 5-06
--
-- Prerequisiti: eseguire prima 01_create_auto_tables.sql e 02_insert_sample_data.sql

USE ex00;


-- ============================================================================
-- QUERY 1
-- Nome e cognome di tutti i proprietari di auto, combinati con modello,
-- targa e prezzo delle auto da loro possedute
-- ============================================================================
-- Uso INNER JOIN per collegare le tabelle proprietari e auto tramite
-- la foreign key proprietario_id.
-- Ogni riga del risultato mostra un proprietario con una delle sue auto.

SELECT
    p.nome,
    p.cognome,
    a.modello,
    a.targa,
    a.prezzo
FROM proprietari p
INNER JOIN auto a ON p.id = a.proprietario_id;

-- Risultato atteso (12 righe, una per ogni auto):
--   Mario       Rossi     Fiat 500           AB123CD   12000.00
--   Mario       Rossi     Alfa Romeo Giulia  RM456EF   28000.00
--   Laura       Bianchi   Volkswagen Golf    RM789GH   22000.00
--   Giuseppe    Verdi     BMW Serie 3        XY123AB   35000.00
--   Giuseppe    Verdi     Audi A4            RM234IL   32000.00
--   Anna        Romano    Mercedes Classe A  ZZ999WW   30000.00
--   Francesco   Ferrari   Toyota Yaris       PO456QR   15000.00
--   Francesco   Ferrari   Ford Focus         RM567ST   18000.00
--   Giulia      Conti     Renault Clio       UV890XY   13000.00
--   Alessandro  Greco     Peugeot 208        RM123UV   16000.00
--   Chiara      De Luca   Opel Corsa         WX345YZ   14000.00
--   Chiara      De Luca   Nissan Qashqai     RM678WX   25000.00


-- ============================================================================
-- QUERY 2
-- Riprendere la query precedente, ma visualizzare solo i modelli la cui
-- targa comprenda la sequenza di lettere "RM" (Roma)
-- ============================================================================
-- Aggiungo un filtro WHERE con LIKE '%RM%' per cercare la sequenza "RM"
-- in qualsiasi posizione della targa.
-- Il pattern %RM% significa: qualsiasi carattere + RM + qualsiasi carattere

SELECT
    p.nome,
    p.cognome,
    a.modello,
    a.targa,
    a.prezzo
FROM proprietari p
INNER JOIN auto a ON p.id = a.proprietario_id
WHERE a.targa LIKE '%RM%';

-- Risultato atteso (6 righe, solo auto con targa contenente "RM"):
--   Mario       Rossi     Alfa Romeo Giulia  RM456EF   28000.00
--   Laura       Bianchi   Volkswagen Golf    RM789GH   22000.00
--   Giuseppe    Verdi     Audi A4            RM234IL   32000.00
--   Francesco   Ferrari   Ford Focus         RM567ST   18000.00
--   Alessandro  Greco     Peugeot 208        RM123UV   16000.00
--   Chiara      De Luca   Nissan Qashqai     RM678WX   25000.00


-- ============================================================================
-- QUERY 3
-- Riprendere la query al punto uno, ma visualizzare i risultati solo
-- per gli automobilisti di Lisbona
-- ============================================================================
-- Riparto dalla query 1 e aggiungo un filtro WHERE sulla città.
-- Filtro solo i proprietari che risiedono a LISBON.

SELECT
    p.nome,
    p.cognome,
    a.modello,
    a.targa,
    a.prezzo
FROM proprietari p
INNER JOIN auto a ON p.id = a.proprietario_id
WHERE p.citta = 'LISBON';

-- Risultato atteso (6 righe, solo proprietari di Lisbona):
--   Mario       Rossi     Fiat 500           AB123CD   12000.00
--   Mario       Rossi     Alfa Romeo Giulia  RM456EF   28000.00
--   Laura       Bianchi   Volkswagen Golf    RM789GH   22000.00
--   Francesco   Ferrari   Toyota Yaris       PO456QR   15000.00
--   Francesco   Ferrari   Ford Focus         RM567ST   18000.00
--   Chiara      De Luca   Opel Corsa         WX345YZ   14000.00
--   Chiara      De Luca   Nissan Qashqai     RM678WX   25000.00


-- ============================================================================
-- QUERY 4
-- Raggruppare per città e contare quante auto sono di proprietà
-- degli abitanti di quella città (usare COUNT)
-- ============================================================================
-- Uso GROUP BY sulla città per raggruppare i risultati.
-- COUNT(*) conta tutte le righe (auto) per ogni gruppo (città).
-- ORDER BY per mostrare i risultati in ordine alfabetico per città.

SELECT
    p.citta,
    COUNT(*) AS numero_auto
FROM proprietari p
INNER JOIN auto a ON p.id = a.proprietario_id
GROUP BY p.citta
ORDER BY p.citta;

-- Risultato atteso (3 righe, una per città):
--   citta          numero_auto
--   LISBON         6
--   NEW YORK       3
--   RACCOON VILLE  2
