<div align="center">

# Emergency — Sistema Gestione Pronto Soccorso

**Spring Boot 4.0.2 | JPA | Thymeleaf | MySQL**

</div>


Applicazione web Spring Boot per la gestione delle emergenze ospedaliere e delle code al pronto soccorso.

## 📋 Descrizione

Sistema di gestione che permette agli operatori di monitorare ospedali e code di pazienti in tempo reale attraverso un'interfaccia web e API REST.

## 🛠️ Tecnologie Utilizzate

- **Spring Boot 4.0.2** - Framework principale
- **Spring Data JPA** - Persistenza dati
- **Hibernate** - ORM
- **Thymeleaf** - Template engine per le views
- **MySQL** - Database
- **Maven** - Build tool
- **Java 21** - Linguaggio di programmazione

## 📁 Struttura del Progetto

```
emergency/
├── src/main/java/com/generation/emergency/
│   ├── EmergencyApplication.java          # Entry point Spring Boot
│   ├── ServletInitializer.java           # Configurazione servlet container
│   ├── model/
│   │   ├── entities/
│   │   │   └── Hospital.java             # Entità JPA Hospital
│   │   └── repository/
│   │       └── HospitalRepository.java   # Repository Spring Data
│   ├── controller/
│   │   └── OperatorController.java       # Controller MVC per operatore
│   └── api/
│       ├── HospitalAPI.java              # REST API per ospedali
│       └── QuoteAPI.java                 # REST API per quotazioni
├── src/main/resources/
│   ├── application.properties            # Configurazione database
│   └── templates/
│       └── operatorhome.html            # View Thymeleaf operatore
└── pom.xml                               # Dipendenze Maven
```

## 📊 Modello Dati

### Hospital Entity

```java
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;          // Chiave primaria auto-increment
    private String name;     // Nome ospedale
    private String address;  // Indirizzo completo
    private String city;     // Città
    private int queue;       // Numero pazienti in coda
}
```

## 🎯 Funzionalità

### 1. Interfaccia Operatore (MVC)
- **URL**: `/emergency/operator`
- **Controller**: `OperatorController`
- **View**: Thymeleaf template (`operatorhome.html`)
- Visualizzazione dashboard operatore

### 2. REST API

#### Hospital API
- **Endpoint**: `/api/hospitals`
- Gestione CRUD ospedali
- Ritorna dati in formato JSON

#### Quote API
- **Endpoint**: `/api/quotes`
- Gestione quotazioni/statistiche
- Integrazione con sistemi esterni

## 🔧 Pattern e Architetture

- **MVC Pattern**: Separazione Controller-View-Model
- **Repository Pattern**: Astrazione accesso dati con Spring Data JPA
- **Dependency Injection**: IoC Container di Spring
- **RESTful API**: Endpoint standard REST

## 🚀 Setup e Configurazione

### 1. Prerequisiti
- JDK 21+
- Maven 3.6+
- MySQL 8.0+
- IDE (Eclipse, IntelliJ IDEA, VS Code)

### 2. Configurazione Database

Creare il database MySQL:
```sql
CREATE DATABASE emergency_db;
USE emergency_db;
```

### 3. Configurare application.properties

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/emergency_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Thymeleaf
spring.thymeleaf.cache=false
```

### 4. Eseguire l'applicazione

**Opzione 1 - Maven:**
```bash
cd emergency/
mvn clean install
mvn spring-boot:run
```

**Opzione 2 - IDE:**
- Eseguire `EmergencyApplication.java` come Java Application

### 5. Accesso

- **Interfaccia Operatore**: http://localhost:8080/emergency/operator
- **API Hospitals**: http://localhost:8080/api/hospitals
- **API Quotes**: http://localhost:8080/api/quotes

## 📝 Note Tecniche

### @Controller vs @RestController

- `@Controller`: Restituisce **views HTML** (Thymeleaf)
- `@RestController`: Restituisce **dati JSON/XML** (REST API)

### Annotazioni JPA

- `@Entity`: Mappa la classe a una tabella database
- `@Id`: Definisce la chiave primaria
- `@GeneratedValue`: Auto-increment per l'ID
- `GenerationType.IDENTITY`: Strategia MySQL AUTO_INCREMENT

### Spring Data JPA

Repository generato automaticamente da Spring con metodi CRUD:
- `save()` - Inserimento/Aggiornamento
- `findAll()` - Recupero tutti
- `findById()` - Ricerca per ID
- `delete()` - Eliminazione

## 🎓 Concetti Appresi

- ✅ Configurazione Spring Boot
- ✅ Spring Data JPA e Hibernate
- ✅ Entità JPA e mapping database
- ✅ Repository Pattern
- ✅ Controller MVC e REST Controller
- ✅ Thymeleaf Template Engine
- ✅ Dependency Injection con Spring
- ✅ Integrazione MySQL con JDBC

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
