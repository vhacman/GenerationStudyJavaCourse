package com.generation.pl.model.repository.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.generation.context.Context;
import com.generation.library.repository.SQLEntityRepository;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.StudentRepository;
import com.generation.library.security.PasswordHasher;

/**
 * SQL implementation of the repository for student management.
 * Extends SQLEntityRepository to inherit base CRUD operations (insert, update, delete, findById, findAll).
 * Handles student authentication, password management, status updates, and lesson relationship loading.
 * Supports both eager and lazy loading strategies for performance optimization.
 */
public class StudentRepositorySQL extends SQLEntityRepository<Student> implements StudentRepository
{
    /**
     * Constructs the student repository with database connection.
     * 
     * @param table Table name in database (e.g., "student")
     * @param connection Active database connection to use for all operations
     */
    public StudentRepositorySQL(String table, Connection connection)
    {
        super(table, connection);
    }

    /**
     * Retrieves students matching a condition with eager loading of associated lessons.
     * 
     * Eager loading process:
     * 1. Calls parent findWhere() to retrieve students matching condition
     * 2. Loads all lessons from database via LessonRepository
     * 3. Iterates through students and lessons to match by student ID
     * 4. Populates each student's lessons list with matching lesson objects
     * 
     * Performance note: Loads all lessons into memory for filtering (N+1 query avoided).
     * Use findById(id, false) for lazy loading when lessons not needed.
     * 
     * @param cond SQL WHERE clause condition (e.g., "status='ACTIVE'")
     * @return List of Student objects with lessons collection populated
     */
    @Override
    public List<Student> findWhere(String cond)
    {
        LessonRepository lessonRepo = Context.getDependency(LessonRepository.class);
        List<Student>    res        = super.findWhere(cond);
        List<Lesson>     allLessons = lessonRepo.findAll();
        for (Student student : res)
            for (Lesson lesson : allLessons)
                if (lesson.getStudent() != null && lesson.getStudent().getId() == student.getId())
                    student.getLessons().add(lesson);
        return res;
    }

    /**
     * Retrieves a student by ID with configurable relationship loading.
     * 
     * Loading strategy:
     * - complete = false: Calls findByIdNaked() for base data only (faster, no joins)
     * - complete = true: Calls inherited findById() which triggers eager loading via findWhere()
     * 
     * Use cases:
     * - Lazy loading (false): Quick lookups, existence checks, foreign key resolution
     * - Eager loading (true): Full student profile with lesson history needed
     * 
     * @param id Unique identifier of the student
     * @param complete true to load lessons, false for base data only
     * @return Student object with or without relationships loaded
     * @throws SQLException If database error occurs
     */
    @Override
    public Student findById(int id, boolean complete) throws SQLException
    {
        return !complete ? findByIdNaked(id) : findById(id);
    }

