# 🌱 Spring Boot Projects

Questa sezione contiene progetti sviluppati con **Spring Boot**, **Spring Data JPA**, e **gestione database**.

## 📋 Progetti

### [Emergency](Emergency/README.md) 🏥 - Sistema Gestione Pronto Soccorso

Sistema web per la gestione delle emergenze ospedaliere con Spring Boot.

**Tecnologie:** Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL, Maven

**Caratteristiche:**
- Gestione ospedali con code pronto soccorso
- Controller MVC per interfaccia operatore
- API REST per integrazione dati esterni
- Repository Pattern con JPA

---

### [Dinner](Dinner/README.md) 🍽️ - Sistema Gestione Ristorante

Sistema di gestione ordini per ristorante con tracciamento costi e mance.

**Tecnologie:** Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL, Maven

**Caratteristiche:**
- Gestione ordini con descrizione, costo e mancia
- Stati ordine (nuovo, in preparazione, servito, pagato)
- Interfaccia web per inserimento ordini
- REST API per integrazione sistemi esterni

---

### [Ticket](Ticket/README.md) 🎫 - Sistema Gestione Segnalazioni

Sistema di gestione ticket e segnalazioni per help desk e facility management.

**Tecnologie:** Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL, Maven

**Caratteristiche:**
- Tracciamento ticket per stanza/area
- Timestamp apertura/chiusura con LocalDateTime
- Gestione stati (aperto, in lavorazione, chiuso)
- Interfaccia web e REST API

---

### [Vault](vault/README.md) 🏛️ - Sistema Membership Vault-Tec

Sistema di gestione richieste membership ispirato all'universo Fallout.

**Tecnologie:** Spring Boot 4.0.2, Spring Data JPA, MySQL, Maven

**Caratteristiche:**
- Gestione richieste ammissione ai Vault
- Valutazione candidati per reddito e criteri
- Assegnazione a Vault specifici
- Stati richiesta (pending, approved, rejected, waitlist)
- REST API completa

---

## 🎯 Obiettivi Didattici

- **Spring Boot Framework**: Configurazione e struttura applicazioni
- **Spring Data JPA**: ORM, entità, repository
- **MVC Pattern**: Controller, View (Thymeleaf), Model
- **REST API**: Endpoint per consumo dati
- **Dependency Injection**: IoC Container Spring
- **Database Integration**: MySQL, JDBC

---

## 🚀 Come Eseguire il Progetto

### Prerequisiti
- Java 21+
- Maven 3.6+
- MySQL Server
- IDE (Eclipse, IntelliJ, VS Code)

### Passi per l'esecuzione

1. **Configurare il database**:
   ```sql
   CREATE DATABASE emergency_db;
   ```

2. **Configurare `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/emergency_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Eseguire con Maven**:
   ```bash
   cd emergency/
   mvn spring-boot:run
   ```

4. **Accedere all'applicazione**:
   - URL: `http://localhost:8080/emergency/operator`

---

## 📚 Risorse di Apprendimento

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf](https://www.thymeleaf.org/)

---

**Autore:** Hacman Viorica Gabriela
**Corso:** Generation Italy - Java Full Stack Developer
