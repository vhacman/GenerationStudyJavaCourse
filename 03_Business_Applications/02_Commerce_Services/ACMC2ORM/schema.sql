-- Schema per Book e Review
-- Book è entità padre, Review è entità figlia

DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS book;

CREATE TABLE book (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    genre TEXT,
    year INTEGER
);

CREATE TABLE review (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    author TEXT NOT NULL,
    text TEXT,
    score INTEGER NOT NULL,
    bookid INTEGER NOT NULL,
    FOREIGN KEY (bookid) REFERENCES book(id)
);

-- Dati di test per il test: Il Signore degli Anelli con 4 review
INSERT INTO book (title, author, genre, year) VALUES
('Il Signore degli Anelli', 'J.R.R. Tolkien', 'Fantasy', 1954);

INSERT INTO review (author, text, score, bookid) VALUES
('Adriano', 'Capolavoro assoluto!', 10, 1),
('Maria', 'Fantastico libro', 10, 1),
('Giuseppe', 'Bellissimo', 10, 1),
('Laura', 'Meraviglioso', 10, 1);
