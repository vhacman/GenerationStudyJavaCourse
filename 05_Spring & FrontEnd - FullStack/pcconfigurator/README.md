# PC Configurator - Configuratore PC

Applicazione Spring Boot per configurare e gestire configurazioni di PC personalizzati.

## Tecnologie Utilizzate

- **Spring Boot** - Framework principale
- **Spring Data JPA** - Persistenza dati
- **MySQL** - Database
- **Maven** - Build tool

## Struttura del Progetto

```
pcconfigurator/
├── src/main/java/com/generation/pcconfigurator/
│   ├── PcconfiguratorApplication.java       # Main application
│   ├── ServletInitializer.java
│   ├── api/
│   │   └── ConfigurationAPI.java           # REST endpoints configurazioni
│   └── model/
│       ├── entities/
│       │   ├── Configuration.java          # Entity Configurazione
│       │   └── Validable.java              # Interface validazione
│       └── repository/
│           └── ConfigurationRepository.java # Repository JPA
└── src/main/resources/
    └── application.properties              # Configurazione database
```

## Funzionalità Principali

- Creazione e gestione configurazioni PC personalizzate
- Validazione configurazioni tramite interface Validable
- API RESTful per integrazione frontend
- Persistenza configurazioni su database MySQL

## Configurazione

Configurare il database in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pcconfigurator_db
spring.datasource.username=root
spring.datasource.password=your_password
```

## Avvio dell'Applicazione

```bash
mvn spring-boot:run
```

L'applicazione sarà disponibile su `http://localhost:8080`

## API Endpoints

- `GET /api/configurations` - Ottieni tutte le configurazioni
- `GET /api/configurations/{id}` - Ottieni configurazione per ID
- `POST /api/configurations` - Crea nuova configurazione
- `PUT /api/configurations/{id}` - Aggiorna configurazione
- `DELETE /api/configurations/{id}` - Elimina configurazione

## Validazione

Le configurazioni implementano l'interface `Validable` per garantire la correttezza dei dati inseriti.
