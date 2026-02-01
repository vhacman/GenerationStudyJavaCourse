package com.generation.pl.model.repository.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.generation.context.Context;
import com.generation.library.repository.SQLEntityRepository;
import com.generation.pl.model.entities.Admin;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.model.repository.interfaces.AdminRepository;
import com.generation.library.security.PasswordHasher;

/**
 * SQL implementation of the repository for admin management.
 * Extends SQLEntityRepository to inherit base CRUD operations (insert, update, delete, findById, findAll).
 * Handles password hashing, authentication, status management, and date tracking for admin entities.
 * All password operations use PasswordHasher for secure bcrypt hashing.
 */
public class AdminRepositorySQL extends SQLEntityRepository<Admin> implements AdminRepository 
{
	/**
	 * Constructs the admin repository with database connection.
	 * 
	 * @param table Table name in database (e.g., "admin")
	 * @param connection Active database connection to use for all operations
	 */
	public AdminRepositorySQL(String table, Connection connection) 
	{
		super(table, connection);
	}

	/**
	 * Prepares SQL statement for inserting a new admin.
	 * 
	 * Insert logic:
	 * 1. Retrieves PasswordHasher from dependency injection context
	 * 2. Hashes plain-text password before database storage (security requirement)
	 * 3. Sets dateLastPasswordChange to current date if not specified
	 * 4. Converts UserStatus enum to string for VARCHAR storage
	 * 
	 * Security: Plain-text passwords never stored in database.
	 * Date handling: Uses setString() with LocalDate.toString() (YYYY-MM-DD format compatible with SQL DATE type).
	 * 
	 * @param x The Admin object to insert
	 * @return PreparedStatement configured for insertion
	 * @throws SQLException If error occurs during statement preparation
	 */
	@Override
	protected PreparedStatement getInsertCmd(Admin x) throws SQLException 
	{
		PasswordHasher 		hasher 	= Context.getDependency(PasswordHasher.class);
	    String 				sql 	= "INSERT INTO admin (firstname, lastname, ssn, email, password, status, datelastpasswordchange) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    PreparedStatement 	ps 		= connection.prepareStatement(sql);
	    ps.setString(1, x.getFirstName());
	    ps.setString(2, x.getLastName());
	    ps.setString(3, x.getSsn());
	    ps.setString(4, x.getEmail());
	    ps.setString(5, hasher.hash(x.getPassword()));
	    ps.setString(6, x.getStatus().toString());
	    if (x.getDateLastPasswordChange() == null)
	        ps.setString(7, LocalDate.now().toString());	    
	    else
	        ps.setString(7, x.getDateLastPasswordChange().toString());
	    return ps;
	}

	/**
	 * Prepares SQL statement for updating an existing admin.
	 * 
	 * Update scope:
	 * - Updates: firstname, lastname, ssn, email, status, dateLastPasswordChange
	 * - Does NOT update: password (must use changePassword() for security validation)
	 * 
	 * Password exclusion ensures old password verification happens through dedicated method.
	 * Uses ID in WHERE clause to target specific admin record.
	 * 
	 * @param newVersion The Admin object with updated data
	 * @return PreparedStatement configured for update
	 * @throws SQLException If error occurs during statement preparation
	 */
	@Override
	protected PreparedStatement getUpdateCmd(Admin newVersion) throws SQLException 
	{
	    String 				sql = "UPDATE admin SET firstname=?, lastname=?, ssn=?, email=?, status=?, datelastpasswordchange=? WHERE id=?";
	    PreparedStatement 	ps = connection.prepareStatement(sql);
	    ps.setString(1, newVersion.getFirstName());
	    ps.setString(2, newVersion.getLastName());
	    ps.setString(3, newVersion.getSsn());
	    ps.setString(4, newVersion.getEmail());
	    ps.setString(5, newVersion.getStatus().toString());
	    ps.setString(6, newVersion.getDateLastPasswordChange().toString());
	    ps.setInt	(7, newVersion.getId());
	    return ps;
	}

	/**
	 * Converts a ResultSet row to an Admin entity.
	 * 
	 * Type conversion handling:
	 * 1. Reads primitive types (int, String) directly from ResultSet
	 * 2. Converts status string to UserStatus enum using valueOf()
	 * 3. Reads dateLastPasswordChange as string (YYYY-MM-DD format from database)
	 * 4. Password remains hashed (never decrypted, used only for comparison)
	 * 
	 * Called by inherited findById(), findAll(), and findWhere() methods.
	 * 
	 * @param rows ResultSet positioned on the row to convert
	 * @return Admin object constructed from row data
	 * @throws SQLException If error occurs reading data or converting types
	 */
	@Override
	public Admin rowToX(ResultSet rows) throws SQLException 
	{
	    Admin a = new Admin();

	    a.setId(rows.getInt("id"));
	    a.setFirstName(rows.getString("firstname"));
	    a.setLastName(rows.getString("lastname"));
	    a.setSsn(rows.getString("ssn"));
	    a.setEmail(rows.getString("email"));
	    a.setPassword(rows.getString("password"));
	    a.setStatus(UserStatus.valueOf(rows.getString("status")));
        a.setDateLastPasswordChange(rows.getString("dateLastPasswordChange"));
	    return a;
	}

