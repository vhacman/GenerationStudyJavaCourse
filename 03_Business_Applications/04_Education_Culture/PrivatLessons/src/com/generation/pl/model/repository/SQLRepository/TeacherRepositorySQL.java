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
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.security.PasswordHasher;

public class TeacherRepositorySQL extends SQLEntityRepository<Teacher> implements TeacherRepository
{
    /**
     * Costruttore che inizializza la connessione al database e la tabella
     * @param table nome della tabella nel database
     * @param connection oggetto Connection per comunicare con il database
     */
    public TeacherRepositorySQL(String table, Connection connection) 
    {
        super(table, connection);
    }
    /**
     * Prepara il comando SQL per aggiornare un Teacher esistente nel database
     * @param newVersion oggetto Teacher con i nuovi dati da salvare
     * @return PreparedStatement pronto per essere eseguito
     * @throws SQLException se ci sono errori nella preparazione della query
     */
    @Override
    protected PreparedStatement getUpdateCmd(Teacher newVersion) throws SQLException 
    {
        // Query SQL per modificare tutti i campi del Teacher tramite ID
        String 				sql = "UPDATE Teacher SET firstname=?, lastname=?, ssn=?, email=?, bio=?, status=?, priceperlesson=?, subjects=? WHERE id=?";
        PreparedStatement 	ps = connection.prepareStatement(sql);
        // Assegna i valori ai parametri della query - allineamento per chiarezza
        ps.setString(1, newVersion.getFirstName());
        ps.setString(2, newVersion.getLastName());
        ps.setString(3, newVersion.getSsn());
        ps.setString(4, newVersion.getEmail());
        ps.setString(5, newVersion.getBio());
        // Converte l'Enum Status in stringa (gestisce anche il caso null)
        ps.setString(6, newVersion.getStatus() != null ? newVersion.getStatus().toString() : null);
        ps.setInt(7, newVersion.getPricePerLesson());
        // Salva le materie in formato CSV (comma-separated values)
        ps.setString(8, newVersion.getSubjectsCSV());
        // ID del Teacher da aggiornare (clausola WHERE)
        ps.setInt(9, newVersion.getId());
        return ps;
    }
    
    /**
     * Prepara il comando SQL per inserire un nuovo Teacher nel database
     * @param x oggetto Teacher da inserire
     * @return PreparedStatement pronto per essere eseguito
     * @throws SQLException se ci sono errori nella preparazione della query
     */
    @Override
    protected PreparedStatement getInsertCmd(Teacher x) throws SQLException 
    {
        // Query SQL per inserire un nuovo record nella tabella Teacher
        String 				sql = "insert into Teacher (firstname,lastname,ssn,email,bio,status,priceperlesson,subjects,password) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement 	ps = connection.prepareStatement(sql);
        // Assegna tutti i valori del nuovo Teacher
        ps.setString(1, x.getFirstName());
        ps.setString(2, x.getLastName());
        ps.setString(3, x.getSsn());
        ps.setString(4, x.getEmail());
        ps.setString(5, x.getBio());
        ps.setString(6, x.getStatus().toString());
        ps.setInt(7, x.getPricePerLesson());
        ps.setString(8, x.getSubjectsCSV());        
        
        // Hashiamo la password prima di salvarla per sicurezza
        PasswordHasher 	hasher = Context.getDependency(PasswordHasher.class);
        ps.setString(9, hasher.hash(x.getPassword()));
                
        return ps;
    }
    /**
     * Recupera una lista di Teacher che soddisfano una condizione specifica,
     * caricando anche tutte le lezioni associate a ciascun teacher.
     *
     * @param cond la condizione SQL da applicare nella query (es. "status='ACTIVE'")
     * @return una lista di oggetti Teacher con le relative lezioni caricate
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    @Override
    public List<Teacher> findWhere(String cond) throws SQLException
    {
        // Ottengo il repository delle lezioni tramite dependency injection
        LessonRepository     	lessonRepo = Context.getDependency(LessonRepository.class);
        List<Teacher>           res = super.findWhere(cond);
        List<Lesson>      		allLessons = lessonRepo.findAll();
        for (Teacher teacher : res)
            for (Lesson lesson : allLessons)
                // Confronto lesson.getTeacher().getId() con l'ID del teacher
                // Accedo all'ID del teacher tramite l'oggetto teacher dentro lesson
                if(lesson.getTeacher().getId() == teacher.getId())
                    teacher.getLessons().add(lesson);
        return res;
    }

    /**
     * Recupera un Teacher tramite ID, con opzione per caricare o meno
     * le entità associate (lezioni).
     *
     * @param id l'identificativo univoco del teacher da cercare
     * @param complete true per caricare anche le lezioni associate, false per i soli dati base
     * @return l'oggetto Teacher richiesto, con o senza relazioni caricate
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    @Override
    public Teacher findById(int id, boolean complete) throws SQLException
    {
        // Se complete è false, chiamo il metodo "naked" che restituisce solo i dati base del teacher
        // Se complete è true, chiamo il metodo che carica anche tutte le relazioni (lezioni, ecc.)
        return !complete ? findByIdNaked(id) : findById(id);
    }

    /**
     * Recupera un Teacher dal database utilizzando il suo ID, senza caricare le relazioni associate.
     * Questo metodo esegue una query diretta sulla tabella teacher e restituisce solo i dati
     * di base del teacher (senza lezioni o altre entità collegate).
     * @param id l'ID univoco del teacher da cercare
     * @return un oggetto Teacher con i soli dati di base se trovato, null se non esiste un teacher con quell'ID
     * @throws SQLException se si verifica un errore durante l'esecuzione della query o la connessione al database
     */
    private Teacher findByIdNaked(int id) throws SQLException
    {
        // Creo uno Statement per eseguire la query SQL
        Statement     		readSQL = connection.createStatement();
        // Eseguo la SELECT per cercare il teacher con l'ID specificato
        ResultSet         	rows = readSQL.executeQuery("SELECT * FROM teacher WHERE id =" + id);
        // Se esiste una riga (rows.next() == true), la converto in oggetto Teacher
        // Altrimenti restituisco null per indicare che il teacher non è stato trovato
        Teacher             res = rows.next() ? rowToX(rows) : null;
        // Chiudo le risorse per evitare memory leak
        readSQL.close();
        rows.close();
        return res;
    }
   
