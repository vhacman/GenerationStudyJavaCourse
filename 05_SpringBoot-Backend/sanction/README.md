<div align="center">

# Sanction — Sistema di Gestione Sanzioni

Sistema web per la gestione di sanzioni amministrative con tracciamento cittadini e pagamenti.

</div>

---

## Panoramica

| Aspetto | Dettaglio |
|---------|-----------|
| **Stack** | Spring Boot 4.0.2, Spring Data JPA, MySQL |
| **Architettura** | REST API, Repository Pattern, DTO Pattern |
| **Database** | MySQL 8.x con relazioni OneToMany / ManyToOne |
| **Pattern** | MVC, Dependency Injection, Entity Mapping |

---

## Struttura del Progetto

```
sanction/
├── src/main/java/com/generation/sanctionweb/
│   ├── SanctionApplication.java          # Classe principale Spring Boot
│   ├── ServletInitializer.java           # Configurazione deployment
│   ├── api/
│   │   └── SanctionAPI.java              # REST Controller
│   ├── dto/
│   │   ├── SanctionDTO.java              # Data Transfer Object
│   │   └── SanctionMapperDTO.java        # Mapper Entity ↔ DTO
│   └── model/
│       ├── entities/
│       │   ├── Citizen.java              # Entità Cittadino
│       │   └── Sanction.java              # Entità Sanzione
│       └── repository/
│           ├── CitizenRepository.java     # Repository Cittadini
│           └── SanctionRepository.java    # Repository Sanzioni
```

---

## Entità

### Citizen
| Campo | Tipo | Descrizione |
|-------|------|-------------|
| `id` | int | Identificativo univoco |
| `firstName` | String | Nome del cittadino |
| `lastName` | String | Cognome del cittadino |
| `SSN` | String | Codice fiscale |
| `sanctions` | List\<Sanction> | Relazione OneToMany |

### Sanction
| Campo | Tipo | Descrizione |
|-------|------|-------------|
| `id` | int | Identificativo univoco |
| `firstName` | String | Nome sanzionato |
| `lastName` | String | Cognome sanzionato |
| `date` | LocalDate | Data emissione |
| `paidOn` | LocalDate | Data pagamento (nullable) |
| `status` | String | Stato (PAID/UNPAID) |
| `reason` | String | Motivo sanzione |
| `price` | int | Importo in euro |
| `citizen` | Citizen | Relazione ManyToOne |

---

## API Endpoints

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/sanction/api/sanctions` | Recupera tutte le sanzioni |

---

## Configurazione

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/sanction_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Dipendenze Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

---

## Concetti Chiave

- **Entity Mapping**: JPA con annotazioni `@Entity`, `@Id`, `@GeneratedValue`
- **Relazioni**: `@OneToMany`, `@ManyToOne`, `FetchType.EAGER`
- **DTO Pattern**: Separazione tra Entity e dati esposti via API
- **Stream API**: Elaborazione declarativa con `map()` e `collect()`
- **Repository**: Spring Data JPA con metodi derivati

---

<div align="center">

[Torna al README Spring Boot](../README.md) | [README Principale](../../README.md)

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

</div>
