# 🎫 Ticket - Sistema di Gestione Segnalazioni

Sistema di gestione ticket e segnalazioni sviluppato con Spring Boot 4.0.2, Spring Data JPA, Thymeleaf e MySQL.

## 📋 Descrizione

**Ticket** è un'applicazione web per la gestione di ticket/segnalazioni, ideale per:
- Help desk aziendali
- Gestione manutenzione (facility management)
- Tracciamento problemi e richieste
- Supporto tecnico IT

Permette di aprire, tracciare e chiudere ticket con timestamp precisi e assegnazione per stanza/area.

## 🛠️ Tecnologie Utilizzate

| Tecnologia | Versione | Utilizzo |
|------------|----------|----------|
| Spring Boot | 4.0.2 | Framework principale |
| Spring Data JPA | 4.0.2 | Persistenza dati |
| Thymeleaf | 4.0.2 | Template engine per views |
| MySQL | 8.x | Database relazionale |
| Java | 21 | Linguaggio di programmazione |
| Maven | - | Build automation |

## 📁 Struttura del Progetto

```
Ticket/
├── src/main/java/com/generation/ticket/
│   ├── TicketApplication.java           # Entry point Spring Boot
│   ├── ServletInitializer.java          # Configurazione deployment WAR
│   ├── controller/
│   │   └── GuestController.java         # Controller MVC per pagine HTML
│   ├── api/
│   │   └── TicketAPI.java               # REST API Controller
│   └── model/
│       ├── entities/
│       │   └── Ticket.java              # Entità JPA
│       └── repository/
│           └── TicketRepository.java    # Repository Spring Data
├── src/main/resources/
│   ├── application.properties           # Configurazione database
│   └── templates/                       # Template Thymeleaf
└── pom.xml                              # Dipendenze Maven
```

## 💾 Modello Dati

### Entità: Ticket

| Campo | Tipo | Descrizione |
|-------|------|-------------|
| `id` | int | Identificativo univoco (auto-incrementale) |
| `room` | String | Stanza/area di riferimento |
| `opening` | String | Descrizione apertura ticket |
| `closure` | String | Descrizione chiusura ticket |
| `openOn` | LocalDateTime | Data/ora apertura ticket |
| `closedOn` | LocalDateTime | Data/ora chiusura ticket |
| `status` | String | Stato ticket (aperto, in lavorazione, chiuso) |

### Stati Ticket

- **Aperto** - Ticket appena creato, in attesa di presa in carico
- **In Lavorazione** - Ticket assegnato, intervento in corso
- **Chiuso** - Ticket risolto e chiuso

## 🔌 API Endpoints

### Controller MVC (GuestController)
- `GET /ticketservice/portal/newticket` - Pagina creazione nuovo ticket
- `GET /ticketservice/portal/tickets` - Lista tutti i ticket

### REST API (TicketAPI)
- `GET /ticketservice/api/tickets` - Lista tutti i ticket (JSON)
- `POST /ticketservice/api/tickets` - Crea nuovo ticket
- `GET /ticketservice/api/tickets/{id}` - Dettaglio ticket
- `PUT /ticketservice/api/tickets/{id}` - Aggiorna ticket
- `DELETE /ticketservice/api/tickets/{id}` - Elimina ticket
- `GET /ticketservice/api/tickets/room/{room}` - Filtra per stanza
- `GET /ticketservice/api/tickets/status/{status}` - Filtra per stato

## ⚙️ Configurazione

### Database MySQL

Configurare `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticket_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Creazione Database

```sql
CREATE DATABASE ticket_db;
USE ticket_db;
```

Le tabelle vengono create automaticamente da Hibernate al primo avvio.

## 🚀 Esecuzione

### Con Maven
```bash
mvn spring-boot:run
```

### Con IDE
Eseguire la classe `TicketApplication.java`

L'applicazione sarà disponibile su: `http://localhost:8080`

## 📝 Pattern Implementati

- **MVC Pattern** - Separazione Model-View-Controller
- **Repository Pattern** - Astrazione dell'accesso ai dati con Spring Data JPA
- **RESTful API Design** - API REST seguendo convenzioni HTTP
- **Dependency Injection** - IoC Container di Spring
- **ORM (Object-Relational Mapping)** - Hibernate JPA

## 🎯 Concetti Chiave

- **@Entity** - Annotazione JPA per definire entità persistenti
- **@Id & @GeneratedValue** - Chiave primaria auto-incrementale
- **LocalDateTime** - Gestione timestamp con Java Time API
- **@Controller vs @RestController** - Views HTML vs JSON
- **Spring Data JPA** - Repository senza codice boilerplate

## 💡 Casi d'Uso

1. **Help Desk IT**
   - Apertura ticket per problemi hardware/software
   - Tracciamento risoluzione con timestamp
   - Assegnazione per ufficio/postazione

2. **Facility Management**
   - Segnalazioni manutenzione (es. "Ufficio 201 - Lampada guasta")
   - Storico interventi per stanza
   - Statistiche per area

3. **Customer Support**
   - Ticket di assistenza clienti
   - Tracciamento tempi di risposta
   - Gestione priorità e stati

## 🔗 Collegamenti

- [Torna a 05_Spring](../README.md)
- [README Principale](../../README.md)
- [Dinner System](../Dinner/README.md)
- [Emergency System](../Emergency/README.md)

---

Sviluppato durante il corso Generation Italy - Full Stack Java Developer
