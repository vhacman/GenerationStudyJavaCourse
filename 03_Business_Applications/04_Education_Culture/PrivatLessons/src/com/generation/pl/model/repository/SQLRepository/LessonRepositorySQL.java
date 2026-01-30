package com.generation.pl.model.repository.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.generation.context.Context;
import com.generation.library.repository.SQLEntityRepository;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.StudentRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;

/**
 * Implementazione SQL del repository per la gestione delle lezioni.
 * Estende SQLEntityRepository per ereditare le operazioni CRUD di base.
 */
public class LessonRepositorySQL extends SQLEntityRepository<Lesson> implements LessonRepository 
{
	/**
	 * Costruttore del repository.
	 * 
	 * @param table Nome della tabella nel database (es. "lesson")
	 * @param connection Connessione al database da utilizzare
	 */
	public LessonRepositorySQL(String table, Connection connection) 
	{
		super(table, connection);
	}

	/**
	 * Prepara il comando SQL per l'inserimento di una nuova lezione.
	 * Salva i riferimenti agli ID dello studente e dell'insegnante.
	 * 
	 * @param x L'oggetto Lesson da inserire
	 * @return PreparedStatement configurato per l'inserimento
	 * @throws SQLException Se si verifica un errore nella preparazione del comando
	 */
	@Override
	protected PreparedStatement 	getInsertCmd(Lesson x) throws SQLException 
	{
		String 				sql = "INSERT INTO lesson (date, hour, studentid, teacherid, price) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement 	ps = connection.prepareStatement(sql);
		
		ps.setString(1, x.getDate().toString());
		ps.setInt	(2, x.getHour());
		ps.setInt	(3, x.getStudent().getId());
		ps.setInt	(4, x.getTeacher().getId());
		ps.setInt	(5, x.getPrice());		
		return ps;
	}

	/**
	 * Prepara il comando SQL per l'aggiornamento di una lezione esistente.
	 * 
	 * @param newVersion L'oggetto Lesson con i dati aggiornati
	 * @return PreparedStatement configurato per l'aggiornamento
	 * @throws SQLException Se si verifica un errore nella preparazione del comando
	 */
	@Override
	protected PreparedStatement 	getUpdateCmd(Lesson newVersion) throws SQLException 
	{
		String 					sql = "UPDATE lesson SET date=?, hour=?, studentid=?, teacherid=?, price=? WHERE id=?";
		PreparedStatement 		ps = connection.prepareStatement(sql);
		
		ps.setString(1, newVersion.getDate().toString());
		ps.setInt	(2, newVersion.getHour());
		ps.setInt	(3, newVersion.getStudent().getId());
		ps.setInt	(4, newVersion.getTeacher().getId());
		ps.setInt	(5, newVersion.getPrice());
		ps.setInt	(6, newVersion.getId());
		
		return ps;
	}

	/**
	 * Converte una riga del ResultSet in un oggetto Lesson.
	 * Carica automaticamente lo studente e l'insegnante associati
	 * tramite i rispettivi repository recuperati dal Context.
	 * @param rows ResultSet posizionato sulla riga da convertire
	 * @return L'oggetto Lesson costruito dai dati della riga
	 * @throws SQLException Se si verifica un errore durante la lettura dei dati
	 */
	@Override
	public Lesson rowToX(ResultSet rows) throws SQLException 
	{
		StudentRepository studentRepo = Context.getDependency(StudentRepository.class);
		TeacherRepository teacherRepo = Context.getDependency(TeacherRepository.class);
	    Lesson lesson = new Lesson();
	    
	    int 	studentId = rows.getInt("studentid");
	    int 	teacherId = rows.getInt("teacherid");
	    lesson.setId		(rows.getInt("id"));
	    lesson.setPrice		(rows.getInt("price"));	    
	    lesson.setDate		(rows.getString("date"));	   
	    lesson.setHour		(rows.getInt("hour"));	    
	    lesson.setStudent	(studentRepo.findById(studentId, false));	    
	    lesson.setTeacher	(teacherRepo.findById(teacherId, false));
	    
	    return lesson;
	}

	/**
	 * Calcola il guadagno totale di un teacher negli ultimi 30 giorni.
	 * Usa il prezzo salvato nella lezione (non quello attuale del teacher).
	 *
	 * @param teacherId ID del teacher
	 * @return Totale guadagni in euro (0 se nessuna lezione)
	 * @throws SQLException se si verifica un errore nel database
	 */
	@Override
	public int calculateEarningsByTeacherLast30Days(int teacherId) throws SQLException
	{
	    // Calcola la data limite (30 giorni fa)
	    LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
	    // Recupera tutte le lezioni del teacher
	    List<Lesson> lessons = findWhere("teacherid = " + teacherId);
	    // Variabile per accumulare il totale
	    int total = 0;
	    // Scorro tutte le lezioni
	    for (Lesson lesson : lessons)
	    {
	        // Controllo se la data della lezione è negli ultimi 30 giorni
	        LocalDate lessonDate = lesson.getDate();
	        // Se la data è >= 30 giorni fa, aggiungo il prezzo DELLA LEZIONE
	        // (non quello attuale del teacher, che potrebbe essere cambiato)
	        if (lessonDate.isAfter(thirtyDaysAgo) || lessonDate.isEqual(thirtyDaysAgo))
	            total += lesson.getPrice();
	    }
	    return total;
	}

	
	// TODO: RICONTROLLARE PERCHE MI SA VOLEVA LE MAPPE
	/**
	 * Calcola il guadagno totale per una materia negli ultimi 30 giorni.
	 * Usa il prezzo salvato nella lezione.
	 *
	 * @param subject Nome della materia
	 * @return Totale guadagni in euro (0 se nessuna lezione)
	 * @throws SQLException se si verifica un errore nel database
	 */
	@Override
	public int calculateEarningsBySubjectLast30Days(String subject) throws SQLException
	{
	    // Calcola la data limite (30 giorni fa)
	    LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
	    // Recupera repository necessari
	    TeacherRepository teacherRepo = Context.getDependency(TeacherRepository.class);
	    // Recupera tutti i teacher che insegnano questa materia
	    List<Teacher> teachers = teacherRepo.findAll();
	    // Variabile per accumulare il totale
	    int total = 0;
	    // Per ogni teacher che insegna la materia
	    for (Teacher teacher : teachers)
	    {
	        // Controllo se il teacher insegna la materia richiesta
	        // Uso split per evitare falsi positivi (es. "Math" vs "Mathematics")
	        String[] subjects = teacher.getSubjectsCSV().split(",");
	        boolean teachesSubject = false;
	        for (String s : subjects)
	        {
	            if (s.trim().equalsIgnoreCase(subject.trim()))
	            {
	                teachesSubject = true;
	                break;
	            }
	        }
	        if (teachesSubject)
	        {
	            // Recupera le lezioni di questo teacher
	            List<Lesson> lessons = findWhere("teacherid = " + teacher.getId());
	            for (Lesson lesson : lessons)	            
	            {
	                LocalDate lessonDate = lesson.getDate();
	                if (lessonDate.isAfter(thirtyDaysAgo) || lessonDate.isEqual(thirtyDaysAgo))
	                    total += lesson.getPrice();
	            }
	        }
	    }
	    return total;
	}	
}