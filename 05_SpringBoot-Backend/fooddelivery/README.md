<div align="center">

# Food Delivery — Consegne a Domicilio

**Spring Boot 4.0.2 | JPA | MapStruct | MySQL**

</div>

Progetto Spring Boot per la gestione di un servizio di food delivery con ristoranti, rider, clienti e consegne.

## Tecnologie

| Tecnologia | Descrizione |
|------------|------------|
| Spring Boot 4.0.2 | Framework principale |
| Spring Data JPA | Persistenza dati |
| MySQL | Database relazionale |
| MapStruct 1.6.3 | Mapping DTO / Entity |
| Lombok 1.18.38 | Riduzione boilerplate |
| Maven | Build automation |

## Struttura

```
fooddelivery/
├── src/main/java/com/generation/fooddelivery/
│   ├── FooddeliveryApplication.java        # Entry point
│   ├── api/
│   │   ├── DeliveryAPI.java                # REST controller consegne
│   │   ├── RestaurantAPI.java              # REST controller ristoranti
│   │   ├── RiderAPI.java                   # REST controller rider
│   │   └── dto/
│   │       ├── CustomerDTO.java
│   │       ├── DeliveryDTO.java
│   │       ├── FeedbackDTO.java
│   │       ├── RestaurantDTO.java
│   │       ├── RiderDTO.java
│   │       └── mapper/                     # MapStruct mappers
│   ├── model/
│   │   ├── entities/
│   │   │   ├── City.java
│   │   │   ├── Customer.java
│   │   │   ├── Delivery.java
│   │   │   ├── Dish.java (*)
│   │   │   ├── Feedback.java
│   │   │   ├── Restaurant.java
│   │   │   └── Rider.java
│   │   └── repository/
│   │       ├── CustomerRepository.java
│   │       ├── DeliveryRepository.java
│   │       ├── RestaurantRepository.java
│   │       └── RiderRepository.java
│   └── service/
│       ├── DeliveryService.java
│       ├── RestaurantService.java
│       ├── RiderService.java
│       └── ServiceException.java
└── src/main/resources/
    └── application.properties
```

## Modello Dati

```
City 1───N Restaurant 1───N Delivery N───1 Rider
                                │
                            N───1 Customer
```

| Entita | Descrizione |
|--------|-------------|
| **City** | Citta con nome e provincia |
| **Restaurant** | Ristorante con posizione, capacita e citta |
| **Rider** | Corriere con posizione, email e autenticazione (MD5) |
| **Customer** | Cliente con indirizzo, email e autenticazione (MD5) |
| **Delivery** | Consegna con stato (OPEN/CLOSED), prezzo e descrizione |
| **Feedback** | Feedback sulla consegna |

## API Endpoints

### Deliveries — `/fooddelivery/api/deliveries`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/{id}` | Trova consegna per ID |
| `POST` | `/` | Crea nuova consegna |

### Restaurants — `/fooddelivery/api/restaurants`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutti i ristoranti |

### Riders — `/fooddelivery/api/riders`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/{id}` | Trova rider per ID |
| `GET` | `/activebetween/{d1}/{d2}` | Rider attivi tra due date |

## Logica di Business

- **Autenticazione**: login cliente via email + password hash MD5
- **Disponibilita ristorante**: controlla che le delivery OPEN siano < capacita
- **Limite ordini**: un cliente puo avere una sola delivery OPEN alla volta
- **Assegnazione rider**: se non specificato, viene assegnato il rider libero piu vicino al ristorante (distanza euclidea, raggio massimo)

## Avvio

```bash
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

## Configurazione Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fooddelivery
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
