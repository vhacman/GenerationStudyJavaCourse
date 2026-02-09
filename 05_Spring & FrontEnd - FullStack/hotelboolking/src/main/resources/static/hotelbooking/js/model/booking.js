// model: entita' Booking lato frontend
// stessa logica della Configuration del pcconfigurator
class Booking
{
    constructor()
    {
        this.guestName     = "";
        this.roomType      = "";
        this.checkInDate   = "";
        this.checkOutDate  = "";
        this.nightlyRate   = 0;
        this.paymentMethod = "";
    }
    getErrors()
    {
        let errors = [];
        if (!this.guestName)
            errors.push("Nome ospite mancante");
        if (!this.roomType)
            errors.push("Tipo camera mancante");
        if (!this.checkInDate)
            errors.push("Data check-in mancante");
        if (!this.checkOutDate)
            errors.push("Data check-out mancante");
        if (!this.nightlyRate || this.nightlyRate <= 0)
            errors.push("Tariffa notturna non valida");
        if (!this.paymentMethod)
            errors.push("Metodo di pagamento mancante");
        return errors;
    }
    isValid()
    {
        return this.getErrors().length == 0;
    }
}
