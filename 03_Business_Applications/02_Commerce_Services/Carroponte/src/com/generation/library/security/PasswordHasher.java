package com.generation.library.security;

/**
 * Interface for password hashing implementations.
 * Defines the contract for converting plain-text passwords into secure hashed representations.
 * Implementations must ensure one-way hashing (irreversible) for security compliance.
 * Used throughout the application for password storage and authentication.
 */
public interface PasswordHasher 
{
    /**
     * Hashes a plain-text password into a secure, irreversible format.
     * 
     * Requirements for implementations:
     * 1. Must be deterministic (same input always produces same output for authentication)
     * 2. Must be one-way (impossible to reverse hash back to plain text)
     * 3. Should be case-sensitive (different cases produce different hashes)
     * 4. Must handle empty strings and special characters safely
     * 
     * Security note: Hashed passwords are stored in database; plain text never persisted.
     * 
     * @param plainPassword The password in plain text (never stored)
     * @return Hashed representation of the password (safe for database storage)
     */
    String hash(String plainPassword);
}
