<div align="center">

# Modulo 7 — Incapsulamento

Modificatori di visibilità, getter/setter e principio di information hiding.

</div>

---

## Argomenti

- Modificatori di visibilità: `private`, `public`, `protected`
- Getter e setter
- Data hiding e validazione nei setter
- Principio di information hiding

---

## Concetti Chiave

| Modificatore | Visibilità |
|-------------|-----------|
| `private` | Solo nella classe |
| `protected` | Package + sottoclassi |
| `public` | Universale |

### Esempio

```java
public class Persona {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }
}
```

---

## Struttura

```
Modulo7Incapsulamento/
└── src/
```

## Tecnologie

- **Java** 17+ | **IDE:** Eclipse

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna ai Fondamenti](../README.md) · [README principale](../../README.md)

</div>
