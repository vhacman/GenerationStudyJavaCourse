package com.generation.nsmpi.listexamples;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Console;
import com.generation.nsmpi.etl.DoctorExtractor;
import com.generation.nsmpi.etl.DoctorExtractorFactory;
import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.model.entities.Gender;
import com.generation.nsmpi.model.entities.Specialty;

public class DoctorListExamples
{

	public static void main(String[] args)
	{
		Console.print("Inserire nome del file da caricare");
		String filename = "";

		DoctorExtractor extractor     = DoctorExtractorFactory.make(filename);
		List<Doctor>    all           = extractor.getDoctorsFromFile(filename);

		Console.print("Sono stati letti " + all.size() + " dottori");

		int    nMen                   = countMen(all);
		int    nWomen                 = countWomen(all);
		int    nNonBinary             = countNonBinary(all);

		Console.print("Numero uomini: " + nMen);
		Console.print("Numero donne: " + nWomen);
		Console.print("Numero non binari: " + nNonBinary);

		double averageAge             = calculateAverageAge(all);
		Console.print("Età media dei dottori: " + averageAge + " anni");

		List<Doctor> women            = filterWomen(all);
		Console.print("Riepilogo delle donne:");
		for (Doctor d : women)
			Console.print(d.getFirstName() + " " + d.getLastName());

		int avgWomenAge               = calculateAverageAge(women);
		Console.print("Età media delle dottoresse: " + avgWomenAge + " anni");

		double totalSalary            = calculateTotalSalary(all);
		Console.print("Somma totale salari: €" + totalSalary);

		double menSalary              = calculateMenSalary(all);
		double womenSalary            = calculateWomenSalary(all);
		Console.print("Somma salari uomini: €" + menSalary + ", Somma salari donne: €" + womenSalary);

		double genderGap              = calculateGenderGap(menSalary, womenSalary, nMen, nWomen);
		Console.print("Gender gap: " + genderGap + "%");

		List<String> payslips         = generatePayslips(all);
		Console.print("Buste paga:");
		for (String payslip : payslips)
			Console.print(payslip);

		int nCardiologists            = countCardiologists(all);
		Console.print("Numero di cardiologi: " + nCardiologists);

		int nPsychiatrists            = countPsychiatrists(all);
		Console.print("Numero di psichiatri: " + nPsychiatrists);

		List<Doctor> youngWomen       = filterYoungWomen(all);
		Console.print("Dottoresse under 30:");
		for (Doctor d : youngWomen)
			Console.print(d.getFirstName() + " " + d.getLastName());

	}

	/**
	 * Calcola il numero di dottori uomini nella lista.
	 *
	 * @param doctors Lista di dottori
	 * @return Numero di dottori uomini
	 */
	public static int countMen(List<Doctor> doctors)
	{
		int nMen = 0;
		for (Doctor d : doctors)
			if (d.getGender() == Gender.M)
				nMen++;
		return nMen;
	}

	/**
	 * Calcola il numero di dottori donne nella lista.
	 *
	 * @param doctors Lista di dottori
	 * @return Numero di dottori donne
	 */
	public static int countWomen(List<Doctor> doctors)
	{
		int nWomen = 0;
		for (Doctor d : doctors)
			if (d.getGender() == Gender.F)
				nWomen++;
		return nWomen;
	}

	/**
	 * Calcola il numero di dottori non binari nella lista.
	 *
	 * @param doctors Lista di dottori
	 * @return Numero di dottori non binari
	 */
	public static int countNonBinary(List<Doctor> doctors)
	{
		int nNonBinary = 0;
		for (Doctor d : doctors)
			if (d.getGender() == Gender.N)
				nNonBinary++;
		return nNonBinary;
	}

	/**
	 * Calcola l'età media dei dottori nella lista.
	 *
	 * @param doctors Lista di dottori
	 * @return Età media
	 */
	public static int calculateAverageAge(List<Doctor> doctors)
	{
		int sum = 0;
		for (Doctor d : doctors)
			sum += d.getAge();
		return sum / doctors.size();
	}

