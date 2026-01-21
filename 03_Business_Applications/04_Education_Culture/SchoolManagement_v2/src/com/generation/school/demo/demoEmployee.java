package com.generation.school.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.school.model.entities.Employee;
import com.generation.school.view.EmployeeView;

public class demoEmployee
{

	public static void main(String[] args)
	{
		Employee e = new Employee();
		e.setId(2);
		e.setName("Mario");
		e.setSurname("Rossi");
		e.setDateOfBirth(LocalDate.of(1975, 5, 15));
		e.setSalary(2500.00);
		e.setRole("Segretario");

		// Employee estende Person, quindi eredita tutti i suoi attributi e metodi
		// Il metodo toString() di Employee sovrascrive quello di Person
		// Quale metodo vince? toString() della superclasse o della classe figlia? Il più specifico
		// Java applica il polimorfismo: viene invocato il metodo della classe più specifica (Employee, non Person)
		//Console.print(e.toString());

		// Utilizzo della View per visualizzare l'impiegato usando il template
		// EmployeeView necessita del percorso del template nel costruttore
		EmployeeView view = new EmployeeView("template/templateEmployee.txt");
		Console.print(view.render(e));
	}

}
