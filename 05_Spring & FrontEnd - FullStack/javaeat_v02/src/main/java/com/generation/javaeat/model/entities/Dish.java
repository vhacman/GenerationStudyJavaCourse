package com.generation.javaeat.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/**
 * Entità che rappresenta un piatto nel menu di un ristorante.
 * Dish ha una doppia natura:
 * - È un elemento del catalogo del ristorante (relazione M:1 con Restaurant, definizione statica)
 * - È un componente dell'ordine consegnato (relazione M:M con Delivery, utilizzo concreto)
 * Cambiata relazione con Delivery: da @ManyToOne (un solo delivery per piatto) a @ManyToMany
 * perché una consegna contiene piu' piatti e lo stesso piatto puo' essere in piu' consegne.
 * La tabella associativa delivery_dish risolve la relazione M:M nel modello relazionale.
 */
@Entity
public class Dish implements Validable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int price;
	private String name;
	private String description;

	// Dish M:1 Restaurant - ogni piatto appartiene a un unico ristorante (definizione nel catalogo)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	// Dish M:M Delivery - lato owner della relazione
	// Cambiato da @ManyToOne Delivery (singola) a @ManyToMany List<Delivery>
	// perché un piatto puo' comparire in molte consegne e una consegna contiene molti piatti
	// Genera la tabella associativa delivery_dish con dish_id e delivery_id
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	(
		name = "delivery_dish",
		joinColumns = @JoinColumn(name = "dish_id"),
		inverseJoinColumns = @JoinColumn(name = "delivery_id")
	)
	private List<Delivery> deliveries;

	public int getId()
	{
		return id;
	}

	public int getPrice()
	{
		return price;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Restaurant getRestaurant()
	{
		return restaurant;
	}

	public List<Delivery> getDeliveries()
	{
		return deliveries;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setRestaurant(Restaurant restaurant)
	{
		this.restaurant = restaurant;
	}

    public void setDeliveries(List<Delivery> deliveries)
    {
        this.deliveries = deliveries;
    }

	/**
	 * Valida i campi del piatto.
	 *
	 * @return lista di errori di validazione
	 */
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();

		if (name == null || name.trim().isEmpty())
			errors.add("Name cannot be null or empty");
		if (price < 0)
			errors.add("Price cannot be negative");
		if (restaurant == null)
			errors.add("Restaurant cannot be null");

		return errors;
	}
}
