# GenSchool - Sistema Gestione Lezioni

Applicazione Spring Boot per la gestione di lezioni scolastiche.

## Tecnologie Utilizzate

- **Spring Boot** - Framework principale
- **Spring Data JPA** - Persistenza dati
- **MySQL** - Database
- **Maven** - Build tool

## Struttura del Progetto

```
GenSchool/
├── src/main/java/com/generation/lesson/
│   ├── GenSchoolApplication.java         # Main application
│   ├── ServletInitializer.java
│   ├── api/
│   │   └── LessonAPI.java               # REST endpoints per lezioni
│   └── model/
│       ├── entities/
│       │   └── Lesson.java              # Entity Lezione
│       └── repository/
│           └── LessonRepository.java    # Repository JPA
└── src/main/resources/
    └── application.properties           # Configurazione database
```

## Funzionalità Principali

- Gestione completa delle lezioni (CRUD)
- API RESTful per integrazione frontend
- Persistenza su database MySQL

## Configurazione

Configurare il database in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/genschool_db
spring.datasource.username=root
spring.datasource.password=your_password
```

## Avvio dell'Applicazione

```bash
mvn spring-boot:run
```

L'applicazione sarà disponibile su `http://localhost:8080`

## API Endpoints

- `GET /api/lessons` - Ottieni tutte le lezioni
- `GET /api/lessons/{id}` - Ottieni lezione per ID
- `POST /api/lessons` - Crea nuova lezione
- `PUT /api/lessons/{id}` - Aggiorna lezione
- `DELETE /api/lessons/{id}` - Elimina lezione
