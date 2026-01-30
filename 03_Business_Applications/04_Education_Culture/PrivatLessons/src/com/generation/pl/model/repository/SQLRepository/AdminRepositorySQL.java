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
import com.generation.pl.security.PasswordHasher;

/**
 * Implementazione SQL del repository per la gestione degli amministratori.
 * Estende SQLEntityRepository per ereditare le operazioni CRUD di base.

 */
public class AdminRepositorySQL extends SQLEntityRepository<Admin> implements AdminRepository 
{
	/**
	 * Costruttore del repository.
	 * 
	 * @param table Nome della tabella nel database (es. "admin")
	 * @param connection Connessione al database da utilizzare
	 */
	public AdminRepositorySQL(String table, Connection connection) 
	{
		super(table, connection);
	}

	/**
	 * Prepara il comando SQL per l'inserimento di un nuovo amministratore.
	 * La password viene automaticamente hashata prima del salvataggio.
	 * Se la data dell'ultimo cambio password non è specificata, viene impostata alla data corrente.
	 * 
	 * @param x L'oggetto Admin da inserire
	 * @return PreparedStatement configurato per l'inserimento
	 * @throws SQLException Se si verifica un errore nella preparazione del comando
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
	    // Usa setString invece di setDate
	    if (x.getDateLastPasswordChange() == null)
	        ps.setString(7, LocalDate.now().toString());	    
	    else
	        ps.setString(7, x.getDateLastPasswordChange().toString());
	    return ps;
	}

	/**
	 * Prepara il comando SQL per l'aggiornamento di un amministratore esistente.
	 * Nota: Non modifica la password, che deve essere cambiata tramite changePassword.
	 * 
	 * @param newVersion L'oggetto Admin con i dati aggiornati
	 * @return PreparedStatement configurato per l'aggiornamento
	 * @throws SQLException Se si verifica un errore nella preparazione del comando
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
	 * Converte una riga del ResultSet in un oggetto Admin.
	 * Gestisce la conversione dei tipi SQL in tipi Java (Date, Enum).
	 * 
	 * @param rows ResultSet posizionato sulla riga da convertire
	 * @return L'oggetto Admin costruito dai dati della riga
	 * @throws SQLException Se si verifica un errore durante la lettura dei dati
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
	    // Usa getString invece di getDate
        a.setDateLastPasswordChange(rows.getString("dateLastPasswordChange"));
	    return a;
	}


	/**
	 * Autentica un amministratore tramite email e password.
	 * La password in chiaro viene hashata e confrontata con quella nel database.
	 * 
	 * @param email L'email dell'amministratore
	 * @param plainPassword La password in chiaro
	 * @return L'oggetto Admin se le credenziali sono corrette, null altrimenti
	 * @throws SQLException Se si verifica un errore durante la query
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
	    // Aggiorna password e data ultimo cambio
	    String sql = "UPDATE admin SET password=?, datelastpasswordchange=? WHERE id=?";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, hasher.hash(newPassword));
	    ps.setString(2, LocalDate.now().toString());  
	    ps.setInt(3, id);
	    ps.execute();
	    ps.close();
	}
	
	
	/**
	 * Controlla se esiste almeno un admin nel database.
	 * 
	 * @return true se esiste almeno un admin, false se la tabella è vuota
	 * @throws SQLException se ci sono problemi di connessione o query
	 *
	@Override
	public boolean existsAnyAdmin() throws SQLException
	{
	    // Query SQL: COUNT(*) conta quante righe ci sono nella tabella admin
	    String sql = "SELECT COUNT(*) FROM admin";	   
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery(); 
	    // Se trovo la riga del COUNT (dovrebbe sempre esserci)
	    if (rs.next())
	    {
	        // Ritorno true se count > 0, false altrimenti
	        int count = rs.getInt(1);
	        rs.close();
	        ps.close();
	        return count > 0;
	    }
	    // Fallback: se per qualche motivo COUNT non restituisce righe
	    rs.close();
	    ps.close();
	    return false;
	}*/
	
	/**
	 * Controlla se esiste almeno un admin nel database.
	 * 
	 * @return true se esiste almeno un admin, false se la tabella è vuota
	 * @throws SQLException se ci sono problemi di connessione o query
	 */
	@Override
	public boolean existsAnyAdmin() throws SQLException
	{
	    // Query SQL: COUNT(*) conta quante righe ci sono nella tabella admin
	    String sql = "SELECT COUNT(*) FROM admin";
	    
	    // Try-with-resources: dichiaro le risorse (ps, rs) tra parentesi tonde
	    // Java le chiude automaticamente alla fine del blocco (anche se c'è un'eccezione)
	    // Ordine di chiusura: inverso rispetto alla creazione (prima rs, poi ps)
	    // Vantaggio: niente memory leak, non serve finally, codice più pulito
	    try (PreparedStatement ps = connection.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery())
	    {
	        // Se trovo la riga del COUNT (dovrebbe sempre esserci)
	        if (rs.next())
	            // Ritorno true se count > 0, false altrimenti
	            return rs.getInt(1) > 0;
	        // Fallback: se per qualche motivo COUNT non restituisce righe
	        return false;
	    }
	    // Qui ps e rs sono già stati chiusi automaticamente da Java
	}

	/**
	 * Cambia lo status di un admin
	 */
	@Override
	public void changeStatus(int adminId, UserStatus newStatus) throws SQLException
	{
	    // Prima recupera l'admin corrente
	    Admin admin = findById(adminId);	    
	    if (admin == null)
	        throw new SQLException("Admin con ID " + adminId + " non trovato");	    
	    // Controlla se lo status è già quello richiesto
	    if (admin.getStatus() == newStatus)
	        throw new SQLException("Admin ha già lo status " + newStatus);	    
	    // Solo se è diverso, aggiorna
	    String query = "UPDATE admin SET status = ? WHERE id = ?";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, newStatus.toString());
	    ps.setInt(2, adminId);
	    
	    ps.executeUpdate();
	    ps.close();
	}

}
