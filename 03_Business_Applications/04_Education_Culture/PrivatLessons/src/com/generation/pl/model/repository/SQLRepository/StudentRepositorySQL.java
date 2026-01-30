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
import com.generation.pl.security.PasswordHasher;

// Repository SQL per la gestione degli Student nel database
public class StudentRepositorySQL extends SQLEntityRepository<Student> implements StudentRepository
{
    /**
     * Costruttore che inizializza la connessione al database e la tabella
     * @param table nome della tabella nel database
     * @param connection oggetto Connection per comunicare con il database
     */
    public StudentRepositorySQL(String table, Connection connection)
    {
        super(table, connection);
    }
    /**
     * Recupera una lista di Student che soddisfano una condizione specifica,
     * caricando anche tutte le lezioni associate a ciascuno student
     * @param cond la condizione SQL da applicare nella query (es. "status='ACTIVE'")
     * @return una lista di oggetti Student con le relative lezioni caricate
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    @Override
    public List<Student> findWhere(String cond) throws SQLException
    {
        // Ottengo il repository delle lezioni tramite dependency injection
        LessonRepository lessonRepo = Context.getDependency(LessonRepository.class);
        List<Student>    res        = super.findWhere(cond);
        List<Lesson>     allLessons = lessonRepo.findAll();
        // Per ogni student trovato, carico le sue lezioni
        for (Student student : res)
            for (Lesson lesson : allLessons)
                // Confronto lesson.getStudent().getId() con l'ID dello student
                if (lesson.getStudent().getId() == student.getId())
                    student.getLessons().add(lesson);
        return res;
    }
    /**
     * Recupera uno Student tramite ID, con opzione per caricare o meno le entità associate
     * @param id l'identificativo univoco dello student da cercare
     * @param complete true per caricare anche le lezioni associate, false per i soli dati base
     * @return l'oggetto Student richiesto, con o senza relazioni caricate
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    @Override
    public Student findById(int id, boolean complete) throws SQLException
    {
        // Se complete è false, chiamo il metodo naked che restituisce solo i dati base
        // Se complete è true, chiamo il metodo che carica anche tutte le relazioni
        return !complete ? findByIdNaked(id) : findById(id);
    }
    /**
     * Recupera uno Student dal database utilizzando il suo ID, senza caricare le relazioni associate
     * @param id l'ID univoco dello student da cercare
     * @return un oggetto Student con i soli dati di base se trovato, null se non esiste
     * @throws SQLException se si verifica un errore durante l'esecuzione della query
     */
    private Student findByIdNaked(int id) throws SQLException
    {
        // Creo uno Statement per eseguire la query SQL
        Statement statement = connection.createStatement();
        // Eseguo la SELECT per cercare lo student con l'ID specificato
        ResultSet rows      = statement.executeQuery("SELECT * FROM student WHERE id =" + id);
        // Se esiste una riga, la converto in oggetto Student, altrimenti null
        Student   res       = rows.next() ? rowToX(rows) : null;
        // Chiudo le risorse per evitare memory leak
        statement.close();
        rows.close();
        return res;
    }
    /**
     * Prepara il comando SQL per inserire un nuovo Student nel database
     * @param x oggetto Student da inserire
     * @return PreparedStatement pronto per essere eseguito
     * @throws SQLException se ci sono errori nella preparazione della query
     */
    @Override
    protected PreparedStatement getInsertCmd(Student x) throws SQLException
    {
        // Query SQL per inserire un nuovo record nella tabella Student
        String            sql    = "insert into Student (firstname,lastname,ssn,email,dob,status,password) values(?,?,?,?,?,?,?)";
        PreparedStatement ps     = connection.prepareStatement(sql);
        PasswordHasher    hasher = Context.getDependency(PasswordHasher.class);
        // Assegna tutti i valori del nuovo Student
        ps.setString(1, x.getFirstName());
        ps.setString(2, x.getLastName());
        ps.setString(3, x.getSsn());
        ps.setString(4, x.getEmail());
        ps.setString(5, x.getDob().toString());
        ps.setString(6, x.getStatus().toString());
        // Hashiamo la password prima di salvarla per sicurezza
        ps.setString(7, hasher.hash(x.getPassword()));
        return ps;
    }
    /**
     * Prepara il comando SQL per aggiornare uno Student esistente nel database
     * @param newVersion oggetto Student con i nuovi dati da salvare
     * @return PreparedStatement pronto per essere eseguito
     * @throws SQLException se ci sono errori nella preparazione della query
     */
    @Override
    protected PreparedStatement getUpdateCmd(Student newVersion) throws SQLException
    {
        // Query SQL per modificare tutti i campi dello Student tramite ID
        String sql = "UPDATE Student SET firstname=?, lastname=?, ssn=?, email=?, dob=?, status=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        // Assegna i valori ai parametri della query
        ps.setString(1, newVersion.getFirstName());
        ps.setString(2, newVersion.getLastName());
        ps.setString(3, newVersion.getSsn());
        ps.setString(4, newVersion.getEmail());
        ps.setString(5, newVersion.getDob().toString());
        // Converte l'Enum Status in stringa (gestisce anche il caso null)
        ps.setString(6, newVersion.getStatus() != null ? newVersion.getStatus().toString() : null);
        // ID dello Student da aggiornare (clausola WHERE)
        ps.setInt(7, newVersion.getId());
        return ps;
    }
    /**
     * Converte una riga del ResultSet in un oggetto Student
     * @param rows ResultSet contenente i dati dal database
     * @return oggetto Student popolato con i dati della riga
     * @throws SQLException se ci sono errori nel leggere i dati
     */
    @Override
    public Student rowToX(ResultSet rows) throws SQLException
    {
        Student s         = new Student();
        String  statusStr = rows.getString("status");
        // Carica i campi base dal database
        s.setId(rows.getInt("id"));
        s.setFirstName(rows.getString("firstname"));
        s.setLastName(rows.getString("lastname"));
        s.setSsn(rows.getString("ssn"));
        s.setEmail(rows.getString("email"));
        s.setDob(rows.getString("dob"));
        // Carica l'hash della password (non la password in chiaro!)
        s.setPassword(rows.getString("password"));
        // Converte la stringa status in Enum (gestisce anche null)
        if (statusStr != null)
            s.setStatus(UserStatus.valueOf(statusStr));
        // La lista lessons rimane vuota (sarà popolata da findWhere o findById se necessario)
        return s;
    }
    /**
     * Esegue il login verificando email e password hashata
     * @param email indirizzo email dello student
     * @param plainPassword password in chiaro inserita dall'utente
     * @return oggetto Student se le credenziali sono corrette, null altrimenti
     * @throws SQLException se ci sono errori nella query al database
     */
    @Override
    public Student login(String email, String plainPassword) throws SQLException
    {
        // Hash della password inserita dall'utente
        PasswordHasher hasher         = Context.getDependency(PasswordHasher.class);
        String         hashedPassword = hasher.hash(plainPassword);
        // Cerca nel database Student con email e password hashata corrispondenti
        List<Student>  matches        = findWhere("email='" + email + "' and password='" + hashedPassword + "'");
        // Se trovo corrispondenza ritorno lo Student, altrimenti null
        return matches.size() > 0 ? matches.get(0) : null;
    }
    /**
     * Cambia la password di uno Student dopo aver verificato quella vecchia
     * @param id identificativo univoco dello Student
     * @param oldPassword password vecchia in chiaro
     * @param newPassword password nuova in chiaro
     * @throws SQLException se le password sono uguali o quella vecchia non corrisponde
     */
    @Override
    public void changePassword(int id, String oldPassword, String newPassword) throws SQLException
    {
        // Validazione: la nuova password deve essere diversa dalla vecchia
        if (oldPassword.equals(newPassword))
            throw new SQLException("Passwords must be different");
        PasswordHasher hasher            = Context.getDependency(PasswordHasher.class);
        String         oldPasswordHashed = hasher.hash(oldPassword);
        // Recupera lo Student dal database
        Student s = findById(id);
        // Verifica che la vecchia password corrisponda
        if (!oldPasswordHashed.equals(s.getPassword()))
            throw new SQLException("Old password does not match");
        // Aggiorna la password nel database con quella nuova hashata
        String            sql = "update student set password=? where id=?";
        PreparedStatement ps  = connection.prepareStatement(sql);
        ps.setString(1, hasher.hash(newPassword));
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }
    /**
     * Cambia lo status di uno student
     */
    @Override
    public void changeStatus(int studentId, UserStatus newStatus) throws SQLException
    {
        //Prima recupera lo student corrente
        Student student = findById(studentId, false);        
        if (student == null)
            throw new SQLException("Student con ID " + studentId + " non trovato");        
        // Controlla se lo status è già quello richiesto
        if (student.getStatus() == newStatus)
            throw new SQLException("Student ha già lo status " + newStatus);        
        // Solo se è diverso, aggiorna
        String query = "UPDATE student SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, newStatus.toString());
        ps.setInt(2, studentId);        
        ps.executeUpdate();
        ps.close();
    }

    
}
