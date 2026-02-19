package com.generation.hotelboolking.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking implements Validable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int         id;
    private String      guestName;
    private String      roomType;
    private LocalDate   checkInDate;
    private LocalDate   checkOutDate;
    private double      nightlyRate;
    private int         numberOfNights;
    private double      totalRoomCost;
    private double      totalAmount;
    private String      status;
    private String      paymentMethod;
    /**
     * Calcola il numero di notti tra check-in e check-out.
     * Utilizza ChronoUnit.DAYS per la differenza temporale.
     */
    public void calculateNumberOfNights()
    {
        if (checkInDate != null && checkOutDate != null)
        {
            int days = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            this.numberOfNights = Math.max(days, 1);
        }
    }
    /**
     * Calcola il costo totale della camera moltiplicando tariffa per numero di notti.
     * Richiede che numberOfNights sia già stato calcolato.
     */
    public void calculateTotalRoomCost()
    {
        this.totalRoomCost = nightlyRate * numberOfNights;
    }
    /**
     * Calcola l'importo finale sommando costo camera e servizi extra.
     * Rappresenta il totale da fatturare all'ospite.
     */
    public void calculateTotalAmount()
    {
        this.totalAmount = totalRoomCost;
    }

    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();
        if (guestName == null || guestName.trim().isEmpty())
            errors.add("Il nome dell'ospite è obbligatorio");
        if (roomType == null || (!roomType.equals("single") && !roomType.equals("double") && !roomType.equals("suite")))
            errors.add("Tipologia camera non valida (ammesse: single, double, suite, deluxe)");
        if (checkInDate == null)
            errors.add("La data di check-in è obbligatoria");
        if (checkOutDate == null)
            errors.add("La data di check-out è obbligatoria");
        if (checkInDate != null && checkOutDate != null && checkOutDate.isBefore(checkInDate))
            errors.add("La data di check-out non può essere precedente al check-in");
        if (nightlyRate <= 0)
            errors.add("La tariffa notturna deve essere maggiore di zero");
        if (status == null || (!status.equals("pending") && !status.equals("confirmed")
                && !status.equals("checked-in") && !status.equals("checked-out") && !status.equals("cancelled")))
        {
            errors.add("Stato prenotazione non valido (ammessi: pending, confirmed, checked-in, checked-out, cancelled)");
        }
        if (paymentMethod == null || (!paymentMethod.equals("credit_card")
                && !paymentMethod.equals("cash") && !paymentMethod.equals("bank_transfer"))){
            errors.add("Metodo di pagamento non valido (ammessi: credit_card, cash, bank_transfer)");
        }
        return errors;
    }
    @Override
    public boolean isValid()
    {
        return getErrors().isEmpty();
    }
    public int          getId()                                             { return id; }
    public String       getGuestName()                                      { return guestName; }
    public String       getRoomType()                                       { return roomType; }
    public LocalDate    getCheckInDate()                                    { return checkInDate; }
    public LocalDate    getCheckOutDate()                                   { return checkOutDate; }
    public double       getNightlyRate()                                    { return nightlyRate; }
    public int          getNumberOfNights()                                 { return numberOfNights; }
    public double       getTotalRoomCost()                                  { return totalRoomCost; }
    public double       getTotalAmount()                                    { return totalAmount; }
    public String       getStatus()                                         { return status; }
    public String       getPaymentMethod()                                  { return paymentMethod; }
    public void         setId(int id)                                       { this.id = id; }
    public void         setGuestName(String guestName)                      { this.guestName = guestName; }
    public void         setRoomType(String roomType)                        { this.roomType = roomType; }
    public void         setCheckInDate(LocalDate checkInDate)               { this.checkInDate = checkInDate; }
    public void         setCheckOutDate(LocalDate checkOutDate)             { this.checkOutDate = checkOutDate; }
    public void         setNightlyRate(double nightlyRate)                  { this.nightlyRate = nightlyRate; }
    public void         setNumberOfNights(int numberOfNights)               { this.numberOfNights = numberOfNights; }
    public void         setTotalRoomCost(double totalRoomCost)              { this.totalRoomCost = totalRoomCost; }
    public void         setTotalAmount(double totalAmount)                  { this.totalAmount = totalAmount; }
    public void         setStatus(String status)                            { this.status = status; }
    public void         setPaymentMethod(String paymentMethod)              { this.paymentMethod = paymentMethod; }
}
