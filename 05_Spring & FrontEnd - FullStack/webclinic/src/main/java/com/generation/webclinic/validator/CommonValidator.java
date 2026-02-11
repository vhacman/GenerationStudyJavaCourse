package com.generation.webclinic.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CommonValidator
{
    // cercare sul libro questo si chiama pattern singleton
    private static CommonValidator instance = new CommonValidator();

    public static CommonValidator getInstance()
    {
        return instance;
    }

    private CommonValidator() {}


    // Regex per l'email (Standard RFC 5322 semplificato, copre il 99.9% dei casi reali)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    // Regex per il Codice Fiscale Italiano (SSN)
    private static final String SSN_REGEX = "^[A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z]$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    private static final Pattern SSN_PATTERN = Pattern.compile(SSN_REGEX);

    /**
     * Valida l'email senza librerie esterne
     */
    public boolean validEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Valida il Codice Fiscale (SSN) italiano
     */
    public boolean validSSN(String ssn) {
        if (ssn == null || ssn.isEmpty()) {
            return false;
        }
        // Il CF deve essere sempre trattato in maiuscolo
        return SSN_PATTERN.matcher(ssn.toUpperCase()).matches();
    }

    public boolean emptyString(String s)
    {
        return s==null || s.isBlank();
    }

}
