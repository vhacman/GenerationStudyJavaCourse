/**
 * ============================================================================
 * TODO LIST AGGIORNATO - LESSON MANAGEMENT SYSTEM
 * Data Aggiornamento: 2026-01-30
 * Completamento Progetto: 65%
 * ============================================================================
 *
 * ============================================================================
 * SEZIONE 1: BUG CRITICI DA FIXARE SUBITO ⚠️
 * ============================================================================
 *
 * [CRITICO] 1.1 AdminRepositorySQL.java - RIGA 219
 *   BUG: changeStatus() aggiorna tabella "admins" invece di "admin"
 *   AZIONE: Cambiare "UPDATE admins SET" in "UPDATE admin SET"
 *   FILE: src/com/generation/pl/model/repository/SQLRepository/AdminRepositorySQL.java
 *
 * [CRITICO] 1.2 TeacherRepositorySQL.java - RIGA 243
 *   BUG: changeStatus() aggiorna tabella "admins" invece di "teacher"
 *   AZIONE: Cambiare "UPDATE admins SET" in "UPDATE teacher SET"
 *   FILE: src/com/generation/pl/model/repository/SQLRepository/TeacherRepositorySQL.java
 *
 * [CRITICO] 1.3 StudentRepositorySQL.java - RIGA 212
 *   BUG: changeStatus() aggiorna tabella "admins" invece di "student"
 *   AZIONE: Cambiare "UPDATE admins SET" in "UPDATE student SET"
 *   FILE: src/com/generation/pl/model/repository/SQLRepository/StudentRepositorySQL.java
 *
 * [CRITICO] 1.4 Collect.java - RIGHE 156-157
 *   BUG: collectAdminData() usa indici array sbagliati
 *   RIGA 156: baseData[3] dovrebbe essere baseData[2] (SSN)
 *   RIGA 157: baseData[4] dovrebbe essere baseData[3] (email)
 *   FILE: src/com/generation/pl/controller/Collect.java
 *
 * [MEDIO] 1.5 Collect.java - collectTeacherData()
 *   BUG: Manca setPricePerLesson() - il prezzo non viene mai impostato
 *   AZIONE: Aggiungere raccolta e set del pricePerLesson
 *   FILE: src/com/generation/pl/controller/Collect.java
 *
 * ============================================================================
 * SEZIONE 2: REPOSITORY - METODI MANCANTI
 * ============================================================================
 *
 * --- 2.1 TeacherRepository.java + TeacherRepositorySQL.java ---
 * [TODO] Aggiungere metodo findLessonsByTeacherId(int teacherId)
 *   - INTERFACCIA: src/com/generation/pl/model/repository/interfaces/TeacherRepository.java
 *   - IMPLEMENTAZIONE: src/com/generation/pl/model/repository/SQLRepository/TeacherRepositorySQL.java
 *   - LOGICA: Usare LessonRepository.findWhere("teacherid = " + teacherId)
 *
 * [TODO] Aggiungere metodo findNextWeekLessonsByTeacherId(int teacherId)
 *   - INTERFACCIA: src/com/generation/pl/model/repository/interfaces/TeacherRepository.java
 *   - IMPLEMENTAZIONE: src/com/generation/pl/model/repository/SQLRepository/TeacherRepositorySQL.java
 *   - LOGICA: Filtrare lezioni con date tra LocalDate.now() e LocalDate.now().plusWeeks(1)
 *
 * [TODO] Aggiungere metodo calculateEarningsByTeacherIdAndPeriod(int teacherId, LocalDate startDate, LocalDate endDate)
 *   - INTERFACCIA: src/com/generation/pl/model/repository/interfaces/TeacherRepository.java
 *   - IMPLEMENTAZIONE: src/com/generation/pl/model/repository/SQLRepository/TeacherRepositorySQL.java
 *   - LOGICA: Sommare lesson.getPrice() per lezioni nel range di date
 *
 * --- 2.2 StudentRepository.java + StudentRepositorySQL.java ---
 * [TODO] Aggiungere metodo findLessonsByStudentId(int studentId)
 *   - INTERFACCIA: src/com/generation/pl/model/repository/interfaces/StudentRepository.java
 *   - IMPLEMENTAZIONE: src/com/generation/pl/model/repository/SQLRepository/StudentRepositorySQL.java
 *   - LOGICA: Usare LessonRepository.findWhere("studentid = " + studentId)
 *
 * [TODO] Aggiungere metodo calculateTotalSpentByStudentId(int studentId)
 *   - INTERFACCIA: src/com/generation/pl/model/repository/interfaces/StudentRepository.java
 *   - IMPLEMENTAZIONE: src/com/generation/pl/model/repository/SQLRepository/StudentRepositorySQL.java
 *   - LOGICA: Sommare lesson.getPrice() per tutte le lezioni dello studente
 *
 * ============================================================================
 * SEZIONE 3: CONTROLLERS - DA COMPLETARE/IMPLEMENTARE
 * ============================================================================
 *
 * --- 3.1 Main.java - ENTRY POINT ---
 * FILE: src/com/generation/pl/controller/Main.java
 * STATO: 30% completo
 *
 * [TODO] Aggiungere controllo primo admin all'avvio
 *   - if (!adminRepo.existsAnyAdmin()) { AdminMain.insertFirstAdmin(); }
 *   - Deve essere chiamato PRIMA del menu principale
 *
 * [TODO] Aggiungere loop infinito per menu
 *   - Wrappare lo switch in while(true) o while(!exit)
 *   - Aggiungere case "0" o "exit" per uscire
 *
 * [TODO] Gestire exit pulito
 *   - Opzione 0 nel menu per terminare
 *   - System.exit(0) o break del loop
 *
 * --- 3.2 TeacherMain.java ---
 * FILE: src/com/generation/pl/controller/TeacherMain.java
 * STATO: 25% completo
 *
 * [TODO] Implementare printLessonHistory()
 *   - Chiamare teacherRepo.findLessonsByTeacherId(loggedUser.getId())
 *   - Stampare lista lezioni con formato tabellare
 *   - Mostrare: data, ora, studente, prezzo
 *
 * [TODO] Implementare printNextWeekLessons()
 *   - Chiamare teacherRepo.findNextWeekLessonsByTeacherId(loggedUser.getId())
 *   - Stampare lezioni della prossima settimana
 *   - Formato: data, ora, studente
 *
 * [TODO] Implementare printEarnings()
 *   - Chiedere startDate e endDate all'utente
 *   - Chiamare teacherRepo.calculateEarningsByTeacherIdAndPeriod(id, start, end)
 *   - Stampare totale guadagni nel periodo
 *
 * [TODO] Implementare insertLesson() [SE RICHIESTO]
 *   - Permettere al teacher di inserire lezione
 *   - Chiedere: studentId, date, hour
 *   - Validare: date >= LocalDate.now().plusDays(1)
 *   - Creare Lesson e chiamare lessonRepo.insert()
 *
 * [TODO] Aggiungere displayMenu()
 *   - Caricare menu da Template.load("template/menu/teacher_menu.txt")
 *   - Mostrare opzioni disponibili
 *
 * --- 3.3 StudentMain.java ---
 * FILE: src/com/generation/pl/controller/StudentMain.java
 * STATO: 0% completo - DA IMPLEMENTARE COMPLETAMENTE
 *
 * [TODO] Dichiarare repository
 *   private static StudentRepository studentRepo = Context.getDependency(StudentRepositorySQL.class);
 *   private static TeacherRepository teacherRepo = Context.getDependency(TeacherRepositorySQL.class);
 *   private static LessonRepository lessonRepo = Context.getDependency(LessonRepositorySQL.class);
 *   private Student loggedUser;
 *
 * [TODO] Implementare login()
 *   - Max 3 tentativi
 *   - Chiamare studentRepo.login(email, password)
 *   - return null se fallisce
 *
 * [TODO] Implementare run()
 *   - loggedUser = login(); if null return
 *   - Loop menu con displayMenu()
 *   - Switch su scelte menu
 *
 * [TODO] Implementare searchTeacher()
 *   - Chiedere materia da cercare
 *   - Chiamare teacherRepo.findTeacherBySubject(subject)
 *   - Stampare lista teacher con: id, nome, bio, prezzo
 *
 * [TODO] Implementare bookLesson()
 *   - Chiedere: teacherId, date, hour
 *   - VALIDAZIONE CRITICA: date >= LocalDate.now().plusDays(1)
 *   - Se data non valida: "Le prenotazioni devono essere fatte almeno 1 giorno prima"
 *   - Recuperare teacher: teacherRepo.findById(teacherId, false)
 *   - Creare Lesson: new Lesson(0, date, hour, loggedUser, teacher, teacher.getPricePerLesson())
 *   - Validare lesson.isValid()
 *   - Chiamare lessonRepo.insert(lesson)
 *   - Stampare conferma con id lezione
 *
 * [TODO] Implementare printHistory()
 *   - Chiamare studentRepo.findLessonsByStudentId(loggedUser.getId())
 *   - Stampare lista lezioni formato tabellare
 *   - Mostrare: id, data, ora, teacher, materia, prezzo
 *   - ALLA FINE chiamare studentRepo.calculateTotalSpentByStudentId(loggedUser.getId())
 *   - Stampare: "Totale speso: €XXX"
 *
 * [TODO] Implementare generateReceipt()
 *   - Chiedere lesson_id
 *   - Chiamare lessonRepo.findById(lesson_id)
 *   - Verificare che lesson.getStudent().getId() == loggedUser.getId()
 *   - Se non corrisponde: "Non puoi generare ricevuta per lezioni di altri studenti"
 *   - Generare file HTML con:
 *     * Intestazione: "Ricevuta Lezione #[id]"
 *     * Data: lesson.getDate()
 *     * Ora: lesson.getHour()
 *     * Teacher: lesson.getTeacher().getFirstName() + " " + getLastName()
 *     * Materia: lesson.getTeacher().getSubjectsCSV()
 *     * Prezzo: €lesson.getPrice()
 *     * Footer: "Generato il [LocalDate.now()]"
 *   - Salvare come: "ricevuta_lesson_" + lessonId + ".html"
 *   - Stampare path file salvato
 *
 * [TODO] Implementare changePassword()
 *   - Chiedere old password e new password
 *   - Chiamare studentRepo.changePassword(loggedUser.getId(), oldPw, newPw)
 *   - Gestire SQLException e stampare messaggio
 *
 * [TODO] Implementare displayMenu()
 *   - Caricare menu da Template.load("template/menu/student_menu.txt")
 *   - Mostrare opzioni disponibili
 *
 * ============================================================================
 * SEZIONE 4: OTTIMIZZAZIONI E MIGLIORAMENTI (BASSA PRIORITÀ)
 * ============================================================================
 *
 * [OPZIONALE] 4.1 LessonRepositorySQL.calculateEarningsBySubjectLast30Days()
 *   FILE: src/com/generation/pl/model/repository/SQLRepository/LessonRepositorySQL.java
 *   RIGA 137: TODO comment suggerisce uso di Map per ottimizzazione
 *   AZIONE: Considerare refactoring con Map<Integer, Teacher> per evitare query ripetute
 *
 * [OPZIONALE] 4.2 Creare file template menu
 *   - template/menu/admin_menu.txt
 *   - template/menu/teacher_menu.txt
 *   - template/menu/student_menu.txt
 *   NOTA: AdminMain già usa Template.load("template/menu/admin_menu.txt") alla riga 120
 *
 * ============================================================================
 * RIEPILOGO MODIFICHE RISPETTO AL TODO ORIGINALE
 * ============================================================================
 *
 * ✅ COMPLETATI RISPETTO AL TODO ORIGINALE:
 *
 * ENTITÀ (100%):
 *   ✅ Admin.java - COMPLETAMENTE IMPLEMENTATO (era TODO)
 *     - dateLastPasswordChange, needsPasswordChange(), validazione completa
 *   ✅ Teacher.java - List<Lesson> + getter/setter aggiunti
 *   ✅ Student.java - List<Lesson> + getter/setter aggiunti
 *   ✅ Lesson.java - Già completato
 *   ✅ User.java - Classe base completa
 *
 * REPOSITORY INTERFACES:
 *   ✅ AdminRepository.java - COMPLETAMENTE DEFINITO (era TODO)
 *     - Tutti i 10 metodi definiti inclusi login, changePassword, existsAnyAdmin, changeStatus
 *   ✅ LessonRepository.java - calculateEarnings* aggiunti
 *   ⚠️  TeacherRepository.java - changeStatus aggiunto (mancano 3 metodi lesson-related)
 *   ⚠️  StudentRepository.java - changeStatus aggiunto (mancano 2 metodi lesson-related)
 *
 * REPOSITORY SQL IMPLEMENTATIONS:
 *   ✅ AdminRepositorySQL.java - COMPLETAMENTE IMPLEMENTATO (era TODO)
 *     - Tutti i metodi inclusi hashing password, login, changePassword, existsAnyAdmin
 *     - BUG: changeStatus() usa tabella sbagliata (riga 219)
 *   ✅ LessonRepositorySQL.java - COMPLETAMENTE IMPLEMENTATO (era TODO)
 *     - getInsertCmd, getUpdateCmd, rowToX, calculateEarnings* tutti implementati
 *   ✅ StudentRepositorySQL.java - getUpdateCmd() IMPLEMENTATO (TODO diceva null)
 *     - BUG: changeStatus() usa tabella sbagliata (riga 212)
 *   ⚠️  TeacherRepositorySQL.java - changeStatus aggiunto
 *     - BUG: changeStatus() usa tabella sbagliata (riga 243)
 *
 * CONTROLLERS:
 *   ✅ AdminMain.java - COMPLETAMENTE IMPLEMENTATO (era TODO)
 *     - login con 3 tentativi, controllo password scaduta
 *     - Menu completo con tutte le 9 opzioni
 *     - insertTeacher, insertStudent, insertAdmin
 *     - changeStatusUser unificato per admin/teacher/student
 *     - teacherEarnings e subjectEarnings
 *     - changePassword
 *     - insertAdmin(boolean isFirst) per primo admin
 *   ⚠️  Main.java - Parzialmente implementato (manca controllo primo admin e loop)
 *   ⚠️  TeacherMain.java - Struttura base creata (mancano 4-5 metodi)
 *   ❌ StudentMain.java - Non implementato (solo TODO comments)
 *
 * CONFIGURATION:
 *   ✅ Context.java - TUTTI i repository registrati
 *     - AdminRepositorySQL registrato (era TODO)
 *     - TeacherRepositorySQL, StudentRepositorySQL, LessonRepositorySQL registrati
 *     - MD5PasswordHasher registrato
 *
 * UTILITIES:
 *   ✅ Collect.java - Helper methods per raccolta dati
 *     - collectAdminData() implementato (ha bug indici array)
 *     - collectTeacherData() implementato (manca pricePerLesson)
 *     - collectStudentData() implementato
 *     - collectUserBaseData(), collectPasswordWithConfirmation(), collectStatus()
 *
 * ============================================================================
 * PERCENTUALE COMPLETAMENTO AGGIORNATA: 65%
 * ============================================================================
 *
 * DISTRIBUZIONE:
 *   - Entità: 100% ✅
 *   - Repository Interfaces: 85% ⚠️
 *   - Repository Implementations: 85% ⚠️ (con bug)
 *   - Controllers: 40% ❌
 *   - Configuration: 100% ✅
 *
 * PRIORITÀ DI LAVORO:
 *   1. [CRITICO] Fixare i 5 bug (changeStatus x3, Collect x2)
 *   2. [ALTA] Completare StudentMain.java (0% fatto)
 *   3. [ALTA] Completare TeacherMain.java (25% fatto)
 *   4. [ALTA] Fixare Main.java (aggiungere controllo primo admin e loop)
 *   5. [MEDIA] Aggiungere metodi mancanti ai repository (5 metodi totali)
 *   6. [BASSA] Ottimizzazioni performance
 *
 * ============================================================================
 * STIMA TEMPO RIMANENTE:
 * ============================================================================
 *   - Fix bug critici: 30 minuti
 *   - StudentMain completo: 2 ore
 *   - TeacherMain completo: 1 ora
 *   - Main.java fix: 15 minuti
 *   - Metodi repository: 1 ora
 *   - Testing completo: 1 ora
 *   TOTALE: ~5.5 ore per completare il progetto al 100%
 *
 * ============================================================================
 * NOTE FINALI
 * ============================================================================
 *
 * FUNZIONALITÀ CHE GIÀ FUNZIONANO:
 *   ✅ Login per Admin, Teacher, Student con password hashate
 *   ✅ Admin può inserire/gestire tutti gli utenti
 *   ✅ Admin può vedere statistiche guadagni (teacher/materia)
 *   ✅ Admin può cambiare status utenti (con bug da fixare)
 *   ✅ Cambio password con validazione
 *   ✅ Controllo scadenza password admin (2 settimane)
 *   ✅ Validazione entità con getErrors()
 *   ✅ Lazy/Eager loading per Teacher e Student
 *   ✅ Gestione lezioni (insert/update/delete)
 *   ✅ Database connection e dependency injection
 *
 * FUNZIONALITÀ CHE NON FUNZIONANO (DA IMPLEMENTARE):
 *   ❌ Studente non può fare nulla (StudentMain vuoto)
 *   ❌ Teacher non può vedere storico/guadagni (TeacherMain incompleto)
 *   ❌ Setup primo admin all'avvio
 *   ❌ Menu principale non loopa
 *   ❌ ChangeStatus utenti (bug tabella sbagliata)
 *   ❌ Raccolta prezzo teacher (bug in Collect)
 *   ❌ Ricevute HTML per studenti
 *
 * ============================================================================
 * FINE TODO AGGIORNATO
 * ============================================================================
 */
