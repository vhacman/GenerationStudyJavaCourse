package com.generation.pl.controller.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.generation.library.Console;
import com.generation.library.utils.ssnUtil;
import com.generation.pl.model.entities.Subject;
import com.generation.pl.model.entities.UserStatus;

/**
 * Utility class for console input validation.
 * Provides atomic validation methods for all user input types.
 * Each method ensures valid data before returning, using infinite loops with validation checks.
 */
public class InputValidator
{
    /**
     * Reads required input from user (non-empty string).
     * 
     * Infinite loop ensures method only returns when valid input is provided.
     * Used for fields like firstName, lastName, bio where empty values are not acceptable.
     * 
     * @param prompt Message to display to user (e.g., "Enter first name:")
     * @param error Error message to show if input is empty (e.g., "First name cannot be empty!")
     * @return Non-empty string (already trimmed, guaranteed not null or empty)
     */
    public static String readRequiredInput(String prompt, String error)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();
            if (input != null && !input.trim().isEmpty())
                return input.trim();
            Console.print(error + "\n");
        }
    }

    /**
     * Reads and validates Italian SSN (Codice Fiscale).
     * 
     * Italian SSN structure (16 alphanumeric characters):
     * - Position 1-6: 6 letters (consonants from surname + name)
     * - Position 7-8: 2 digits (birth year, last 2 digits)
     * - Position 9: 1 letter (birth month encoded A-T: A=Jan, B=Feb, ..., T=Dec)
     * - Position 10-11: 2 digits (day of month: 01-31 for males, 41-71 for females)
     * - Position 12-15: 1 letter + 3 digits (municipality/country code)
     * - Position 16: 1 letter (control character/checksum)
       * Process:
     * 1. Prompts user for SSN
     * 2. Normalizes input to uppercase (SSN is case-insensitive)
     * 3. Validates format using SsnUtil.hasValidSSN() from generation library
     * 4. If valid, returns normalized (uppercase) SSN
     * 5. If invalid, displays error and loops back
     * 
     * Regex pattern used: [A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]
     *
     * @return Valid SSN (16 uppercase characters, correct format according to Italian standards)
     */
    public static String readSSN()
    {
        while (true)
        {
            Console.print("Enter SSN:");
            String ssn = Console.readString().trim().toUpperCase();
            ssnUtil validator = new ssnUtil();
            if (validator.hasValidSSN(ssn))
                return ssn;
            Console.print("\nInvalid SSN!");
        }
    }

    /**
     * Reads and validates email address.
     * 
     * Email validation theory:
     * - Checks for presence of @ symbol (separates local part from domain)
     * - Checks for presence of dot (.) in domain part
     * 
     * Process:
     * 1. Prompts user for email
     * 2. Trims whitespace (emails should not have leading/trailing spaces)
     * 3. Checks for @ presence (required to separate user from domain)
     * 4. Checks for . presence (required for domain extension)
     * 5. If both present, returns email
     * 6. If validation fails, displays error and loops back
     * @return Valid email address containing @ and at least one dot
     */
    public static String readEmail()
    {
        while (true)
        {
            Console.print("Enter email:");
            String email = Console.readString().trim();
            if (email.contains("@") && email.contains("."))
                return email;
            Console.print("Invalid email! Must contain @ and a domain (e.g.: .com)\n");
        }
    }

    /**
     * Reads password with confirmation and length validation.
     * 
     * Password security best practices:
     * - Minimum 8 characters (industry standard for basic security)
     * - NO trim() applied - leading/trailing spaces can be part of password
     * - Confirmation required to prevent typos during registration
     * Two-level validation:
     * - Inner do-while: ensures minimum length
     * - Outer do-while: ensures passwords match
     * 
     * Security note: Password will be hashed by repository before database storage.
     * Never store plain-text passwords in database.
     * 
     * @return Validated password (minimum 8 characters, confirmed by user)
     */
    public static String readPasswordWithConfirmation()
    {
        String password;
        String confirmPassword;
        do
        {
            do
            {
                Console.print("Enter new Password (minimum 8 characters):");
                password = Console.readString();
                if (password.length() < 8)
                    Console.print("Password too short! Minimum 8 characters.\n");
            }
            while (password.length() < 8);
            Console.print("Confirm new password:");
            confirmPassword = Console.readString();
            if (!password.equals(confirmPassword))
                Console.print("Passwords do not match! Try again.\n");
        }
        while (!password.equals(confirmPassword));

        return password;
    }

    /**
     * Reads an integer with minimum value constraint.
     * Buffer consumption explanation:
     * - Console.readInt() fails on non-numeric input but leaves input in buffer
     * - Next readInt() call reads same bad input from buffer
     * - Console.readString() consumes and discards the bad input
     * - Next iteration starts with clean buffer
     * 
     * @param prompt Message to show user (should include minimum value info)
     * @param min Minimum accepted value (inclusive)
     * @return Valid integer >= min
     */
    public static int readIntWithMin(String prompt, int min)
    {
        while (true)
        {
            Console.print(prompt);
            try
            {
                int value = Console.readInt();
                if (value >= min)
                    return value;
                Console.print("The value must be at least " + min + "!\n");
            }
            catch (Exception e)
            {
                Console.print("Enter a valid number!\n");
                Console.readString();
            }
        }
    }
    
    /**
     * Reads and validates date string in ISO 8601 format (YYYY-MM-DD).
     * @param prompt Message to display to user
     * @return Validated date string in YYYY-MM-DD format
     */
    public static String readValidDate(String prompt)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString().trim();
            if (input.isEmpty())
            {
                Console.print("Date is required. Try again.\n");
                continue;
            }
            try
            {
                LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return input;
            }
            catch (DateTimeParseException e)
            {
                Console.print("Invalid date format! Use YYYY-MM-DD (e.g.: 2000-01-15)\n");
            }
        }
    }

    /**
     * Reads and validates date of birth 
     * 1. Cannot be in the future (impossible to be born in future)
     * 2. Minimum age 14 years (student eligibility requirement)
     * 3. Maximum age 100 years (data sanity check, prevents typos like 1024 instead of 2024)
     * 
     * @return Validated LocalDate for date of birth (14-100 years old, not in future)
     */
    public static LocalDate readDateOfBirth()
    {
        while (true)
        {
            Console.print("Date of birth (format YYYY-MM-DD):");
            String dobString = Console.readString().trim();

            try
            {
                LocalDate dob = LocalDate.parse(dobString);

                if (dob.isAfter(LocalDate.now()))
                {
                    Console.print("Date of birth cannot be in the future!\n");
                    continue;
                }
                if (dob.isAfter(LocalDate.now().minusYears(14)))
                {
                    Console.print("The student must be at least 14 years old!\n");
                    continue;
                }
                if (dob.isBefore(LocalDate.now().minusYears(100)))
                {
                    Console.print("Invalid date of birth!\n");
                    continue;
                }
                return dob;
            }
            catch (DateTimeParseException e)
            {
                Console.print("Invalid date format! Use YYYY-MM-DD (e.g.: 2000-01-15)\n");
            }
        }
    }

    /**
     * Reads and validates UserStatus enum from user input.
     * 
     * Java Enum validation:
     * - Enum.valueOf(String) converts String to enum constant
     * - Method is CASE-SENSITIVE: "active" != "ACTIVE"
     * - Throws IllegalArgumentException if string doesn't match any constant
     * - Must always use try-catch when parsing user input to enum
     * Valid values (as of current enum):
     * - ACTIVE: User can access system
     * - INACTIVE: User account suspended temporarily
     * - SUSPENDED: User account suspended permanently (requires admin reactivation)
     * 
     * Use case: Admin changing user status, user registration
     *
     * @return Validated UserStatus enum constant (ACTIVE, INACTIVE, or SUSPENDED)
     */
    public static UserStatus readUserStatus()
    {
        while (true)
        {
            Console.print("\n=== AVAILABLE STATUSES ===");
            for (UserStatus status : UserStatus.values())
                Console.print("- " + status.name());

            Console.print("\nNew status:");
            String statusStr = Console.readString().trim().toUpperCase();
            if (statusStr.isEmpty())
            {
                Console.print("Status is required. Try again.\n");
                continue;
            }
            try
            {
                return UserStatus.valueOf(statusStr);
            }
            catch (IllegalArgumentException e)
            {
                Console.print("Invalid status! Choose from: ACTIVE, INACTIVE, SUSPENDED\n");
            }
        }
    }

    /**
     * Reads and validates single Subject enum from user input.
     * @return Validated Subject enum constant
     */
    public static Subject readSubject()
    {
        while (true)
        {
            Console.print("\n=== AVAILABLE SUBJECTS ===");
            for (Subject s : Subject.values())
                Console.print("- " + s.name());
            Console.print("\nEnter subject:");
            String subjectStr = Console.readString().trim().toUpperCase();
            if (subjectStr.isEmpty())
            {
                Console.print("Subject is required. Try again.\n");
                continue;
            }
            try
            {
                return Subject.valueOf(subjectStr);
            }
            catch (IllegalArgumentException e)
            {
                Console.print("Invalid subject! Use one of the subjects listed above.\n");
            }
        }
    }

    /**
     * Reads and validates comma-separated list of Subject enums.
     * 
     * CSV (Comma-Separated Values) parsing with enum validation:
     * 
     * Input format: "subject1,subject2,subject3"
     * Example input: "java, sql, MATH" (mixed case, spaces allowed)
     * Example output: "JAVA,SQL,MATH" (normalized)
     * 
     */
    public static String readSubjectsCSV()
    {
        while (true)
        {
            Console.print("\n=== AVAILABLE SUBJECTS ===");
            for (Subject s : Subject.values())
                Console.print("- " + s.name());
            Console.print("\nEnter subjects (comma-separated, e.g.: JAVA,SQL,PROGRAMMING):");
            String subjects = Console.readString().trim().toUpperCase();
            if (subjects.isEmpty())
            {
                Console.print("At least one subject is required.\n");
                continue;
            }
            String[] subjectArray = subjects.split(",");
            boolean allValid = true;
            for (String subject : subjectArray)
            {
                try
                {
                    Subject.valueOf(subject.trim());
                }
                catch (IllegalArgumentException e)
                {
                    Console.print("Invalid subject: " + subject.trim() + "\n");
                    allValid = false;
                    break;
                }
            }
            if (allValid)
                return subjects;
        }
    }

}
