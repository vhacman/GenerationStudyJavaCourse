<div align="center">

# JavaEat v01 — Food Delivery Platform

**Spring Boot 4.0.2 | JPA | MySQL | DTO Pattern**

</div>

Piattaforma REST API per la gestione di un servizio di food delivery: citta, ristoranti, clienti, rider e consegne.

## Tecnologie

| Tecnologia | Descrizione |
|------------|-------------|
| Spring Boot 4.0.2 | Framework principale |
| Spring Data JPA | Persistenza dati con Hibernate |
| MySQL | Database relazionale |
| Maven | Build automation |

## Modello Dati

```
City 1───N Restaurant 1───N Delivery N───1 Rider
  │                            │
  └──1───N Costumer ───1───N───┘
```

| Entita | Campi principali | Relazioni |
|--------|-----------------|-----------|
| **City** | name, province | 1:N Restaurant |
| **Restaurant** | name, email, pw (MD5), address, capacity | N:1 City, 1:N Delivery |
| **Costumer** | email, pw (MD5), legalName, address | N:1 City, 1:N Delivery |
| **Rider** | email, pw (MD5), legalName, serviceCost | 1:N Delivery |
| **Delivery** | description, status, price, deliveryTimeOpen | N:1 Restaurant/Costumer/Rider |

**Stati Delivery:** `OPEN` · `DELIVERED` · `CANCELED`

## API Endpoints

### Cities — `/javaeat/api/cities`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutte le citta |
| `GET` | `/{id}` | Trova citta per ID |
| `GET` | `/byname/{name}` | Cerca per nome (contains) |
| `GET` | `/byprovince/{province}` | Filtra per provincia |
| `POST` | `/` | Crea nuova citta |
| `PATCH` | `/{id}/changeprovince/{province}` | Aggiorna provincia |
| `DELETE` | `/{id}` | Elimina citta |

### Restaurants — `/javaeat/api/restaurants`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/{id}` | Trova ristorante per ID (con citta) |
| `POST` | `/` | Crea ristorante (password MD5, validazione citta) |
| `DELETE` | `/{id}` | Elimina ristorante (controlla delivery associate) |

### Customers — `/javaeat/api/customers`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/{id}` | Trova cliente per ID |
| `POST` | `/` | Crea cliente (password MD5, validazione citta) |
| `DELETE` | `/{id}` | Elimina cliente (controlla delivery associate) |

### Riders — `/javaeat/api/riders`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/{id}` | Trova rider per ID |
| `POST` | `/` | Crea rider (password MD5) |
| `DELETE` | `/{id}` | Elimina rider (controlla delivery associate) |

### Deliveries — `/javaeat/api/deliveries`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutte le consegne |
| `GET` | `/{id}` | Trova consegna per ID |
| `POST` | `/` | Crea consegna con logica di business |
| `DELETE` | `/{id}` | Elimina consegna |

## Logica di Business

- **Autenticazione**: password hashate con MD5 alla registrazione
- **Limite ordini**: un cliente puo avere una sola delivery OPEN alla volta
- **Capacita ristorante**: verifica che le delivery OPEN non superino la capacity
- **Assegnazione rider**: assegnazione automatica del rider disponibile
- **Integrita referenziale**: controllo delivery associate prima dell'eliminazione

## Struttura

```
src/main/java/com/generation/javaeat/
├── api/
│   ├── CityAPI.java
│   ├── CustomerAPI.java
│   ├── RestaurantAPI.java
│   ├── DeliveryAPI.java
│   ├── RiderAPI.java
│   ├── MD5Hasher.java
│   └── dto/
│       ├── CityDTO.java / CityDTOMapper.java
│       ├── CustomerDTO.java / CustomerDTOMapper.java
│       ├── RestaurantDTO.java / RestaurantDTOMapper.java
│       ├── RiderDTO.java / RiderDTOMapper.java
│       └── DeliveryDTO.java / DeliveryDTOMapper.java
└── model/
    ├── entities/
    │   ├── City.java, Costumer.java, Restaurant.java
    │   ├── Rider.java, Delivery.java, Validable.java
    └── repository/
        ├── CityRepository.java, CustomerRepository.java
        ├── RestaurantRepository.java, RiderRepository.java
        └── DeliveryRepository.java
```

## Avvio

```bash
mysql -u root -p -e "CREATE DATABASE javaeat;"
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
