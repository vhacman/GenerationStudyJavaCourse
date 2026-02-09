<div align="center">

# Spring Boot & Frontend

Progetti sviluppati con Spring Boot, Spring Data JPA, Thymeleaf, MySQL e tecnologie frontend.

</div>

---

## Progetti Spring Boot

### Emergency — Sistema Pronto Soccorso
Gestione emergenze ospedaliere e code pazienti in tempo reale.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| **Features** | Dashboard operatore, REST API, gestione code |
| **Dettagli** | [README](Emergency/README.md) |

### Dinner — Gestione Ristorante
Sistema di gestione ordini con tracciamento stati e costi.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| **Features** | CRUD ordini, stati ordine, mance |
| **Dettagli** | [README](Dinner/README.md) |

### Ticket — Sistema Segnalazioni
Gestione ticket e segnalazioni con timestamp e assegnazione per area.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| **Features** | Apertura/chiusura ticket, filtri per stanza/stato |
| **Dettagli** | [README](Ticket/README.md) |

### Vault — Membership Vault-Tec
Sistema di gestione richieste di membership ispirato all'universo Fallout.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | Richieste ammissione, valutazione candidati, assegnazione Vault |
| **Dettagli** | [README](vault/README.md) |

### GenSchool — Gestione Scolastica
Applicazione per la gestione di lezioni scolastiche.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | CRUD lezioni, REST API |
| **Dettagli** | [README](GenSchool/README.md) |

### PC Configurator
Configurazione e gestione di PC personalizzati con validazione.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | Configurazioni PC, validazione, REST API |
| **Dettagli** | [README](pcconfigurator/README.md) |

### Hotel Booking — Prenotazioni Alberghiere
Sistema completo di prenotazioni con check-in/check-out.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| **Features** | Gestione camere, prenotazioni, tariffe |
| **Dettagli** | [README](hotelboolking/README.md) |

### Sanction — Sistema Gestione Sanzioni
Sistema per la gestione di sanzioni amministrative con tracciamento cittadini e pagamenti.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | Relazioni OneToMany/ManyToOne, DTO Pattern, REST API |
| **Dettagli** | [README](sanction/README.md) |

---

## Progetti Frontend

### FrontEnd — HTML/CSS/JavaScript
Progetti web sviluppati con tecnologie frontend vanilla.

| | |
|---|---|
| **Stack** | HTML5, CSS3, JavaScript ES6+ |
| **Features** | Gioco carte italiano (MVC), PC Configurator web, esercizi HTML |
| **Dettagli** | [README](FrontEnd%20-%20FullStack/README.md) |

---

## Concetti Appresi

| Area | Dettagli |
|------|----------|
| Spring Boot | Configurazione, auto-configuration, embedded server |
| Spring Data JPA | Repository automatici, query derivate, Hibernate ORM |
| MVC Pattern | `@Controller` per views HTML, `@RestController` per JSON |
| REST API | CRUD endpoints, `@RequestMapping`, `@PathVariable` |
| Thymeleaf | Template engine, binding dati, form processing |
| MySQL | Integrazione JDBC, `ddl-auto`, dialetti |
| Dependency Injection | IoC Container di Spring, `@Autowired` |

---

## Quick Start

```bash
# 1. Creare il database MySQL
mysql -u root -p -e "CREATE DATABASE nome_db;"

# 2. Configurare application.properties
# spring.datasource.url=jdbc:mysql://localhost:3306/nome_db
# spring.jpa.hibernate.ddl-auto=update

# 3. Avviare l'applicazione
cd nome_progetto/
mvn spring-boot:run

# 4. Accesso: http://localhost:8080
```

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna al README principale](../README.md)

</div>