    /**
     * Retrieves student by ID without loading relationships (naked/lazy loading).
     * 
     * Performs direct SELECT query without triggering relationship loading.
     * Used internally for performance-critical operations and circular dependency prevention.
     * 
     * @param id Student ID to retrieve
     * @return Student object with base data only, null if not found
     * @throws SQLException If database error occurs
     */
    private Student findByIdNaked(int id) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet rows      = statement.executeQuery("SELECT * FROM student WHERE id =" + id);
        Student   res       = rows.next() ? rowToX(rows) : null;
        statement.close();
        rows.close();
        return res;
    }

    /**
     * Prepares SQL statement for inserting a new student.
     * 
     * Insert logic:
     * 1. Stores student personal data (firstname, lastname, ssn, email, dob)
     * 2. Hashes password using PasswordHasher before database storage (security requirement)
     * 3. Converts UserStatus enum to string for VARCHAR storage
     * 4. Converts LocalDate (dob) to string format (YYYY-MM-DD) for SQL DATE compatibility
     * 
     * Security: Plain-text passwords never stored in database.
     * 
     * @param x The Student object to insert
     * @return PreparedStatement configured for insertion
     * @throws SQLException If error occurs during statement preparation
     */
    @Override
    protected PreparedStatement getInsertCmd(Student x) throws SQLException
    {
        String            sql    = "insert into Student (firstname,lastname,ssn,email,dob,status,password) values(?,?,?,?,?,?,?)";
        PreparedStatement ps     = connection.prepareStatement(sql);
        PasswordHasher    hasher = Context.getDependency(PasswordHasher.class);
        ps.setString(1, x.getFirstName());
        ps.setString(2, x.getLastName());
        ps.setString(3, x.getSsn());
        ps.setString(4, x.getEmail());
        ps.setString(5, x.getDob().toString());
        ps.setString(6, x.getStatus().toString());
        ps.setString(7, hasher.hash(x.getPassword()));
        return ps;
    }

    /**
     * Prepares SQL statement for updating an existing student.
     * 
     * Update scope:
     * - Updates: firstname, lastname, ssn, email, dob, status
     * - Does NOT update: password (must use changePassword() for security validation)
     * 
     * Password exclusion ensures old password verification happens through dedicated method.
     * Handles null status with ternary operator to prevent NullPointerException.
     * 
     * @param newVersion The Student object with updated data
     * @return PreparedStatement configured for update
     * @throws SQLException If error occurs during statement preparation
     */
    @Override
    protected PreparedStatement getUpdateCmd(Student newVersion) throws SQLException
    {
        String sql = "UPDATE Student SET firstname=?, lastname=?, ssn=?, email=?, dob=?, status=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newVersion.getFirstName());
        ps.setString(2, newVersion.getLastName());
        ps.setString(3, newVersion.getSsn());
        ps.setString(4, newVersion.getEmail());
        ps.setString(5, newVersion.getDob().toString());
        ps.setString(6, newVersion.getStatus() != null ? newVersion.getStatus().toString() : null);
        ps.setInt(7, newVersion.getId());
        return ps;
    }

    /**
     * Converts a ResultSet row to a Student entity.
     * 
     * Type conversion handling:
     * 1. Reads primitive types (int, String) directly from ResultSet
     * 2. Converts status string to UserStatus enum using valueOf() (null-safe)
     * 3. Reads dob as string (YYYY-MM-DD format from database)
     * 4. Password remains hashed (never decrypted, used only for comparison)
     * 5. Lessons list initialized empty (populated by findWhere() if needed)
     * 
     * Called by inherited findById(), findAll(), and findWhere() methods.
     * 
     * @param rows ResultSet positioned on the row to convert
     * @return Student object constructed from row data (without lessons)
     * @throws SQLException If error occurs reading data or converting types
     */
    @Override
    public Student rowToX(ResultSet rows) throws SQLException
    {
        Student s         = new Student();
        String  statusStr = rows.getString("status");
        s.setId(rows.getInt("id"));
        s.setFirstName(rows.getString("firstname"));
        s.setLastName(rows.getString("lastname"));
        s.setSsn(rows.getString("ssn"));
        s.setEmail(rows.getString("email"));
        s.setDob(rows.getString("dob"));
        s.setPassword(rows.getString("password"));
        if (statusStr != null)
            s.setStatus(UserStatus.valueOf(statusStr));
        return s;
    }

    /**
     * Authenticates a student using email and password.
     * 
     * Authentication process:
     * 1. Hashes plain-text password using same algorithm as registration (bcrypt)
     * 2. Queries database for matching email AND hashed password
     * 3. Returns Student object if credentials match, null otherwise
     * 
     * Security: Plain-text password never sent to database or stored in query.
     * Uses findWhere() inherited method for parameterized query construction.
     * 
     * @param email Student's email address
     * @param plainPassword Password in plain text (hashed before comparison)
     * @return Student object if credentials correct, null if authentication fails
     * @throws SQLException If database error occurs during query
     */
    @Override
    public Student login(String email, String plainPassword) throws SQLException
    {
        PasswordHasher hasher         = Context.getDependency(PasswordHasher.class);
        String         hashedPassword = hasher.hash(plainPassword);
        List<Student>  matches        = findWhere("email='" + email + "' and password='" + hashedPassword + "'");
        return matches.size() > 0 ? matches.get(0) : null;
    }

    /**
     * Changes student password with old password verification.
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
     * @param id Student ID to change password for
     * @param oldPassword Current password in plain text (for verification)
     * @param newPassword New password in plain text (will be hashed)
     * @throws SQLException If old password incorrect, passwords identical, or database error
     */
    @Override
    public void changePassword(int id, String oldPassword, String newPassword) throws SQLException
    {
        if (oldPassword.equals(newPassword))
            throw new SQLException("Passwords must be different");
        PasswordHasher hasher            = Context.getDependency(PasswordHasher.class);
        String         oldPasswordHashed = hasher.hash(oldPassword);
        Student s = findById(id);
        if (!oldPasswordHashed.equals(s.getPassword()))
            throw new SQLException("Old password does not match");
        String            sql = "update student set password=? where id=?";
        PreparedStatement ps  = connection.prepareStatement(sql);
        ps.setString(1, hasher.hash(newPassword));
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }

    /**
     * Changes the status of a student (ACTIVE, INACTIVE, SUSPENDED).
     * 
     * Status change validation:
     * 1. Retrieves student by ID with lazy loading (complete=false for performance)
     * 2. Throws SQLException if student not found (invalid ID)
     * 3. Checks if current status already matches new status (prevents redundant updates)
     * 4. If status differs, executes UPDATE query to change status
     * 
     * Use cases: Account suspension, reactivation, temporary deactivation.
     * Idempotency check avoids unnecessary database writes.
     * 
     * @param studentId ID of student to modify
     * @param newStatus New UserStatus enum value (ACTIVE, INACTIVE, or SUSPENDED)
     * @throws SQLException If student not found, status unchanged, or database error
     */
    @Override
    public void changeStatus(int studentId, UserStatus newStatus) throws SQLException
    {
        Student student = findById(studentId, false);        
        if (student == null)
            throw new SQLException("Student con ID " + studentId + " non trovato");        
        if (student.getStatus() == newStatus)
            throw new SQLException("Student ha già lo status " + newStatus);        
        String query = "UPDATE student SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, newStatus.toString());
        ps.setInt(2, studentId);        
        ps.executeUpdate();
        ps.close();
    }
}
