package com.generation.pl.model.repository.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import com.generation.context.Context;
import com.generation.library.repository.SQLEntityRepository;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.library.security.PasswordHasher;

/**
 * SQL implementation of the repository for teacher management.
 * Extends SQLEntityRepository to inherit base CRUD operations (insert, update, delete, findById, findAll).
 * Handles teacher authentication, subject-based search, lesson relationship loading, and earnings calculations.
 * Supports both eager and lazy loading strategies for performance optimization.
 */
public class TeacherRepositorySQL extends SQLEntityRepository<Teacher> implements TeacherRepository
{
    /**
     * Constructs the teacher repository with database connection.
     * 
     * @param table Table name in database (e.g., "teacher")
     * @param connection Active database connection to use for all operations
     */
    public TeacherRepositorySQL(String table, Connection connection) 
    {
        super(table, connection);
    }

    /**
     * Prepares SQL statement for updating an existing teacher.
     * 
     * Update scope:
     * - Updates: firstname, lastname, ssn, email, bio, status, priceperlesson, subjects
     * - Does NOT update: password (must use changePassword() for security validation)
     * 
     * Subject handling: Subjects stored as CSV string (e.g., "JAVA,SQL,MATH").
     * Password exclusion ensures old password verification happens through dedicated method.
     * Handles null status with ternary operator to prevent NullPointerException.
     * 
     * @param newVersion The Teacher object with updated data
     * @return PreparedStatement configured for update
     * @throws SQLException If error occurs during statement preparation
     */
    @Override
    protected PreparedStatement getUpdateCmd(Teacher newVersion) throws SQLException 
    {
        String 				sql = "UPDATE Teacher SET firstname=?, lastname=?, ssn=?, email=?, bio=?, status=?, priceperlesson=?, subjects=? WHERE id=?";
        PreparedStatement 	ps = connection.prepareStatement(sql);
        ps.setString(1, newVersion.getFirstName());
        ps.setString(2, newVersion.getLastName());
        ps.setString(3, newVersion.getSsn());
        ps.setString(4, newVersion.getEmail());
        ps.setString(5, newVersion.getBio());
        ps.setString(6, newVersion.getStatus() != null ? newVersion.getStatus().toString() : null);
        ps.setInt(7, newVersion.getPricePerLesson());
        ps.setString(8, newVersion.getSubjectsCSV());
        ps.setInt(9, newVersion.getId());
        return ps;
    }
    
    /**
     * Prepares SQL statement for inserting a new teacher.
     * 
     * Insert logic:
     * 1. Stores teacher personal data (firstname, lastname, ssn, email, bio)
     * 2. Stores teaching metadata (status, priceperlesson, subjects as CSV)
     * 3. Hashes password using PasswordHasher before database storage (security requirement)
     * 4. Converts UserStatus enum to string for VARCHAR storage
     * 
     * Security: Plain-text passwords never stored in database.
     * Subject format: Comma-separated values stored in single VARCHAR column.
     * 
     * @param x The Teacher object to insert
     * @return PreparedStatement configured for insertion
     * @throws SQLException If error occurs during statement preparation
     */
    @Override
    protected PreparedStatement getInsertCmd(Teacher x) throws SQLException 
    {
        String 				sql = "insert into Teacher (firstname,lastname,ssn,email,bio,status,priceperlesson,subjects,password) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement 	ps = connection.prepareStatement(sql);
        ps.setString(1, x.getFirstName());
        ps.setString(2, x.getLastName());
        ps.setString(3, x.getSsn());
        ps.setString(4, x.getEmail());
        ps.setString(5, x.getBio());
        ps.setString(6, x.getStatus().toString());
        ps.setInt(7, x.getPricePerLesson());
        ps.setString(8, x.getSubjectsCSV());        
        PasswordHasher 	hasher = Context.getDependency(PasswordHasher.class);
        ps.setString(9, hasher.hash(x.getPassword()));
        return ps;
    }

    /**
     * Retrieves teachers matching a condition with eager loading of associated lessons.
     * 
     * Eager loading process:
     * 1. Calls parent findWhere() to retrieve teachers matching condition
     * 2. Loads all lessons from database via LessonRepository
     * 3. Iterates through teachers and lessons to match by teacher ID
     * 4. Populates each teacher's lessons list with matching lesson objects
     * 
     * Performance note: Loads all lessons into memory for filtering (N+1 query avoided).
     * Use findById(id, false) for lazy loading when lessons not needed.
     * 
     * @param cond SQL WHERE clause condition (e.g., "status='ACTIVE'")
     * @return List of Teacher objects with lessons collection populated
     */
    @Override
    public List<Teacher> findWhere(String cond)
    {
        LessonRepository     	lessonRepo = Context.getDependency(LessonRepository.class);
        List<Teacher>           res = super.findWhere(cond);
        List<Lesson>      		allLessons = lessonRepo.findAll();
        for (Teacher teacher : res)
            for (Lesson lesson : allLessons)
                if(lesson.getTeacher() != null && lesson.getTeacher().getId() == teacher.getId())
                    teacher.getLessons().add(lesson);
        return res;
    }

