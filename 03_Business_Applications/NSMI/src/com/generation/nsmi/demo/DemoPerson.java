package com.generation.nsmi.demo;

import java.time.LocalDate;

import com.generation.nsmi.model.entities.Person;
import com.generation.nsmi.model.entities.Patient;
import com.generation.nsmi.model.entities.Doctor;
import com.generation.nsmi.model.entities.InsuranceType;
import com.generation.nsmi.view.PersonView;

public class DemoPerson
{

	public static void main(String[] args)
	{
		
		/*
		 * TIPO FORMALE vs TIPO EFFETTIVO
		 *
		 * La variabile p è di TIPO FORMALE Person (dichiarato nel codice).
		 * Il tipo dell'oggetto che p conterrà è il TIPO EFFETTIVO (determinato a runtime).
		 *
		 * IMPORTANTE: Il tipo formale e il tipo effettivo NON devono necessariamente coincidere,
		 * ma devono essere COMPATIBILI (relazione di ereditarietà).
		 *
		 * Person è il tipo formale di p.
		 */

		// p è POLIMORFICA - può assumere forme diverse (Person, Patient, Doctor, etc.)
		// POLIMORFISMO DI OGGETTO: una variabile può riferirsi a oggetti di tipi diversi
		Person p; // p può contenere qualunque Person (o sue sottoclassi: Patient, Doctor, etc.)

		/*
		 * PRINCIPIO DI SOSTITUZIONE DI LISKOV (Liskov Substitution Principle)
		 *
		 * Posso SEMPRE mettere un SOTTOTIPO al posto di un SUPERTIPO.
		 * Il contrario NON è vero (non posso mettere un supertipo dove serve un sottotipo).
		 *
		 * Esempi validi:
		 * - Person p = new Person();   ✓ (tipo formale = tipo effettivo)
		 * - Person p = new Patient();  ✓ (upcasting: sottotipo al posto di supertipo)
		 *
		 * Esempio NON valido:
		 * - Patient p = new Person();  ✗ (downcasting implicito non permesso)
		 *
		 * SIGNIFICATO PRATICO:
		 * Questo principio garantisce che una sottoclasse possa essere usata
		 * ovunque sia richiesta la superclasse, senza rompere il comportamento del programma.
		 *
		 * In altre parole: se scrivo un metodo che accetta un parametro di tipo Person,
		 * posso passargli sia un oggetto Person che un oggetto Patient (o qualsiasi sottoclasse).
		 * Il metodo continuerà a funzionare correttamente perché Patient HA TUTTE
		 * le caratteristiche di Person (più altre aggiuntive).
		 *
		 * Il codice NON si rompe perché Patient è compatibile con Person.
		 */

		/*
		 * POLIMORFISMO DI OGGETTO IN AZIONE
		 *
		 * Tipo formale di p => Person
		 * Tipo concreto/effettivo di p => Patient
		 * La variabile p (polimorfica) PUNTA a un oggetto di tipo Patient
		 *
		 * p può cambiare "forma" a runtime - ecco il polimorfismo!
		 */
		p = new Patient(); // p ora "è" un Patient

		// Usiamo p come se fosse una Person (tipo formale)
		// Ma p punta a un Patient (tipo effettivo) - variabile polimorfica!
		p.setId(1);
		p.setFirstName("Carlo");
		p.setLastName("Ianna");
		p.setDob(1997, 9, 24); // year, month, day
		p.setSSN("PIPPO");

		/*
		 * POLIMORFISMO DI METODO (Dynamic Binding)
		 *
		 * REGOLA CHIAVE:
		 * - TIPO VARIABILE (formale) → definisce COSA posso fare
		 * - TIPO OGGETTO (effettivo) → definisce COME viene fatto
		 *
		 * p è polimorfica: tipo formale Person, tipo effettivo Patient
		 * - Posso chiamare solo metodi di Person (COSA)
		 * - Ma toString() esegue la versione di Patient (COME) - dynamic binding!
		 *
		 * Output: Patient [history=null, insurance=null, Person [id=1, ...]]
		 */
		System.out.println(p.toString()); // Chiama toString() di Patient a runtime!
		// Creiamo la View con il path alla cartella template
		PersonView		view = new PersonView("templateFolder");

		// Creiamo una Person "liscia"
		Person			person1 = new Person(10, "Mario", "Rossi", "RSSMRA80A01H501Z",
		                            LocalDate.of(1980, 1, 1));

		// Creiamo un Patient con storia clinica e assicurazione
		Patient			patient1 = new Patient(20, "Laura", "Bianchi", "BNCLAR85M42F205X",
		                               LocalDate.of(1985, 8, 2),
		                               "Ipertensione, diabete tipo 2",
		                               InsuranceType.FULL);

		/*
		 * POLIMORFISMO NELLA VIEW
		 *
		 * Il metodo render() accetta Person (tipo formale)
		 * ma può gestire sia Person che Patient (tipi effettivi)
		 * grazie al polimorfismo e instanceof
		 */

		System.out.println("RENDER DI PERSON:");
		System.out.println(view.render(person1)); // Tipo effettivo: Person

		System.out.println("\nRENDER DI PATIENT:");
		System.out.println(view.render(patient1)); // Tipo effettivo: Patient

		/*
		 *
		 * Possiamo anche passare patient1 come Person (upcasting)
		 * Il metodo render() scoprirà a runtime che è un Patient
		 * e chiamerà renderPatient() - dynamic binding!
		 */
		System.out.println("\nRENDER CON UPCASTING (Person p = patient1):");
		Person p2 = patient1; // Upcasting implicito
		System.out.println(view.render(p2)); // Chiama renderPatient() a runtime!

		// Creiamo un Doctor
		Doctor doctor1 = new Doctor();

		System.out.println("\nRENDER DI DOCTOR:");
		System.out.println(view.render(doctor1)); // Tipo effettivo: Doctor


	}

}
