package com.generation.pl.model.repository.demo;

import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.repository.interfaces.StudentRepository;

public class DemoStudentRepository
{

	public static void main(String[] args) throws Exception
	{
		StudentRepository studentRepo = Context.getDependency(StudentRepository.class);
		/*
		
		Console.print("=== TEST INSERT ===");
		// Test 1: Insert nuovo studente valido
		Student s1 = new Student();
		s1.setFirstName("Francesco");
		s1.setLastName("Romano");
		s1.setSsn("RMNFNC04D10H501A");
		s1.setEmail("francesco.romano@example.com");
		s1.setPassword("mypass789");
		s1.setDob(LocalDate.of(2004, 4, 10));
		s1.setStatus("ACTIVE");

		studentRepo.insert(s1);
		Console.print("Studente inserito: " + s1.getFirstName() + " " + s1.getLastName() + " (Age: " + s1.getAge() + ")");

		// Test 2: Insert studente con età minima (14 anni)
		Student s2 = new Student();
		s2.setFirstName("Alessia");
		s2.setLastName("Gallo");
		s2.setSsn("GLLLSA10E50H501B");
		s2.setEmail("alessia.gallo@example.com");
		s2.setPassword("student2024");
		s2.setDob(LocalDate.now().minusYears(14));
		s2.setStatus("PENDING");

		studentRepo.insert(s2);
		Console.print("Studente inserito: " + s2.getFirstName() + " " + s2.getLastName() + " (Age: " + s2.getAge() + ")");
		*/

		// TEST LOGIN - Usa utenti già presenti nel DB
		Console.print("\n=== LOGIN TEST ===");
		Student s = studentRepo.login("giulia.bianchi@example.com", "student456");
		Student s1 = studentRepo.login("marco.verdi@example.com", "student456");
		Student s2 = studentRepo.login("sofia.neri@example.com", "student456");

		if(s != null)
			Console.print("Login successful: " + s.getFirstName() + " " + s.getLastName() + " - Age: " + s.getAge());
		else
			Console.print("Login failed for giulia.bianchi@example.com");

		if(s1 != null)
			Console.print("Login successful: " + s1.getFirstName() + " " + s1.getLastName() + " - Age: " + s1.getAge());
		else
			Console.print("Login failed for marco.verdi@example.com");

		if(s2 != null)
			Console.print("Login successful: " + s2.getFirstName() + " " + s2.getLastName() + " - Age: " + s2.getAge());
		else
			Console.print("Login failed for sofia.neri@example.com");
	}

}
