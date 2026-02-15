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

### FoodDelivery — Consegne a Domicilio
Servizio di food delivery con assegnazione automatica rider per vicinanza geografica.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MapStruct 1.6.3, Lombok, MySQL |
| **Features** | Assegnazione rider per distanza euclidea, feedback consegne, MD5 auth |
| **Dettagli** | [README](fooddelivery/README.md) |

### JavaEat v01 — Food Delivery Platform
Piattaforma food delivery con gestione citta, ristoranti, clienti, rider e consegne.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | DTO Pattern manuale, MD5 auth, capacity management, auto-assign rider |
| **Dettagli** | [README](javaeat_v01/README.md) |

### JavaEat v02 — Food Delivery Platform (Refactored)
Evoluzione di JavaEat v01 con relazioni Many-to-Many e analytics.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | Dish-Delivery M:N, rider status/posizione, endpoint analytics, service layer, manual DTO mapper |
| **Dettagli** | [README](javaeat_v02/README.md) |

### Product — Catalogo Prodotti e Recensioni
Catalogo prodotti con sistema di recensioni e valutazioni 1-5 stelle.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, Validation, MySQL |
| **Features** | Review 1-5 stelle, validazione duplicati, data.sql con 50 prodotti |
| **Dettagli** | [README](product/README.md) |

### WebClinic — Gestionale Clinica Medica
Gestione pazienti con validazione Codice Fiscale italiano.

| | |
|---|---|
| **Stack** | Spring Boot 4.0.2, JPA, MySQL |
| **Features** | Validazione Codice Fiscale, CommonValidator Singleton, DTO Pattern |
| **Dettagli** | [README](webclinic/README.md) |

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
