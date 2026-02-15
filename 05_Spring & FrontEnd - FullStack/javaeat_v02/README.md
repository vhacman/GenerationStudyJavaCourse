<div align="center">

# JavaEat v02 — Food Delivery Platform (Refactored)

**Spring Boot 4.0.2 | JPA | MySQL**

</div>

Evoluzione di [JavaEat v01](../javaeat_v01/): piattaforma REST API per food delivery con modello dati ristrutturato, relazioni Many-to-Many, MapStruct per il mapping DTO e endpoint di analytics.

## Novita rispetto a v01

| Cambiamento | Prima (v01) | Dopo (v02) |
|-------------|-------------|------------|
| **Dish ↔ Delivery** | ManyToOne | **ManyToMany** (join table `delivery_dish`) |
| **Customer ↔ Rider** | Relazione diretta | Indiretta tramite Delivery |
| **Rider ↔ City** | Rider legato a una citta | Rider indipendente, opera ovunque |
| **Mapping DTO** | Mapper manuali custom | **Manual mapper (no MapStruct)** |
| **Rider** | — | Nuovi campi: `status`, `positionX`, `positionY` |
| **Entita Dish** | Non presente | **Nuova entita** con menu per ristorante |
| **Analytics** | — | Endpoint di conteggio delivery |

## Tecnologie

| Tecnologia | Descrizione |
|------------|-------------|
| Spring Boot 4.0.2 | Framework principale |
| Spring Data JPA | Persistenza dati con Hibernate |
| MySQL | Database relazionale |
| Maven | Build automation |

## Modello Dati

```
City 1───N Restaurant 1───N Dish
  │           │
  │           └──1───N Delivery N───M Dish
  │                      │
  └──1───N Costumer N───1┤
                         │
               Rider N───1
```

| Entita | Campi principali | Relazioni |
|--------|-----------------|-----------|
| **City** | name, province | 1:N Restaurant, 1:N Costumer |
| **Restaurant** | name, email, pw, address, capacity | N:1 City, 1:N Delivery, 1:N Dish |
| **Costumer** | email, pw, legalName, address | N:1 City, 1:N Delivery |
| **Dish** | name, price, description | N:1 Restaurant, **M:N Delivery** |
| **Delivery** | description, status, price, deliveryTimeOpen | N:1 Restaurant/Costumer/Rider, **M:N Dish** |
| **Rider** | email, pw, legalName, serviceCost, status, positionX/Y | 1:N Delivery |

**Stati Delivery:** `OPEN` · `DELIVERED` · `CANCELED`
**Stati Rider:** `AVAILABLE` · `NOTAVAILABLE`

## API Endpoints

### Cities — `/javaeat/api/cities`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutte le citta |
| `GET` | `/{id}` | Trova per ID |
| `GET` | `/search/{name}` | Cerca per nome (contains) |
| `GET` | `/province/{province}` | Filtra per provincia |
| `POST` | `/` | Inserisci nuova citta |
| `POST` | `/save` | Salva/aggiorna citta |
| `DELETE` | `/{id}` | Elimina citta |

### Restaurants — `/javaeat/api/restaurants`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutti i ristoranti |
| `GET` | `/{id}` | Trova per ID |
| `GET` | `/search/{name}` | Cerca per nome |
| `GET` | `/city/{cityId}` | Ristoranti per citta |
| `POST` | `/` | Inserisci ristorante |
| `POST` | `/save` | Salva/aggiorna ristorante |
| `DELETE` | `/{id}` | Elimina ristorante |

### Dishes — `/javaeat/api/dishes`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutti i piatti |
| `GET` | `/{id}` | Trova per ID |
| `GET` | `/restaurant/{restaurantId}` | Menu del ristorante |
| `GET` | `/delivery/{deliveryId}` | Piatti di una consegna |
| `GET` | `/search/{name}` | Cerca per nome |
| `POST` | `/` | Inserisci piatto |
| `POST` | `/save` | Salva/aggiorna piatto |
| `DELETE` | `/{id}` | Elimina piatto |

### Deliveries — `/javaeat/api/deliveries`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutte le consegne |
| `GET` | `/{id}` | Trova per ID |
| `GET` | `/restaurant/{id}` | Consegne per ristorante |
| `GET` | `/costumer/{id}` | Consegne per cliente |
| `GET` | `/rider/{id}` | Consegne per rider |
| `GET` | `/{entity}/{id}/status/{status}` | Filtra per entita e stato |
| `GET` | `/count/{entity}/{id}` | Conteggio per entita |
| `GET` | `/count/status/{status}` | Conteggio per stato |
| `POST` | `/` | Crea consegna |
| `POST` | `/save` | Salva/aggiorna consegna |
| `DELETE` | `/{id}` | Elimina consegna |

### Riders — `/javaeat/api/riders`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutti i rider |
| `GET` | `/{id}` | Trova per ID |
| `GET` | `/status/{status}` | Filtra per disponibilita |
| `GET` | `/search/{legalName}` | Cerca per nome |
| `POST` | `/` | Inserisci rider |
| `POST` | `/save` | Salva/aggiorna rider |
| `DELETE` | `/{id}` | Elimina rider |

### Customers — `/javaeat/api/costumers`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutti i clienti |
| `GET` | `/{id}` | Trova per ID |
| `GET` | `/city/{cityId}` | Clienti per citta |
| `GET` | `/search/{legalName}` | Cerca per nome |
| `POST` | `/` | Inserisci cliente |
| `POST` | `/save` | Salva/aggiorna cliente |
| `DELETE` | `/{id}` | Elimina cliente |

## Architettura

```
API (RestController)
    ↓
Service (Business Logic + Validation)
    ↓
DTO ↔ Manual Mapper
    ↓
Repository (JPA)
    ↓
Entity ↔ MySQL
```

## Struttura

```
src/main/java/com/generation/javaeat/
├── api/
│   ├── CityAPI, RestaurantAPI, CostumerAPI
│   ├── DishAPI, DeliveryAPI, RiderAPI
├── model/
│   ├── entities/
│   │   ├── City, Restaurant, Costumer, Dish
│   │   ├── Delivery, Rider, Validable
│   └── repository/
│       ├── CityRepository, RestaurantRepository
│       ├── CostumerRepository, DishRepository
│       ├── DeliveryRepository, RiderRepository
└── service/
    ├── CityService, RestaurantService, CostumerService
    ├── DishService, DeliveryService, RiderService
    ├── MyServiceException
    └── dto/
        ├── CityDTO, RestaurantDTO, CostumerDTO
        ├── DishDTO, DeliveryDTO, RiderDTO
        └── mapper/
            ├── CityDTOMapper, RestaurantDTOMapper
            ├── CostumerDTOMapper, DishDTOMapper
            ├── DeliveryDTOMapper, RiderDTOMapper
```

## Avvio

```bash
mysql -u root -p -e "CREATE DATABASE javaeattest;"
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
