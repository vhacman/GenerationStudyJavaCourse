<div align="center">

# WebClinic — Gestionale Clinica Medica

**Spring Boot 4.0.2 | JPA | MySQL**

</div>

REST API per la gestione di pazienti in una clinica medica, con validazione del Codice Fiscale italiano e dati anagrafici.

## Tecnologie

| Tecnologia | Descrizione |
|------------|-------------|
| Spring Boot 4.0.2 | Framework principale |
| Spring Data JPA | Persistenza dati con Hibernate |
| MySQL | Database relazionale |
| Maven | Build automation |

## Modello Dati

| Entita | Campi | Validazione |
|--------|-------|-------------|
| **Patient** | firstName, lastName, ssn, gender, address, city, email, dob, history | Codice Fiscale, email RFC 5322, data non futura |

## Validazione

Il progetto include un **CommonValidator** (Singleton) con:

- **Codice Fiscale**: regex per il formato italiano (`[A-Z]{6}[0-9LMNPQRSTUV]{2}...`)
- **Email**: validazione RFC 5322
- **Campi obbligatori**: nome, cognome, indirizzo, citta, genere (M/F/O)
- **Data di nascita**: obbligatoria, non puo essere futura

## API Endpoints

### Patients — `/webclinic/api/patients`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/{id}` | Trova paziente per ID |

## Architettura

```
API (RestController)
    ↓
DTO + Mapper (toDTO / toFullDTO)
    ↓
Repository (JPA)
    ↓
Entity + Validable Interface
    ↓
CommonValidator (Singleton)
```

## Struttura

```
src/main/java/com/generation/webclinic/
├── api/
│   ├── PatientAPI.java
│   └── dto/
│       ├── PatientDTO.java
│       └── PatientDTOMapper.java
├── model/
│   ├── entities/
│   │   ├── Patient.java
│   │   └── Validable.java
│   └── repository/
│       └── PatientRepository.java
└── validator/
    └── CommonValidator.java
```

## Avvio

```bash
mysql -u root -p -e "CREATE DATABASE webclinic;"
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
