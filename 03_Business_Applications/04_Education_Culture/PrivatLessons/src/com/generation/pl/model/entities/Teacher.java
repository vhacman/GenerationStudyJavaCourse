package com.generation.pl.model.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Notate che non mi chiede getErrors(), c'era in User...
 * ma ne parleremo...
 * Un Teacher è una tipologia di utente
 */
public class Teacher extends User
{
	private final static int 	MINPRICE = 20;
	String						bio;
	int							pricePerLesson;
	Set<Subject>				subjects = new HashSet<Subject>();
	List<Lesson>				lessons = new ArrayList<Lesson>();

	/**
	 * salverò sul db e sul file le mie materie come una stringa separata da virgola
	 * esempio. JAVA,SQL
	 * @param subjectsCSV
	 */
	public void 	setSubjects(String subjectsCSV) throws IllegalArgumentException
	{
		String[] subjectsArray = subjectsCSV.split(",");
		for(String subject:subjectsArray)
			this.subjects.add(Subject.valueOf(subject));
	}
	
	public void 	addSubject(String subject) throws IllegalArgumentException
	{
		this.subjects.add(Subject.valueOf(subject));
	}
	
	public void 	removeSubject(String subject) throws IllegalArgumentException
	{
		this.subjects.remove(Subject.valueOf(subject));
	}
	
	
	
	@Override
	public List<String> getErrors()
	{
		List<String> res = super.getErrors();		
		if(isMissing(bio))
			res.add("Missing value for bio");
		if(subjects.size()==0)
			res.add("Insert at least a subject");
		if(pricePerLesson< MINPRICE)
			res.add("Invalid price");		
		return res;
	}
	
	/**
	 * VOGLIO PRODURRE UNA STRINGA DI MATERIE IN FORMATO CSV
	 */
	public String getSubjectsCSV()
	{
		String res = "";
		for(Subject s : subjects)
			res += s + ",";
		return res.substring(0, res.length()-1);
	}

	public String		getBio()								{ return bio; }
	public int			getPricePerLesson()						{ return pricePerLesson; }
	public Set<Subject>	getSubjects()							{ return subjects; }
	public List<Lesson>	getLessons()							{ return lessons; }

	public void			setBio(String bio)						{ this.bio = bio; }
	public void			setPricePerLesson(int pricePerLesson)	{ this.pricePerLesson = pricePerLesson; }
	public void			setSubjects(Set<Subject> subjects)		{ this.subjects = subjects; }
	public void			setLessons(List<Lesson> lessons)		{ this.lessons = lessons; }
}
