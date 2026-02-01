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
 * SQL implementation of the repository for lesson management.
 * Extends SQLEntityRepository to inherit base CRUD operations (insert, update, delete, findById, findAll).
 * Handles lesson booking, entity relationship loading (Student, Teacher), and earnings calculations.
 * Manages foreign key references and date-based queries for scheduling and financial reporting.
 */
public class LessonRepositorySQL extends SQLEntityRepository<Lesson> implements LessonRepository 
{
	/**
	 * Constructs the lesson repository with database connection.
	 * 
	 * @param table Table name in database (e.g., "lesson")
	 * @param connection Active database connection to use for all operations
	 */
	public LessonRepositorySQL(String table, Connection connection) 
	{
		super(table, connection);
	}

	/**
	 * Prepares SQL statement for inserting a new lesson.
	 * 
	 * Foreign key handling: Only IDs stored in database; full entities loaded during retrieval via rowToX().
	 * 
	 * @param x The Lesson object to insert
	 * @return PreparedStatement configured for insertion
	 * @throws SQLException If error occurs during statement preparation
	 */
	@Override
	protected PreparedStatement getInsertCmd(Lesson x) throws SQLException 
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
	 * Prepares SQL statement for updating an existing lesson.
	 * 
	 * Update scope:
	 * - Updates: date, hour, studentid, teacherid, price
	 * - Uses ID in WHERE clause to target specific lesson record
	 * 
	 * All fields updatable to support rescheduling and reassignment scenarios.
	 * Price update allows manual corrections if needed.
	 * 
	 * @param newVersion The Lesson object with updated data
	 * @return PreparedStatement configured for update
	 * @throws SQLException If error occurs during statement preparation
	 */
	@Override
	protected PreparedStatement getUpdateCmd(Lesson newVersion) throws SQLException 
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
	 * Converts a ResultSet row to a Lesson entity with full relationship loading.
	 * 
	 * Entity hydration process:
	 * 1. Reads primitive fields (id, price, date, hour) from ResultSet
	 * 2. Extracts foreign key IDs (studentid, teacherid)
	 * 3. Loads full Student and Teacher entities via their respective repositories
	 * 4. Sets loaded entities on Lesson object (eager loading for complete object graph)
	 * 
	 * Repository injection: Uses Context.getDependency() to retrieve StudentRepository and TeacherRepository.
	 * Lazy loading flag: Passes false to prevent circular dependency issues during nested entity loading.
	 * 
	 * Called by inherited findById(), findAll(), and findWhere() methods.
	 * 
	 * @param rows ResultSet positioned on the row to convert
	 * @return Lesson object with fully loaded Student and Teacher entities
	 * @throws SQLException If error occurs reading data or loading related entities
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
	 * Calculates total earnings for a teacher in the last 30 days.
	 * 
	 * Calculation logic:
	 * 1. Computes date threshold (current date - 30 days)
	 * 2. Retrieves all lessons for specified teacher
	 * 3. Filters lessons with date >= threshold (includes today and 30 days back)
	 * 4. Sums lesson prices (uses historical price from lesson record, not current teacher rate)
	 * 
	 * Historical pricing: Uses price stored in lesson table (teacher's rate at booking time).
	 * This ensures earnings reflect actual transaction amounts, even if teacher changed rates.
	 * 
	 * @param teacherId ID of the teacher
	 * @return Total earnings in euros (0 if no lessons in period)
	 * @throws SQLException If database error occurs during query
	 */
	@Override
	public int calculateEarningsByTeacherLast30Days(int teacherId) throws SQLException
	{
	    LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
	    List<Lesson> lessons = findWhere("teacherid = " + teacherId);
	    int total = 0;
	    for (Lesson lesson : lessons)
	    {
	        LocalDate lessonDate = lesson.getDate();
	        if (lessonDate.isAfter(thirtyDaysAgo) || lessonDate.isEqual(thirtyDaysAgo))
	            total += lesson.getPrice();
	    }
	    return total;
	}

	/**
	 * Calculates total earnings for a specific subject in the last 30 days.
	 * 
	 * Multi-step calculation process:
	 * 1. Computes date threshold (current date - 30 days)
	 * 2. Retrieves all teachers from database
	 * 3. For each teacher, checks if they teach the specified subject
	 *    - Splits CSV subjects field (e.g., "JAVA,SQL,MATH")
	 *    - Uses case-insensitive comparison with trim to avoid false negatives
	 *    - Prevents partial matches (e.g., "MATH" won't match "MATHEMATICS")
	 * 4. For matching teachers, retrieves their lessons in date range
	 * 5. Sums lesson prices (historical rates from lesson records)
	 * 
	 * Subject matching: Split-based comparison ensures exact subject match within CSV list.
	 * Historical pricing: Uses lesson.price (rate at booking time), not teacher's current rate.
	 * 
	 * Performance note: Loads all teachers and filters in-memory (could be optimized with JOIN query).
	 * TODO: Consider using Map-based approach for better performance with large datasets.
	 * 
	 * @param subject Name of the subject (case-insensitive)
	 * @return Total earnings in euros (0 if no lessons for subject in period)
	 * @throws SQLException If database error occurs during query
	 */
	@Override
	public int calculateEarningsBySubjectLast30Days(String subject) throws SQLException
	{
	    LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
	    TeacherRepository teacherRepo = Context.getDependency(TeacherRepository.class);
	    List<Teacher> teachers = teacherRepo.findAll();
	    int total = 0;
	    for (Teacher teacher : teachers)
	    {
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
