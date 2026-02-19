<div align="center">

# Vault — Sistema di Gestione Membership

**Spring Boot 4.0.2 | JPA | MySQL**

*"VaultTec inc. — Your future in our hands!"*

</div>


Sistema di gestione richieste di membership per Vault-Tec Inc. sviluppato con Spring Boot 4.0.2, Spring Data JPA e MySQL.

## 📋 Descrizione

**Vault** è un'applicazione web ispirata all'universo Fallout per la gestione di richieste di membership ai rifugi atomici (Vault). Il sistema permette di:
- Ricevere e processare richieste di ammissione ai Vault
- Valutare candidati in base a reddito, genere e altri criteri
- Assegnare candidati a specifici Vault
- Tracciare lo stato delle richieste (pending, approved, rejected)

## 🛠️ Tecnologie Utilizzate

| Tecnologia | Versione | Utilizzo |
|------------|----------|----------|
| Spring Boot | 4.0.2 | Framework principale |
| Spring Data JPA | 4.0.2 | Persistenza dati |
| Spring Web MVC | 4.0.2 | Web layer |
| MySQL | 8.x | Database relazionale |
| Java | 21 | Linguaggio di programmazione |
| Maven | - | Build automation |

## 📁 Struttura del Progetto

```
vault/
├── src/main/java/com/generation/vault/
│   ├── VaultApplication.java                # Entry point Spring Boot
│   ├── ServletInitializer.java              # Configurazione deployment WAR
│   ├── controller/
│   │   └── VaultController.java             # Controller MVC
│   ├── api/
│   │   └── MembershipRequestAPI.java        # REST API Controller
│   └── model/
│       ├── entities/
│       │   └── MembershipRequest.java       # Entità JPA
│       └── repository/
│           └── MembershipRequestRepository.java # Repository Spring Data
├── src/main/resources/
│   ├── application.properties               # Configurazione database
│   └── templates/                           # Template Thymeleaf (se presenti)
└── pom.xml                                  # Dipendenze Maven
```

## 💾 Modello Dati

### Entità: MembershipRequest

| Campo | Tipo | Descrizione |
|-------|------|-------------|
| `id` | int | Identificativo univoco (auto-incrementale) |
| `firstName` | String | Nome del candidato |
| `lastName` | String | Cognome del candidato |
| `gender` | String | Genere del candidato |
| `income` | int | Reddito annuale del candidato |
| `status` | String | Stato richiesta (pending, approved, rejected) |
| `vault` | String | Vault assegnato (es. "Vault 101", "Vault 76") |

### Stati Richiesta

- **pending** - Richiesta in attesa di valutazione
- **approved** - Richiesta approvata, candidato ammesso al Vault
- **rejected** - Richiesta respinta
- **waitlist** - Candidato in lista d'attesa

### Vault Disponibili

- **Vault 13** - Il Vault originale (Fallout 1)
- **Vault 76** - Vault di ricostruzione (Fallout 76)
- **Vault 101** - "It's home" (Fallout 3)
- **Vault 111** - Vault criogenico (Fallout 4)

## 🔌 API Endpoints

### Controller MVC (VaultController)
- `GET /vault/portal/newrequest` - Pagina creazione nuova richiesta
- `GET /vault/portal/requests` - Lista tutte le richieste

### REST API (MembershipRequestAPI)
- `GET /vault/api/requests` - Lista tutte le richieste (JSON)
- `POST /vault/api/requests` - Crea nuova richiesta di membership
- `GET /vault/api/requests/{id}` - Dettaglio richiesta specifica
- `PUT /vault/api/requests/{id}` - Aggiorna richiesta
- `DELETE /vault/api/requests/{id}` - Elimina richiesta
- `GET /vault/api/requests/vault/{vaultName}` - Filtra per Vault
- `GET /vault/api/requests/status/{status}` - Filtra per stato
- `GET /vault/api/requests/income/min/{amount}` - Filtra per reddito minimo

## ⚙️ Configurazione

### Database MySQL

Configurare `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vault_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Creazione Database

```sql
CREATE DATABASE vault_db;
USE vault_db;
```

Le tabelle vengono create automaticamente da Hibernate al primo avvio.

## 🚀 Esecuzione

### Con Maven
```bash
mvn spring-boot:run
```

### Con IDE
Eseguire la classe `VaultApplication.java`

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
- **Spring Data JPA Repository** - Query derivate dai nomi dei metodi
- **@RestController** - Controller REST per API JSON
- **@Controller** - Controller MVC per views HTML

## 💡 Logica di Business

### Criteri di Ammissione
Il sistema può implementare logica di business per valutare candidati:

```java
// Esempio di logica di valutazione
if (income >= 50000 && status.equals("pending")) {
    status = "approved";
    vault = assignOptimalVault(gender, income);
} else if (income < 20000) {
    status = "rejected";
} else {
    status = "waitlist";
}
```

### Assegnazione Vault
I candidati possono essere assegnati a Vault specifici in base a:
- Reddito (priorità per Vault di lusso)
- Competenze professionali
- Composizione demografica del Vault
- Disponibilità di posti

## 🎮 Contesto Fallout

Il progetto è un omaggio alla serie di videogiochi Fallout, dove Vault-Tec Corporation costruì rifugi antiatomici sotterranei (Vault) per proteggere la popolazione da una guerra nucleare. Ogni Vault aveva scopi sperimentali nascosti e criteri di selezione dei residenti.

## 🔗 Collegamenti

- [Torna a 05_Spring](../README.md)
- [README Principale](../../README.md)
- [Ticket System](../Ticket/README.md)
- [Dinner System](../Dinner/README.md)

---

<div align="center">

*"War. War never changes."* — Fallout Series

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
