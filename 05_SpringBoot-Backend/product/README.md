<div align="center">

# Product — Catalogo Prodotti e Recensioni

**Spring Boot 4.0.2 | JPA | Validation | MySQL**

</div>

REST API per la gestione di un catalogo prodotti con sistema di recensioni. Ogni prodotto puo ricevere valutazioni da 1 a 5 stelle.

## Tecnologie

| Tecnologia | Descrizione |
|------------|-------------|
| Spring Boot 4.0.2 | Framework principale |
| Spring Data JPA | Persistenza dati con Hibernate |
| Spring Validation | Validazione input |
| MySQL | Database relazionale |
| Maven | Build automation |

## Modello Dati

```
Product 1───N Review
```

| Entita | Campi principali | Note |
|--------|-----------------|------|
| **Product** | name (2-100 char), price | Validazione duplicati nome |
| **Review** | title, author, score (1-5) | N:1 Product, cascade delete |

## API Endpoints

### Products — `/product/api/products`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutti i prodotti |
| `GET` | `/{id}` | Trova prodotto per ID |
| `POST` | `/` | Crea prodotto (controlla duplicati) |
| `GET` | `/{id}/reviews` | Recensioni del prodotto |
| `POST` | `/{id}/reviews` | Aggiungi recensione al prodotto |

### Reviews — `/review/api/reviews`

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| `GET` | `/` | Lista tutte le recensioni |
| `GET` | `/{id}` | Trova recensione per ID |

## Validazione

- **Product**: nome obbligatorio (2-100 caratteri), prezzo non negativo, nome univoco
- **Review**: titolo e autore (2-100 caratteri), score 1-5, prodotto obbligatorio
- Interfaccia `Validable` per validazione centralizzata

## Struttura

```
src/main/java/com/generation/product/
├── api/
│   ├── ProductAPI.java
│   ├── ReviewAPI.java
│   └── dto/
│       ├── ProductDTO.java / ProductDTOMapper.java
│       └── ReviewDTO.java / ReviewDTOMapper.java
├── model/
│   ├── entities/
│   │   ├── Product.java, Review.java, Validable.java
│   └── repository/
│       ├── ProductRepository.java
│       └── ReviewRepository.java
└── src/main/resources/
    ├── application.properties
    └── data.sql                    # 50 prodotti di esempio
```

## Avvio

```bash
mysql -u root -p -e "CREATE DATABASE product;"
mvn spring-boot:run
```

**URL:** `http://localhost:8080`

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Spring Boot](../README.md) · [README principale](../../README.md)

</div>