	/**
	 * Authenticates an admin using email and password.
	 * 
	 * Authentication process:
	 * 1. Hashes plain-text password using same algorithm as registration (bcrypt)
	 * 2. Queries database for matching email AND hashed password
	 * 3. Returns Admin object if credentials match, null otherwise
	 * 
	 * Security: Plain-text password never sent to database or stored in query.
	 * Uses findWhere() inherited method for parameterized query construction.
	 * 
	 * @param email Admin's email address
	 * @param plainPassword Password in plain text (hashed before comparison)
	 * @return Admin object if credentials correct, null if authentication fails
	 * @throws SQLException If database error occurs during query
	 */
	@Override
	public Admin login(String email, String plainPassword) throws SQLException 
	{
		PasswordHasher hasher = Context.getDependency(PasswordHasher.class);
		String hashedPassword = hasher.hash(plainPassword);
		
		List<Admin> matches = 
				findWhere("email='" + email + "' and password='" + hashedPassword + "'");
		
		return matches.size() > 0 ? matches.get(0) : null;
	}

	/**
	 * Changes admin password with old password verification.
	 * 
	 * Password change workflow:
	 * 1. Validates new password differs from old password (prevents accidental re-use)
	 * 2. Hashes old password and compares with stored hash (authentication check)
	 * 3. If verification passes, updates password to hashed new password
	 * 4. Updates dateLastPasswordChange to current date (tracks password age)
	 * 
	 * Security validations:
	 * - Throws SQLException if old password incorrect (prevents unauthorized changes)
	 * - Throws SQLException if passwords identical (enforces actual change)
	 * 
	 * @param id Admin ID to change password for
	 * @param oldPassword Current password in plain text (for verification)
	 * @param newPassword New password in plain text (will be hashed)
	 * @throws SQLException If old password incorrect, passwords identical, or database error
	 */
	@Override
	public void changePassword(int id, String oldPassword, String newPassword) throws SQLException 
	{
		PasswordHasher hasher = Context.getDependency(PasswordHasher.class);
	    if(oldPassword.equals(newPassword))
	        throw new SQLException("Passwords must be different");
	    String oldPasswordHashed = hasher.hash(oldPassword);
	    Admin a = findById(id);
	    if(!oldPasswordHashed.equals(a.getPassword()))
	        throw new SQLException("Old password does not match");
	    String sql = "UPDATE admin SET password=?, datelastpasswordchange=? WHERE id=?";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, hasher.hash(newPassword));
	    ps.setString(2, LocalDate.now().toString());  
	    ps.setInt(3, id);
	    ps.execute();
	    ps.close();
	}

	/**
	 * Checks if at least one admin exists in the database.
	 * 
	 * Implementation using try-with-resources:
	 * 1. Executes COUNT(*) query to count total admin rows
	 * 2. Returns true if count > 0, false if table empty
	 * 3. PreparedStatement and ResultSet automatically closed by try-with-resources (prevents memory leaks)
	 * 
	 * Use case: First-time setup to determine if initial admin creation needed.
	 * Try-with-resources ensures cleanup even if SQLException thrown.
	 * 
	 * @return true if at least one admin exists, false if admin table empty
	 * @throws SQLException If connection error or query execution fails
	 */
	@Override
	public boolean existsAnyAdmin() throws SQLException
	{
	    String sql = "SELECT COUNT(*) FROM admin";
	    
	    try (PreparedStatement ps = connection.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery())
	    {
	        if (rs.next())
	            return rs.getInt(1) > 0;
	        return false;
	    }
	}

	/**
	 * Changes the status of an admin (ACTIVE, INACTIVE, SUSPENDED).
	 * 
	 * Status change validation:
	 * 1. Retrieves admin by ID to verify existence
	 * 2. Throws SQLException if admin not found (invalid ID)
	 * 3. Checks if current status already matches new status (prevents redundant updates)
	 * 4. If status differs, executes UPDATE query to change status
	 * 
	 * Use cases: Admin suspension, account activation, temporary deactivation.
	 * Idempotency check avoids unnecessary database writes.
	 * 
	 * @param adminId ID of admin to modify
	 * @param newStatus New UserStatus enum value (ACTIVE, INACTIVE, or SUSPENDED)
	 * @throws SQLException If admin not found, status unchanged, or database error
	 */
	@Override
	public void changeStatus(int adminId, UserStatus newStatus) throws SQLException
	{
	    Admin admin = findById(adminId);	    
	    if (admin == null)
	        throw new SQLException("Admin con ID " + adminId + " non trovato");	    
	    if (admin.getStatus() == newStatus)
	        throw new SQLException("Admin ha già lo status " + newStatus);	    
	    String query = "UPDATE admin SET status = ? WHERE id = ?";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, newStatus.toString());
	    ps.setInt(2, adminId);
	    
	    ps.executeUpdate();
	    ps.close();
	}
}
