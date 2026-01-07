package com.generation.school.model.entities;

import java.time.LocalDate;

/**
 * Classe che rappresenta un insegnante nel sistema scolastico.
 * Estende Employee aggiungendo attributi specifici relativi all'insegnamento.
 *
 */
public class Teacher extends Employee
{
	// ==================== ATTRIBUTI ====================
	/** Materia insegnata dal docente */
	protected String	subject;
	/** Anni di esperienza nell'insegnamento */
	protected int		yearsOfExp;

	// ==================== COSTRUTTORI ====================
	/**
	 * Costruttore vuoto.
	 * Utile per la creazione di oggetti tramite framework o per inizializzazione graduale.
	 */
	public Teacher () { super(); }

	/**
	 * Costruttore completo.
	 *
	 * @param id Identificativo univoco
	 * @param name Nome dell'insegnante
	 * @param surname Cognome dell'insegnante
	 * @param dateOfBirth Data di nascita
	 * @param salary Stipendio mensile
	 * @param role Ruolo o mansione
	 * @param subject Materia insegnata
	 * @param yearsOfExp Anni di esperienza nell'insegnamento
	 */
	public Teacher (int id, String name, String surname, LocalDate dateOfBirth,
			double salary, String role, String subject, int yearsOfExp)
	{
		super(id, name, surname, dateOfBirth, salary, role);

		this.subject = subject;
		this.yearsOfExp = yearsOfExp;
	}

	// ==================== GETTER E SETTER ====================

	/** @return la materia insegnata */
	public String 	getSubject() 					{return subject;}

	/** @return gli anni di esperienza */
	public int 		getYears() 						{return yearsOfExp;}

	/** @param subject la materia da impostare */
	public void 	setSubject(String subject) 		{this.subject = subject;}

	/** @param years gli anni di esperienza da impostare */
	public void 	setYears(int years) 			{this.yearsOfExp = years;}

	/**
	 * Verifica se l'oggetto Teacher è valido.
	 * Un insegnante è valido se:
	 * - I dati base dell'impiegato sono validi (super.isValid())
	 * - La materia non è null e non è vuota
	 * - Gli anni di esperienza sono maggiori o uguali a 0
	 *
	 * @return true se l'insegnante è valido, false altrimenti
	 */
	@Override
	public boolean isValid()
	{
		if (!super.isValid()) return false;
		if (subject == null || subject.isEmpty()) return false;
		if (yearsOfExp < 0) return false;
		return true;
	}

	// ==================== OVERRIDE METODI ============================
	// =========== SOVRASCRIVERE UN METODO DI UN SUPERTIPO =============

	/**
	 * Restituisce una rappresentazione testuale dell'oggetto Teacher.
	 * Utilizza super.toString() per includere gli attributi della superclasse Employee
	 * e aggiunge gli attributi specifici di Teacher (subject e yearsOfExp).
	 *
	 * @return stringa formattata con le informazioni dell'insegnante
	 */
	@Override
	public String toString()
	{
		return super.toString() +
				", subject='" + subject + '\'' +
				", yearsOfExperience=" + yearsOfExp;
	}
}
