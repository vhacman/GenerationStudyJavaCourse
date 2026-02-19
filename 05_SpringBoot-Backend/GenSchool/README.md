<div align="center">

# GenSchool — Sistema Gestione Scolastico

**Spring Boot 4.0.2 | JPA | MySQL**

</div>


Applicazione Spring Boot per la gestione di lezioni scolastiche.

## 🛠️ Tecnologie

| Tecnologia | Descrizione |
|------------|------------|
| Spring Boot 4.0.2 | Framework principale |
| Spring Data JPA | Persistenza dati |
| MySQL | Database relazionale |
| Maven | Build automation |
| Thymeleaf | Template engine |

## 📁 Struttura

```
GenSchool/
├── src/main/java/com/generation/lesson/
│   ├── GenSchoolApplication.java         # Entry point
│   ├── ServletInitializer.java
│   ├── api/
│   │   └── LessonAPI.java               # REST endpoints
│   └── model/
│       ├── entities/
│       │   └── Lesson.java              # Entity
│       └── repository/
│           └── LessonRepository.java    # JPA Repository
└── src/main/resources/
    └── application.properties
```

## 🎯 Funzionalità

- Gestione completa delle lezioni (CRUD)
- API RESTful per integrazione frontend
- Persistenza su database MySQL

## 🚀 Avvio

```bash
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

## 🔌 API Endpoints

| Metodo | Endpoint | Descrizione |
|--------|----------|------------|
| GET | `/api/lessons` | Lista tutte le lezioni |
| GET | `/api/lessons/{id}` | Ottieni lezione per ID |
| POST | `/api/lessons` | Crea nuova lezione |
| PUT | `/api/lessons/{id}` | Aggiorna lezione |
| DELETE | `/api/lessons/{id}` | Elimina lezione |

## ⚙️ Configurazione Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/genschool_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
