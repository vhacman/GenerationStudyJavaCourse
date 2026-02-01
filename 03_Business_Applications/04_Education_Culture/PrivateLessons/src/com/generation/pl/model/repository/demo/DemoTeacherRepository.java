package com.generation.pl.model.repository.demo;

import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.repository.interfaces.TeacherRepository;

public class DemoTeacherRepository
{

	public static void main(String[] args) throws Exception
	{
		TeacherRepository teacherRepo = Context.getDependency(TeacherRepository.class);
	
		Console.print("=== TEST INSERT ===");

		// Test 1: Insert nuovo teacher con una materia
		Teacher t1 = new Teacher();
		t1.setFirstName("Chiara");
		t1.setLastName("Martini");
		t1.setSsn("MRTCHR88H50H501C");
		t1.setEmail("chiara.martini@example.com");
		t1.setPassword("teach123");
		t1.setBio("Mathematics teacher with a passion for algebra.");
		t1.setPricePerLesson(38);
		t1.setSubjects("MATH");
		t1.setStatus("ACTIVE");

		teacherRepo.insert(t1);
		Console.print("Teacher inserito: " + t1.getFirstName() + " " + t1.getLastName() +
					  " Subjects: " + t1.getSubjectsCSV() + " - Price: €" + t1.getPricePerLesson());

		// Test 2: Insert teacher con multiple materie
		Teacher t2 = new Teacher();
		t2.setFirstName("Roberto");
		t2.setLastName("Conti");
		t2.setSsn("CNTRRT75M12H501D");
		t2.setEmail("roberto.conti@example.com");
		t2.setPassword("teacher456");
		t2.setBio("Senior developer with 20 years of experience.");
		t2.setPricePerLesson(55);
		t2.setSubjects("JAVA,SQL,PROGRAMMING");
		t2.setStatus("ACTIVE");

		teacherRepo.insert(t2);
		Console.print(" Teacher inserito: " + t2.getFirstName() + " " + t2.getLastName() +
					  " - Subjects: " + t2.getSubjectsCSV() + " - Price: €" + t2.getPricePerLesson());
		

		// TEST LOGIN
		Console.print("\n=== TEST LOGIN ===");
		Teacher t = teacherRepo.login("mario.rossi@example.com", "password123");

		if(t != null)
			Console.print("Login successful: " + t.getFirstName() + " " + t.getLastName() +
						  " - Subjects: " + t.getSubjectsCSV() + " - Price: €" + t.getPricePerLesson());
		else
			Console.print("Login failed!");
	}

}
