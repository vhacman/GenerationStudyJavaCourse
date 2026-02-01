package com.generation.library.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5-based implementation of PasswordHasher.
 * Uses MD5 cryptographic hash algorithm to convert plain-text passwords into 32-character hexadecimal hashes.
 * 
 * Algorithm characteristics:
 * - Deterministic: Same password always produces same hash (required for login verification)
 * - One-way: Cannot reverse hash back to original password
 * - Case-sensitive: "password" and "Password" produce different hashes
 * - Fast computation: Suitable for authentication workflows
 * 
 * Security note: MD5 is considered weak for modern cryptographic standards (vulnerable to collision attacks).
 * Recommended for educational purposes only. Production systems should use bcrypt, PBKDF2, or Argon2.
 * 
 * Output format: 32-character lowercase hexadecimal string (e.g., "5f4dcc3b5aa765d61d8327deb882cf99")
 */
public class MD5PasswordHasher implements PasswordHasher
{
    /**
     * Hashes a plain-text password using MD5 algorithm.
     * 
     * Hashing process:
     * 1. Instantiates MD5 MessageDigest algorithm (guaranteed available in all JVMs)
     * 2. Converts password string to byte array and computes MD5 hash (16 bytes)
     * 3. Converts byte array to hexadecimal string representation (32 characters)
     *    - Each byte formatted as two-digit hex (00-FF range)
     *    - Uses StringBuilder for efficient string concatenation
     * 
     * Exception handling: NoSuchAlgorithmException wrapped in RuntimeException
     * (MD5 guaranteed in Java spec, but checked exception must be handled).
     * 
     * @param password Plain-text password to hash
     * @return 32-character hexadecimal MD5 hash (lowercase)
     * @throws RuntimeException If MD5 algorithm not available (should never occur)
     */
    @Override
    public String hash(String password) 
    {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Error: MD5 algorithm not found", e);
        }
    }
}
