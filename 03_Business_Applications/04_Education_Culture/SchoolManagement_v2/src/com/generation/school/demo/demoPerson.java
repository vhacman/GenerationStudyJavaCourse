package com.generation.school.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.school.model.entities.Person;
import com.generation.school.view.PersonView;

public class demoPerson
{

	public static void main(String[] args)
	{
		Person p = new Person(1, "Ferdinando", "Primerano", LocalDate.of(1980, 2, 5));
		
		// Ogni classe in Java estende implicitamente Object se non specifica una superclasse
		// Il metodo toString() fornisce una rappresentazione testuale dello stato interno dell'oggetto
		// Lo stato dell'oggetto è rappresentato dai valori correnti dei suoi attributi
		
		
		// Quale metodo vince? toString() della superclasse o della classe figlia? Il più specifico
		// Java applica il polimorfismo: viene invocato il metodo della classe più specifica (Person, non Object)
		//Console.print(p.toString());
		
		
		PersonView	view = new PersonView("template/templatePerson.txt");
		Console.print(view.render(p));
	}

}
