package com.generation.nsmi.model.entities;

import java.time.LocalDate;

/*
 * EREDITARIETÀ: Doctor estende Person
 *
 * Doctor È UNA Person con attributi e comportamenti aggiuntivi specifici
 * per un medico (stipendio e specializzazione)
 */
public class Doctor extends Person
{
	// Attributi specifici di Doctor
	private double 					salary;				// Stipendio del dottore
	private SpecialtyType 			specialization;		// Specializzazione medica

	public Doctor() { super(); }

	public Doctor(int id, String firstName, String lastName, String SSN, LocalDate dob,
	              double salary, SpecialtyType specialization) {
		super(id, firstName, lastName, SSN, dob);
		this.specialization = specialization;
		setSalary(salary); // Usa il setter per validare
	}

	// Getters e Setters
	public double getSalary() {
		return salary;
	}

	/**
	 * Imposta lo stipendio validandolo contro il range della specializzazione
	 * @param salary lo stipendio da impostare
	 * @throws IllegalArgumentException se lo stipendio non è valido per la specializzazione
	 */
	public void setSalary(double salary) {
		if (salary < 0) {
			throw new IllegalArgumentException("Lo stipendio non può essere negativo");
		}

		if (specialization != null && !specialization.isValidSalary(salary)) {
			throw new IllegalArgumentException(
				String.format("Lo stipendio €%.2f non è valido per la specializzazione %s (range: €%.2f - €%.2f)",
					salary, specialization.name(), specialization.getMinSalary(), specialization.getMaxSalary())
			);
		}

		this.salary = salary;
	}

	public SpecialtyType getSpecialization() {
		return specialization;
	}

	/**
	 * Imposta la specializzazione e rivalidare lo stipendio
	 * @param specialization la nuova specializzazione
	 */
	public void setSpecialization(SpecialtyType specialization) {
		this.specialization = specialization;
		// Rivalidare lo stipendio con la nuova specializzazione
		if (specialization != null && salary > 0 && !specialization.isValidSalary(salary)) {
			System.err.println("ATTENZIONE: Lo stipendio attuale (€" + salary +
				") non è valido per la nuova specializzazione " + specialization.name());
		}
	}

	/**
	 * Verifica se lo stipendio è valido per la specializzazione corrente
	 * @return true se lo stipendio è nel range valido
	 */
	public boolean isValidSalaryForSpecialty() {
		if (specialization == null) return true;
		return specialization.isValidSalary(salary);
	}

	/**
	 * Calcola la percentuale di posizionamento dello stipendio nel range della specializzazione
	 * @return percentuale (0-100) del posizionamento nel range
	 */
	public double getSalaryPercentageInRange() {
		if (specialization == null) return 0;
		double min = specialization.getMinSalary();
		double max = specialization.getMaxSalary();
		return ((salary - min) / (max - min)) * 100;
	}

	/**
	 * Restituisce informazioni dettagliate sullo stipendio
	 * @return stringa con dettagli sullo stipendio e il suo posizionamento
	 */
	public String getSalaryInfo() {
		if (specialization == null) {
			return String.format("Stipendio: €%.2f (specializzazione non specificata)", salary);
		}

		String position;
		double percentage = getSalaryPercentageInRange();

		if (percentage < 33) {
			position = "livello junior";
		} else if (percentage < 66) {
			position = "livello intermedio";
		} else {
			position = "livello senior";
		}

		return String.format("Stipendio: €%.2f (%s, %.1f%% del range %s)",
			salary, position, percentage, specialization.name());
	}

	/*
	 * OVERRIDE del metodo toString()
	 * Include le informazioni specifiche di Doctor + quelle di Person
	 */
	@Override
	public String toString() {
		String specInfo = (specialization != null) ? specialization.toString() : "Non specificata";
		return "Doctor [" +
		       "salary=€" + String.format("%.2f", salary) +
		       ", specialization=" + specInfo +
		       ", valid=" + isValidSalaryForSpecialty() +
		       ", " + super.toString() + "]";
	}

	/**
	 * Fornisce una rappresentazione dettagliata del dottore
	 * @return stringa con tutte le informazioni del dottore
	 */
	public String toDetailedString() {
		return super.toString() + "\n" +
		       "Specializzazione: " + ((specialization != null) ? specialization.toString() : "Non specificata") + "\n" +
		       getSalaryInfo();
	}
}
