package com.generation.school.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.school.model.entities.Student;
import com.generation.school.view.StudentView;

public class demoStudent
{
    public static void main(String[] args)
    {
    	Student s = new Student(1, "Gabriela", "Hacman", LocalDate.of(1998, 10, 21), 5, "A");

		// Student estende Person, quindi eredita tutti i suoi attributi e metodi
		// Il metodo toString() di Student sovrascrive quello di Person
		// Quale metodo vince? toString() della superclasse o della classe figlia? Il più specifico
		// Java applica il polimorfismo: viene invocato il metodo della classe più specifica (Student, non Person)
		//Console.print(s.toString());
		// Utilizzo della View per visualizzare lo studente usando il template
		// StudentView necessita del percorso del template nel costruttore
		StudentView view = new StudentView("template/templateStudent.txt");
		Console.print(view.render(s));
    	
    }
}