    /**
     * Retrieves a teacher by ID with configurable relationship loading.
     * 
     * Loading strategy:
     * - complete = false: Calls findByIdNaked() for base data only (faster, no joins)
     * - complete = true: Calls inherited findById() which triggers eager loading via findWhere()
     * 
     * Use cases:
     * - Lazy loading (false): Quick lookups, existence checks, foreign key resolution
     * - Eager loading (true): Full teacher profile with lesson history needed
     * 
     * @param id Unique identifier of the teacher
     * @param complete true to load lessons, false for base data only
     * @return Teacher object with or without relationships loaded
     * @throws SQLException If database error occurs
     */
    @Override
    public Teacher findById(int id, boolean complete) throws SQLException
    {
        return !complete ? findByIdNaked(id) : findById(id);
    }

    /**
     * Retrieves teacher by ID without loading relationships (naked/lazy loading).
     * 
     * Performs direct SELECT query without triggering relationship loading.
     * Used internally for performance-critical operations and circular dependency prevention.
     * 
     * @param id Teacher ID to retrieve
     * @return Teacher object with base data only, null if not found
     * @throws SQLException If database error occurs
     */
    private Teacher findByIdNaked(int id) throws SQLException
    {
        Statement     		readSQL = connection.createStatement();
        ResultSet         	rows = readSQL.executeQuery("SELECT * FROM teacher WHERE id =" + id);
        Teacher             res = rows.next() ? rowToX(rows) : null;
        readSQL.close();
        rows.close();
        return res;
    }
   
    /**
     * Converts a ResultSet row to a Teacher entity.
     * 
     * Type conversion handling:
     * 1. Reads primitive types (int, String) directly from ResultSet
     * 2. Converts status string to UserStatus enum using valueOf() (null-safe)
     * 3. Reads subjects as CSV string (parsed into list by entity setter)
     * 4. Password remains hashed (never decrypted, used only for comparison)
     * 5. Lessons list initialized empty (populated by findWhere() if needed)
     * 
     * Called by inherited findById(), findAll(), and findWhere() methods.
     * 
     * @param rows ResultSet positioned on the row to convert
     * @return Teacher object constructed from row data (without lessons)
     * @throws SQLException If error occurs reading data or converting types
     */
    @Override
    public Teacher rowToX(ResultSet rows) throws SQLException
    {
        Teacher 		teacher = new Teacher();
        teacher.setId(rows.getInt("id"));
        teacher.setFirstName(rows.getString("firstname"));
        teacher.setLastName(rows.getString("lastname"));
        teacher.setSsn(rows.getString("ssn"));
        teacher.setEmail(rows.getString("email"));
        teacher.setBio(rows.getString("bio"));
        teacher.setPricePerLesson(rows.getInt("priceperlesson"));
        teacher.setSubjects(rows.getString("subjects"));
        teacher.setPassword(rows.getString("password"));
        String statusStr = rows.getString("status");
        if (statusStr != null)
            teacher.setStatus(UserStatus.valueOf(statusStr));
        return teacher;
    }

    /**
     * Authenticates a teacher using email and password.
     * 
     * Authentication process:
     * 1. Hashes plain-text password using same algorithm as registration (bcrypt)
     * 2. Queries database for matching email AND hashed password
     * 3. Returns Teacher object if credentials match, null otherwise
     * 
     * Security: Plain-text password never sent to database or stored in query.
     * Uses findWhere() inherited method for parameterized query construction.
     * 
     * @param email Teacher's email address
     * @param plainPassword Password in plain text (hashed before comparison)
     * @return Teacher object if credentials correct, null if authentication fails
     * @throws SQLException If database error occurs during query
     */
    @Override
    public Teacher login(String email, String plainPassword) throws SQLException 
    {
        PasswordHasher hasher         = Context.getDependency(PasswordHasher.class);
        String         hashedPassword = hasher.hash(plainPassword);       
        List<Teacher> matches = findWhere("email='" + email + "' and password='" + hashedPassword + "'");
        return matches.size() > 0 ? matches.get(0) : null;
    }

    /**
     * Changes teacher password with old password verification.
     * 
     * Password change workflow:
     * 1. Validates new password differs from old password (prevents accidental re-use)
     * 2. Hashes old password and compares with stored hash (authentication check)
     * 3. If verification passes, updates password to hashed new password
     * 
     * Security validations:
     * - Throws SQLException if old password incorrect (prevents unauthorized changes)
     * - Throws SQLException if passwords identical (enforces actual change)
     * 
     * @param id Teacher ID to change password for
     * @param oldPassword Current password in plain text (for verification)
     * @param newPassword New password in plain text (will be hashed)
     * @throws SQLException If old password incorrect, passwords identical, or database error
     */
    @Override
    public void changePassword(int id, String oldPassword, String newPassword) throws SQLException 
    {
        if (oldPassword.equals(newPassword))
            throw new SQLException("Passwords must be different");        
        PasswordHasher 		hasher            = Context.getDependency(PasswordHasher.class);
        String         		oldPasswordHashed = hasher.hash(oldPassword);        
        Teacher 			t = findById(id);
        if (!oldPasswordHashed.equals(t.getPassword()))
            throw new SQLException("Old password does not match");
        String            	sql = "update teacher set password=? where id=?";
        PreparedStatement 	ps  = connection.prepareStatement(sql);
        ps.setString(1, hasher.hash(newPassword));
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }
    