    /**
     * Converte una riga del ResultSet in un oggetto Teacher
     * @param rows ResultSet contenente i dati dal database
     * @return oggetto Teacher popolato con i dati della riga
     * @throws SQLException se ci sono errori nel leggere i dati
     */
    @Override
    public Teacher rowToX(ResultSet rows) throws SQLException
    {
        Teacher 		teacher = new Teacher();
        // Carica i campi base dal database
        teacher.setId(rows.getInt("id"));
        teacher.setFirstName(rows.getString("firstname"));
        teacher.setLastName(rows.getString("lastname"));
        teacher.setSsn(rows.getString("ssn"));
        teacher.setEmail(rows.getString("email"));
        // Carica i campi specifici di Teacher
        teacher.setBio(rows.getString("bio"));
        teacher.setPricePerLesson(rows.getInt("priceperlesson"));
        // Carica le materie dalla stringa CSV
        teacher.setSubjects(rows.getString("subjects"));
        // Carica l'hash della password (non la password in chiaro!)
        teacher.setPassword(rows.getString("password"));
        // Converte la stringa status in Enum (gestisce anche null)
        String statusStr = rows.getString("status");
        if (statusStr != null)
            teacher.setStatus(UserStatus.valueOf(statusStr));
        // La lista lessons rimane vuota (sarà popolata da findWhere o findById se necessario)
        return teacher;
    }
    /**
     * Esegue il login verificando email e password hashata
     * @param email indirizzo email del teacher
     * @param plainPassword password in chiaro inserita dall'utente
     * @return oggetto Teacher se le credenziali sono corrette, null altrimenti
     * @throws SQLException se ci sono errori nella query al database
     */
    @Override
    public Teacher login(String email, String plainPassword) throws SQLException 
    {
        // Hash della password inserita dall'utente
        PasswordHasher hasher         = Context.getDependency(PasswordHasher.class);
        String         hashedPassword = hasher.hash(plainPassword);       
        
        // Cerca nel database Teacher con email e password hashata corrispondenti
        List<Teacher> matches = findWhere("email='" + email + "' and password='" + hashedPassword + "'");
        // Se trovo corrispondenza ritorno il Teacher, altrimenti null
        return matches.size() > 0 ? matches.get(0) : null;
    }
    /**Cambia la password di un Teacher dopo aver verificato quella vecchia
     * @param id identificativo univoco del Teacher
     * @param oldPassword password vecchia in chiaro
     * @param newPassword password nuova in chiaro
     * @throws SQLException se le password sono uguali o quella vecchia non corrisponde*/
    @Override
    public void changePassword(int id, String oldPassword, String newPassword) throws SQLException 
    {
        // Validazione: la nuova password deve essere diversa dalla vecchia
        if (oldPassword.equals(newPassword))
            throw new SQLException("Passwords must be different");        
        PasswordHasher 		hasher            = Context.getDependency(PasswordHasher.class);
        String         		oldPasswordHashed = hasher.hash(oldPassword);        
        // Recupera il Teacher dal database
        Teacher 			t = findById(id);
        // Verifica che la vecchia password corrisponda
        if (!oldPasswordHashed.equals(t.getPassword()))
            throw new SQLException("Old password does not match");
        // Aggiorna la password nel database con quella nuova hashata
        String            	sql = "update teacher set password=? where id=?";
        PreparedStatement 	ps  = connection.prepareStatement(sql);
        ps.setString(1, hasher.hash(newPassword));
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }
    
    /**
     * Cerca tutti i Teacher che insegnano una specifica materia
     * @param subject nome della materia da cercare (es. "Math", "English")
     * @return lista di Teacher che insegnano quella materia
     * @throws SQLException se ci sono errori nella query
     */
    @Override
    public List<Teacher> findTeacherBySubject(String subject) throws SQLException
    {
        return findWhere("subjects LIKE '%" + subject + "%'");
    }
    
    /**
     * Cambia lo status di un teacher
     */
    @Override
    public void changeStatus(int teacherId, UserStatus newStatus) throws SQLException
    {
        // Prima recupera il teacher corrente
        Teacher teacher = findById(teacherId, false);        
        if (teacher == null)
            throw new SQLException("Teacher con ID " + teacherId + " non trovato");        
        // Controlla se lo status è già quello richiesto
        if (teacher.getStatus() == newStatus)
            throw new SQLException("Teacher ha già lo status " + newStatus);        
        //Solo se è diverso, aggiorna
        String query = "UPDATE teacher SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, newStatus.toString());
        ps.setInt(2, teacherId);        
        ps.executeUpdate();
        ps.close();
    }

    
    
}
