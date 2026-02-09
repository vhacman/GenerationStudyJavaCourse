# 💻 PC Configurator

Applicazione Spring Boot per la configurazione e gestione di PC personalizzati.

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
pcconfigurator/
├── src/main/java/com/generation/pcconfigurator/
│   ├── PcconfiguratorApplication.java       # Entry point
│   ├── ServletInitializer.java
│   ├── api/
│   │   └── ConfigurationAPI.java            # REST endpoints
│   └── model/
│       ├── entities/
│       │   ├── Configuration.java           # Entity
│       │   └── Validable.java               # Interface validazione
│       └── repository/
│           └── ConfigurationRepository.java # JPA Repository
└── src/main/resources/
    └── application.properties
```

## 🎯 Funzionalità

- Creazione configurazioni PC personalizzate
- Validazione tramite interfaccia `Validable`
- API RESTful per integrazione frontend
- Persistenza su database MySQL

## 🚀 Avvio

```bash
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

## 🔌 API Endpoints

| Metodo | Endpoint | Descrizione |
|--------|----------|------------|
| GET | `/api/configurations` | Lista tutte le configurazioni |
| GET | `/api/configurations/{id}` | Ottieni configurazione per ID |
| POST | `/api/configurations` | Crea nuova configurazione |
| PUT | `/api/configurations/{id}` | Aggiorna configurazione |
| DELETE | `/api/configurations/{id}` | Elimina configurazione |

## ✅ Validazione

Le configurazioni implementano l'interfaccia `Validable` per garantire la correttezza dei dati.

## ⚙️ Configurazione Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pcconfigurator_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
