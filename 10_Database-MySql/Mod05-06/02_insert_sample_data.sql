-- Inserimento dati di esempio per proprietari e auto
--
-- Popolo il database con alcuni proprietari di diverse città
-- e le loro auto con targhe varie (alcune di Roma, altre no)

USE ex00;

-- Inserisco i proprietari
INSERT INTO proprietari (nome, cognome, citta) VALUES
    ('Mario',      'Rossi',     'LISBON'),
    ('Laura',      'Bianchi',   'LISBON'),
    ('Giuseppe',   'Verdi',     'NEW YORK'),
    ('Anna',       'Romano',    'RACCOON VILLE'),
    ('Francesco',  'Ferrari',   'LISBON'),
    ('Giulia',     'Conti',     'NEW YORK'),
    ('Alessandro', 'Greco',     'RACCOON VILLE'),
    ('Chiara',     'De Luca',   'LISBON');

-- Inserisco le auto con targhe diverse
-- Alcune targhe contengono "RM" (Roma), altre no
INSERT INTO auto (modello, targa, prezzo, proprietario_id) VALUES
    -- Auto di Mario Rossi (Lisbona)
    ('Fiat 500',         'AB123CD',  12000.00, 1),
    ('Alfa Romeo Giulia','RM456EF',  28000.00, 1),

    -- Auto di Laura Bianchi (Lisbona)
    ('Volkswagen Golf',  'RM789GH',  22000.00, 2),

    -- Auto di Giuseppe Verdi (New York)
    ('BMW Serie 3',      'XY123AB',  35000.00, 3),
    ('Audi A4',          'RM234IL',  32000.00, 3),

    -- Auto di Anna Romano (Raccoon Ville)
    ('Mercedes Classe A','ZZ999WW',  30000.00, 4),

    -- Auto di Francesco Ferrari (Lisbona)
    ('Toyota Yaris',     'PO456QR',  15000.00, 5),
    ('Ford Focus',       'RM567ST',  18000.00, 5),

    -- Auto di Giulia Conti (New York)
    ('Renault Clio',     'UV890XY',  13000.00, 6),

    -- Auto di Alessandro Greco (Raccoon Ville)
    ('Peugeot 208',      'RM123UV',  16000.00, 7),

    -- Auto di Chiara De Luca (Lisbona)
    ('Opel Corsa',       'WX345YZ',  14000.00, 8),
    ('Nissan Qashqai',   'RM678WX',  25000.00, 8);
