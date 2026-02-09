# 🔒 Modulo7 - Incapsulamento

Modulo dedicato all'incapsulamento in Java: modificatori di visibilità, getter e setter.

## Argomenti

- Modificatori di visibilità (private, public, protected)
- Getter e setter
- Data hiding
- Validazione nei setter
- Principio di information hiding

## Struttura

```
Modulo7Incapsulamento/
├── src/
└── .settings/
```

## Concetti Chiave

- `private` - accesso solo nella classe
- `public` - accesso universale
- `protected` - accesso package e subclass
- Getter: `getCampo()`
- Setter: `setCampo(valore)`

## Esempio

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

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
