package com.generation.school.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.school.model.entities.Teacher;
import com.generation.school.view.TeacherView;

public class demoTeacher
{

	public static void main(String[] args)
	{
		Teacher t = new Teacher(4, "Maria", "Verdi", LocalDate.of(1978, 3, 12), 25000.0, "Docente", "Matematica", 15);

		// Teacher estende Person, quindi eredita tutti i suoi attributi e metodi
		// Il metodo toString() di Teacher sovrascrive quello di Person
		// Quale metodo vince? toString() della superclasse o della classe figlia? Il più specifico
		// Java applica il polimorfismo: viene invocato il metodo della classe più specifica (Teacher, non Person)
		//Console.print(t.toString());

		// Utilizzo della View per visualizzare l'insegnante usando il template
		// TeacherView necessita del percorso del template nel costruttore
		TeacherView view = new TeacherView("template/templateTeacher.txt");
		Console.print(view.render(t));
	}

}
