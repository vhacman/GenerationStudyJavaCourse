<div align="center">

# Dinner — Sistema di Gestione Ristorante

**Spring Boot 4.0.2 | JPA | Thymeleaf | MySQL**

</div>


Sistema di gestione ordini per ristorante sviluppato con Spring Boot 4.0.2, Spring Data JPA, Thymeleaf e MySQL.

## 📋 Descrizione

**Dinner** è un'applicazione web per la gestione degli ordini di un ristorante, che permette di:
- Inserire nuovi ordini tramite interfaccia web
- Gestire lo stato degli ordini (in preparazione, servito, pagato)
- Tracciare costi e mance
- Esporre API REST per integrazione con sistemi esterni

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
Dinner/
├── src/main/java/com/generation/dinner/
│   ├── DinnerApplication.java           # Entry point Spring Boot
│   ├── ServletInitializer.java          # Configurazione deployment WAR
│   ├── controller/
│   │   └── GuestController.java         # Controller MVC per pagine HTML
│   ├── api/
│   │   └── DinnerAPI.java               # REST API Controller
│   └── model/
│       ├── entities/
│       │   └── Dinner.java              # Entità JPA
│       └── repository/
│           └── DinnerRepository.java    # Repository Spring Data
├── src/main/resources/
│   ├── application.properties           # Configurazione database
│   └── templates/                       # Template Thymeleaf
│       └── newDinner.html
└── pom.xml                              # Dipendenze Maven
```

## 💾 Modello Dati

### Entità: Dinner

| Campo | Tipo | Descrizione |
|-------|------|-------------|
| `id` | int | Identificativo univoco (auto-incrementale) |
| `description` | String | Descrizione dell'ordine |
| `cost` | int | Costo totale in centesimi |
| `status` | String | Stato ordine (nuovo, in preparazione, servito, pagato) |
| `tip` | int | Mancia in centesimi |

## 🔌 API Endpoints

### Controller MVC (GuestController)
- `GET /dinnerservice/portal/newdinner` - Pagina inserimento nuovo ordine

### REST API (DinnerAPI)
- `GET /dinnerservice/api/dinners` - Lista tutti gli ordini
- `POST /dinnerservice/api/dinners` - Crea nuovo ordine
- `GET /dinnerservice/api/dinners/{id}` - Dettaglio ordine
- `PUT /dinnerservice/api/dinners/{id}` - Aggiorna ordine
- `DELETE /dinnerservice/api/dinners/{id}` - Elimina ordine

## ⚙️ Configurazione

### Database MySQL

Configurare `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dinner_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Creazione Database

```sql
CREATE DATABASE dinner_db;
USE dinner_db;
```

Le tabelle vengono create automaticamente da Hibernate al primo avvio.

## 🚀 Esecuzione

### Con Maven
```bash
mvn spring-boot:run
```

### Con IDE
Eseguire la classe `DinnerApplication.java`

L'applicazione sarà disponibile su: `http://localhost:8080`

## 📝 Pattern Implementati

- **MVC Pattern** - Separazione Model-View-Controller
- **Repository Pattern** - Astrazione dell'accesso ai dati con Spring Data JPA
- **RESTful API Design** - API REST seguendo convenzioni HTTP
- **Dependency Injection** - IoC Container di Spring
- **ORM (Object-Relational Mapping)** - Hibernate JPA per mappatura oggetti-database

## 🎯 Concetti Chiave

- **@Entity** - Annotazione JPA per definire entità persistenti
- **@Controller** - Restituisce views HTML (Thymeleaf)
- **@RestController** - Restituisce dati JSON per API
- **@Repository** - Interfaccia Spring Data per operazioni CRUD
- **@RequestMapping** - Mappatura URL a metodi controller

## 🔗 Collegamenti

- [Torna a 05_Spring](../README.md)
- [README Principale](../../README.md)
- [Emergency System](../Emergency/README.md)

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
