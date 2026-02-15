package com.generation.fooddelivery.service;

/**
 * Eccezione personalizzata per gli errori di business logic.
 * Include un codice di stato HTTP per gestire risposte REST.
 */
public class ServiceException extends Exception
{
    int status;  // Codice HTTP (es: 404, 403, 400)

    public ServiceException(String message)
    {
        super(message);
    }

    public ServiceException(String message, int status)
    {
        super(message);
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }
}