	/**
	 * Estrae tutte le dottoresse dalla lista.
	 *
	 * @param doctors Lista di dottori
	 * @return Lista di dottoresse
	 */
	public static List<Doctor> filterWomen(List<Doctor> doctors)
	{
		List<Doctor> women = new ArrayList<>();
		for (Doctor d : doctors)
			if (d.getGender() == Gender.F)
				women.add(d);
		return women;
	}

	/**
	 * Calcola la somma totale di tutti i salari.
	 *
	 * @param doctors Lista di dottori
	 * @return Somma totale salari
	 */
	public static double calculateTotalSalary(List<Doctor> doctors)
	{
		double sum = 0.0;
		for (Doctor d : doctors)
			sum += d.getSalary();
		return sum;
	}

	/**
	 * Calcola la somma dei salari dei dottori uomini.
	 *
	 * @param doctors Lista di dottori
	 * @return Somma salari uomini
	 */
	public static double calculateMenSalary(List<Doctor> doctors)
	{
		double sum = 0.0;
		for (Doctor d : doctors)
			if (d.getGender() == Gender.M)
				sum += d.getSalary();
		return sum;
	}

	/**
	 * Calcola la somma dei salari delle dottoresse.
	 *
	 * @param doctors Lista di dottori
	 * @return Somma salari donne
	 */
	public static double calculateWomenSalary(List<Doctor> doctors)
	{
		double sum = 0.0;
		for (Doctor d : doctors)
			if (d.getGender() == Gender.F)
				sum += d.getSalary();
		return sum;
	}

	/**
	 * Calcola il gender gap percentuale tra salari medi di uomini e donne.
	 *
	 * @param menSalary Somma salari uomini
	 * @param womenSalary Somma salari donne
	 * @param nMen Numero uomini
	 * @param nWomen Numero donne
	 * @return Gender gap in percentuale
	 */
	public static double calculateGenderGap(double menSalary, double womenSalary, int nMen, int nWomen)
	{
		double salarioMedioUomini = menSalary / nMen;
		double salarioMedioDonne  = womenSalary / nWomen;
		double differenza         = salarioMedioUomini - salarioMedioDonne;
		double percentuale        = (differenza / salarioMedioUomini) * 100;
		return percentuale;
	}

	/**
	 * Genera lista di buste paga con nome e salario del dottore.
	 *
	 * @param doctors Lista di dottori
	 * @return Lista di stringhe con le buste paga
	 */
	public static List<String> generatePayslips(List<Doctor> doctors)
	{
		List<String> payslips = new ArrayList<>();

		for (Doctor d : doctors)
		{
			String payslip = "Dr. " + d.getFirstName() + " " + d.getLastName() + " - Salario: €" + d.getSalary();
			payslips.add(payslip);
		}
		return payslips;
	}

	/**
	 * Conta quanti dottori sono cardiologi.
	 *
	 * @param doctors Lista di dottori
	 * @return Numero di cardiologi
	 */
	public static int countCardiologists(List<Doctor> doctors)
	{
		int count = 0;
		for (Doctor d : doctors)
			if (d.getSpecialties().contains(Specialty.CARDIOLOGY))
				count++;
		return count;
	}

	/**
	 * Conta quanti dottori sono psichiatri.
	 *
	 * @param doctors Lista di dottori
	 * @return Numero di psichiatri
	 */
	public static int countPsychiatrists(List<Doctor> doctors)
	{
		int count = 0;
		for (Doctor d : doctors)
			if (d.getSpecialties().contains(Specialty.PSYCHIATRY))
				count++;
		return count;
	}

	/**
	 * Estrae dottoresse con meno di 30 anni.
	 *
	 * @param doctors Lista di dottori
	 * @return Lista di dottoresse under 30
	 */
	public static List<Doctor> filterYoungWomen(List<Doctor> doctors)
	{
		List<Doctor> youngWomen = new ArrayList<>();
		for (Doctor d : doctors)
			if (d.getGender() == Gender.F && d.getAge() < 30)
				youngWomen.add(d);
		return youngWomen;
	}

}
