package com.generation.pl.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordHasher implements PasswordHasher
{

	public String hash(String password) 
	{
	    try 
	    {
	        // 1. Istanziamo l'algoritmo MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 2. Calcoliamo l'hash (restituisce un array di byte)
	        byte[] hashBytes = md.digest(password.getBytes());
	        // 3. Convertiamo l'array di byte in una stringa esadecimale
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashBytes) {
	            // Formattiamo ogni byte come stringa esadecimale a due cifre
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    } 
	    catch (NoSuchAlgorithmException e) {
	        // MD5 è garantito in ogni JVM, ma dobbiamo gestire l'eccezione
	        throw new RuntimeException("Error: MD5 algorithm not found", e);
	    }
	}
}
