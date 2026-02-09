# 🌱 Spring Boot Projects

Progetti sviluppati con Spring Boot, Spring Data JPA e MySQL.

---

## 📋 Progetti

### 🏥 [Emergency](Emergency/README.md)
Sistema gestione pronto soccorso ospedaliero.

| Aspetto | Dettaglio |
|---------|-----------|
| Stack | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| Feature | Code emergenza, REST API, MVC |

### 🍽️ [Dinner](Dinner/README.md)
Sistema di gestione ordini ristorante.

| Aspetto | Dettaglio |
|---------|-----------|
| Stack | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| Feature | Ordini, stati, tracciamento costi |

### 🎫 [Ticket](Ticket/README.md)
Sistema gestione segnalazioni e ticket.

| Aspetto | Dettaglio |
|---------|-----------|
| Stack | Spring Boot 4.0.2, JPA, Thymeleaf, MySQL |
| Feature | Timestamp, stati ticket, REST API |

### 🏛️ [Vault](vault/README.md)
Sistema membership Vault-Tec (tema Fallout).

| Aspetto | Dettaglio |
|---------|-----------|
| Stack | Spring Boot 4.0.2, JPA, MySQL |
| Feature | Richieste ammissione, valutazione candidati |

---

## 🎯 Obiettivi

- Spring Boot Framework
- Spring Data JPA / ORM
- MVC Pattern (Thymeleaf)
- REST API
- Dependency Injection
- MySQL Integration

---

## 🚀 Esecuzione

```bash
# Configurazione database
CREATE DATABASE nome_db;

# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_db
spring.jpa.hibernate.ddl-auto=update

# Esecuzione
cd nome_progetto/
mvn spring-boot:run
```

Accesso: `http://localhost:8080`

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
