<div align="center">

# 08 — FullStack JS + Angular

Applicazioni full-stack con backend **Node.js / Express.js** + **SQLite** e frontend **Angular 21**.

</div>

---

## Architettura comune

```
progetto/
├── backend/
│   ├── api.js          # Express.js REST API
│   ├── *.db            # Database SQLite (better-sqlite3)
│   └── package.json
└── frontend/
    └── src/app/
        ├── model/      # Interfacce TypeScript
        ├── *-service   # HttpClient service (inject pattern)
        └── form-*/     # Componenti form con ngModel
```

---

## Progetti

### Carroponte — Gestione Spettacoli

| | |
|---|---|
| **Backend** | Express.js + better-sqlite3 + SQLite |
| **Frontend** | Angular 21 + HttpClient |
| **Entità** | `Show` (title, description, date) |
| **API** | `GET /carroponte/api/shows`, `GET /carroponte/api/shows/:id`, `POST /carroponte/api/shows` |
| **Dettagli** | [README](carroponte/README.md) |

---

### Sadder — Gestione Persone su Canvas

| | |
|---|---|
| **Backend** | Express.js + better-sqlite3 + SQLite |
| **Frontend** | Angular 21 + HttpClient |
| **Entità** | `Person` (name, surname, birthdate, x, y) |
| **API** | `GET /sadder/api/people`, `POST /sadder/api/people` |
| **Dettagli** | [README](sadder/README.md) |

---

## Stack Tecnologico

| Layer | Tecnologie |
|-------|------------|
| **Backend** | Node.js, Express.js, better-sqlite3 |
| **Database** | SQLite 3 |
| **Frontend** | Angular 21, TypeScript 5.9, HttpClient |
| **Pattern** | Dependency Injection (`inject()`), Service, Observable/subscribe |
| **Binding** | Two-way (`[(ngModel)]`), FormsModule |

## Concetti chiave

- **HttpClient**: chiamate HTTP dal frontend verso il backend Express
- **inject()**: pattern Angular per la Dependency Injection senza costruttore
- **Observable / subscribe**: gestione asincrona delle risposte HTTP
- **Spread operator** (`...`): costruzione oggetti risposta nel backend
- **better-sqlite3**: accesso sincrono a SQLite nel backend Node.js
