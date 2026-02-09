# 🏨 Hotel Booking - Sistema Prenotazioni Alberghiere

Progetto Spring Boot per la gestione delle prenotazioni alberghiere.

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
hotelboolking/
├── src/main/java/com/generation/hotelboolking/
│   ├── HotelbookingApplication.java    # Entry point
│   ├── controller/
│   ├── api/
│   └── model/
│       ├── entities/
│       └── repository/
└── src/main/resources/
    ├── application.properties
    └── templates/
```

## 🎯 Funzionalità

- Gestione camere
- Sistema prenotazioni
- Check-in/Check-out
- Tariffe e prezzi
- API RESTful
- Interfaccia web Thymeleaf

## 🚀 Avvio

```bash
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

## ⚙️ Configurazione Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