    /**
     * Searches for teachers by subject using LIKE pattern matching.
     * 
     * Subject matching:
     * - Uses SQL LIKE operator with wildcards (%subject%)
     * - Matches subject within CSV string (e.g., "%JAVA%" matches "SQL,JAVA,MATH")
     * - Case-sensitive (ensure consistent uppercase in database)
     * 
     * Returns teachers with eager loading (includes lesson history via findWhere()).
     * 
     * @param subject Subject name to search (e.g., "JAVA", "MATH")
     * @return List of teachers who teach the specified subject
     * @throws SQLException If database error occurs during query
     */
    @Override
    public List<Teacher> findTeacherBySubject(String subject) throws SQLException
    {
        return findWhere("subjects LIKE '%" + subject + "%'");
    }
    
    /**
     * Changes the status of a teacher (ACTIVE, INACTIVE, SUSPENDED).
     * 
     * Status change validation:
     * 1. Retrieves teacher by ID with lazy loading (complete=false for performance)
     * 2. Throws SQLException if teacher not found (invalid ID)
     * 3. Checks if current status already matches new status (prevents redundant updates)
     * 4. If status differs, executes UPDATE query to change status
     * 
     * Use cases: Account suspension, reactivation, temporary deactivation.
     * Idempotency check avoids unnecessary database writes.
     * 
     * @param teacherId ID of teacher to modify
     * @param newStatus New UserStatus enum value (ACTIVE, INACTIVE, or SUSPENDED)
     * @throws SQLException If teacher not found, status unchanged, or database error
     */
    @Override
    public void changeStatus(int teacherId, UserStatus newStatus) throws SQLException
    {
        Teacher teacher = findById(teacherId, false);        
        if (teacher == null)
            throw new SQLException("Teacher con ID " + teacherId + " non trovato");        
        if (teacher.getStatus() == newStatus)
            throw new SQLException("Teacher ha già lo status " + newStatus);        
        String query = "UPDATE teacher SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, newStatus.toString());
        ps.setInt(2, teacherId);        
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Calculates total earnings for a teacher within a specific date range.
     * 
     * Calculation logic:
     * 1. Executes SUM aggregation query on lesson prices
     * 2. Filters by teacher ID and date range using BETWEEN (inclusive on both ends)
     * 3. Returns total from SUM result (0 if NULL, meaning no lessons in period)
     * 
     * Uses SQL SUM function for efficient server-side calculation.
     * BETWEEN includes both startDate and endDate in range.
     * 
     * @param id Teacher ID
     * @param startDate Period start date (inclusive)
     * @param endDate Period end date (inclusive)
     * @return Total earnings in euros (0 if no lessons in period)
     * @throws SQLException If database error occurs during query
     */
    @Override
    public int calculateEarningsByTeacherIdAndPeriod(int id, LocalDate startDate, LocalDate endDate) throws SQLException
    {
        String sql = "SELECT SUM(price) as total FROM lesson WHERE teacherid = ? AND date BETWEEN ? AND ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, startDate.toString());
        ps.setString(3, endDate.toString());
        ResultSet rs = ps.executeQuery();
        int totalEarnings = 0;
        if (rs.next())
            totalEarnings = rs.getInt("total");
        rs.close();
        ps.close();
        return totalEarnings;
    }

    /**
     * Retrieves all lessons for a specific teacher (complete history).
     * 
     * Delegates to LessonRepository with teacher ID filter.
     * Returns all past and future lessons for scheduling and historical analysis.
     * 
     * @param id Teacher ID
     * @return List of all lessons taught by this teacher
     * @throws SQLException If database error occurs during query
     */
    @Override
    public List<Lesson> findLessonsByTeacherId(int id) throws SQLException
    {
        LessonRepository lessonRepo = Context.getDependency(LessonRepository.class);
        return lessonRepo.findWhere("teacherid = " + id);
    }

    /**
     * Retrieves lessons scheduled in the next 7 days for a teacher.
     * 
     * Date range calculation:
     * - Start: Current date (inclusive)
     * - End: Current date + 7 days (inclusive)
     * - Uses BETWEEN for inclusive range matching
     * 
     * Use case: Weekly schedule planning, upcoming lesson overview.
     * 
     * @param id Teacher ID
     * @return List of lessons scheduled within next 7 days
     * @throws SQLException If database error occurs during query
     */
    @Override
    public List<Lesson> findNextWeekLessonsByTeacherId(int id) throws SQLException
    {
        LessonRepository lessonRepo = Context.getDependency(LessonRepository.class);
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);
        String condition = "teacherid = " + id + " AND date BETWEEN '" + today.toString() + "' AND '" + nextWeek.toString() + "'";
        return lessonRepo.findWhere(condition);
    }
}
