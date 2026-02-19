// controller: gestisce eventi, sincronizza vista e model, chiama le API
let controller =
{
    booking: new Booking(),
    // sincronizza la form con il model (come in pcconfigurator)
    synch: function()
    {
        controller.booking.guestName     = bookingForm.guestName.value;
        controller.booking.roomType      = bookingForm.roomType.value;
        controller.booking.checkInDate   = bookingForm.checkInDate.value;
        controller.booking.checkOutDate  = bookingForm.checkOutDate.value;
        controller.booking.nightlyRate   = bookingForm.nightlyRate.value;
        controller.booking.paymentMethod = bookingForm.paymentMethod.value;
        // abilita/disabilita il bottone salva
        bookingForm.saveBtn.disabled = !controller.booking.isValid();
    },
    // POST: crea una nuova prenotazione
    callAPI: function()
    {
        let apiURL  = '/hotelservice/api/booking';
        let payload =
        {
            guestName:     controller.booking.guestName,
            roomType:      controller.booking.roomType,
            checkInDate:   controller.booking.checkInDate,
            checkOutDate:  controller.booking.checkOutDate,
            nightlyRate:   controller.booking.nightlyRate,
            paymentMethod: controller.booking.paymentMethod
        };
        fetch(apiURL,
        {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        })
        .then(response =>
        {
            if (!response.ok)
                throw new Error("Dati non validi");
            return response.json();
        })
        .then(json =>
        {
            bookingForm.reset();
            bookingForm.saveBtn.disabled = true;
            bookingForm.operationstatus.value = 'Salvato con id ' + json.id;
        })
        .catch(err =>
        {
            bookingForm.operationstatus.value = 'Errore nel salvataggio';
        });
    },
    // RICERCA: controlla quale filtro e' stato compilato e chiama l'endpoint giusto
    search: function()
    {
        let apiURL = '/hotelservice/api/booking';
        let url    = '';

        if (searchForm.searchId.value)
            url = apiURL + '/' + searchForm.searchId.value;
        else if (searchForm.searchGuest.value)
            url = apiURL + '/search?guest=' + searchForm.searchGuest.value;
        else if (searchForm.filterStatus.value)
            url = apiURL + '/status/' + searchForm.filterStatus.value;
        else if (searchForm.filterRoomType.value)
            url = apiURL + '/room/' + searchForm.filterRoomType.value;
        else
            url = apiURL;
        fetch(url)
        .then(response =>
        {
            if (!response.ok)
                throw new Error("Non trovato");
            return response.json();
        })
        .then(json =>
        {
            // se e' un singolo oggetto (ricerca per ID) lo metto in un array
            let bookings = Array.isArray(json) ? json : [json];
            controller.displayResults(bookings);
            searchForm.operationstatus.value = 'Trovati ' + bookings.length + ' risultati';
        })
        .catch(err =>
        {
            document.getElementById('results').innerHTML = 'Nessun risultato trovato';
            searchForm.operationstatus.value = 'Errore nella ricerca';
        });
    },

    // mostra i risultati nel div #results
    displayResults: function(bookings)
    {
        let html = '';
        for (let i = 0; i < bookings.length; i++)
        {
            let b = bookings[i];
            html += '<div class="result-item">';
            html += '<strong>#' + b.id + '</strong> - ' + b.guestName + '<br>';
            html += 'Camera: ' + b.roomType + ' | Stato: ' + b.status + '<br>';
            html += 'Check-in: ' + b.checkInDate + ' | Check-out: ' + b.checkOutDate + '<br>';
            html += 'Notti: ' + b.numberOfNights + ' | Totale: EUR ' + b.totalAmount;
            html += '</div>';
        }
        document.getElementById('results').innerHTML = html;
    },

    // AZIONI: cambiano lo stato della prenotazione tramite PUT
    doAction: function(action, successMessage)
    {
        let id = searchForm.actionId.value;
        if (!id)
        {
            searchForm.operationstatus.value = 'Inserisci un ID prenotazione';
            return;
        }
        let apiURL = '/hotelservice/api/booking/' + id + '/' + action;
        fetch(apiURL, { method: "PUT" })
        .then(response =>
        {
            if (!response.ok)
                return response.text().then(text => { throw new Error(text || "Operazione non riuscita"); });
            return response.json();
        })
        .then(json =>
        {
            searchForm.operationstatus.value = successMessage + ' (id ' + json.id + ')';
        })
        .catch(err =>
        {
            searchForm.operationstatus.value = 'Errore: ' + err.message;
        });
    },
    confirm:  function() { controller.doAction('confirm',  'Prenotazione confermata');  },
    checkin:  function() { controller.doAction('checkin',   'Check-in effettuato');      },
    checkout: function() { controller.doAction('checkout',  'Check-out effettuato');     },
    cancel:   function() { controller.doAction('cancel',    'Prenotazione cancellata'); }
};
