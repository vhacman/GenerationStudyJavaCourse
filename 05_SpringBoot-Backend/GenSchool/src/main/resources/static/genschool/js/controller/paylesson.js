/***************************************************************
 * CONTROLLER - Pattern MVC (Model-View-Controller)
 * 
 * Responsabilità:
 * 1. Gestione eventi UI (onChange, onClick)
 * 2. Sincronizzazione bidirezionale View ↔ Model
 * 3. Comunicazione con backend via fetch API
 * 
 * Flusso dati:
 * User Input → synch() → Model (Lesson)
 * Model.getPrice() → synch() → View (price output)
 * saveBtn → callAPI() → Backend REST API
 ***************************************************************/

let controller =
{
    lesson: new Lesson(),

    /**
     * Sincronizza i dati tra il form HTML e l'oggetto Model.
     * Esegue validazione e aggiorna i campi di output.
     */
    synch: function()
    {
        console.log("Sincronizzo");
        
        /***************************************************************
         * FASE INPUT: Form → Model
         * 
         * → Legge valori dai campi del form tramite lessonForm
         * → Popola le proprietà dell'oggetto lesson
         * → parseInt() converte stringa in numero per hours
         * 
         * Vantaggi uso di name attribute:
         * lessonForm.student.value invece di getElementById()
         * Codice più compatto e leggibile
         ***************************************************************/
        
        controller.lesson.student   = lessonForm.student.value;
        controller.lesson.hours     = parseInt(lessonForm.hours.value);
        controller.lesson.subject   = lessonForm.subject.value;

        /***************************************************************
         * FASE OUTPUT: Model → Form
         * 
         * → Calcola prezzo tramite lesson.getPrice()
         * → Aggiorna campo readonly con valore formattato
         * → Abilita/disabilita bottone in base a lesson.isValid()
         * 
         * Pattern: Reactive UI
         * Ogni modifica input aggiorna automaticamente gli output
         ***************************************************************/
        
        lessonForm.price.value          = controller.lesson.getPrice() + " €";
        lessonForm.saveBtn.disabled     = !controller.lesson.isValid();
    },

    /**
     * Invia i dati della lezione al backend tramite chiamata REST API.
     * Gestisce risposta positiva e negativa con feedback utente.
     */
    callAPI: function()
    {
        /***************************************************************
         * FETCH API - Comunicazione HTTP Asincrona
         * 
         * Pattern: Promise Chain
         * fetch() → then(response) → then(json) → catch(error)
         * 
         * Metodo: POST
         * Headers: Content-Type application/json (serializzazione JSON)
         * Body: Payload con proprietà dell'entità Lesson
         * 
         * Backend si aspetta:
         * POST /genschool/api/lessons
         * {
         *   "student": "Mario Rossi",
         *   "hours": 2,
         *   "subject": "JAVA"
         * }
         ***************************************************************/
        
        let apiURL = '/genschool/api/lessons';
        
        let payload =
        {
            student:    controller.lesson.student,
            hours:      controller.lesson.hours,
            subject:    controller.lesson.subject
        };

        fetch(apiURL,
        {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload) // Converte oggetto JS → stringa JSON
        })
        .then(response =>
        {
            /***************************************************************
             * GESTIONE RESPONSE HTTP
             * 
             * → response.ok è true per status code 200-299
             * → Se false, lancia eccezione che va in catch()
             * → response.json() parsa il corpo della risposta come JSON
             * 
             * Backend dovrebbe restituire:
             * {
             *   "id": 123,
             *   "student": "Mario Rossi",
             *   "hours": 2,
             *   "subject": "JAVA",
             *   "price": 100.0
             * }
             ***************************************************************/
            
            if(!response.ok)
                throw new Error("Invalid data");
            
            return response.json();
        })
        .then(json =>
        {
            /***************************************************************
             * SUCCESSO - Salvataggio completato
             * 
             * → Reset form ripristina valori di default
             * → operationstatus mostra ID assegnato dal backend
             * → ID confermato da database (auto-increment)
             ***************************************************************/
            
            lessonForm.reset();
            lessonForm.operationstatus.value = 'Prenotazione salvata con ID: ' + json.id;
        })
        .catch(err =>
        {
            /***************************************************************
             * ERRORE - Gestione fallimento
             * 
             * → Cattura errori di rete o status code != 200
             * → Mostra messaggio generico (evita esporre dettagli tecnici)
             * → In produzione: logging lato server per debug
             ***************************************************************/
            
            lessonForm.operationstatus.value = 'Errore durante il salvataggio';
        });
    }
};

/***************************************************************
 * NOTA ARCHITETTURALE
 * 
 * Questo controller è un oggetto singleton (unica istanza globale).
 * Mantiene lo stato dell'applicazione tramite la proprietà lesson.
 * 
 * Vantaggi:
 * - Accesso globale da eventi HTML (onChange="controller.synch()")
 * - Persistenza stato tra chiamate synch()
 * 
 * Limitazioni:
 * - Non supporta gestione multi-lesson simultanee
 * - Stato non condiviso tra tab browser (no sessionStorage)
 * 
 * Possibili miglioramenti:
 * - Aggiungere debouncing su synch() per input pesanti
 * - Implementare loading state durante fetch()
 * - Mostrare errori di validazione specifici all'utente
 ***************************************************************/