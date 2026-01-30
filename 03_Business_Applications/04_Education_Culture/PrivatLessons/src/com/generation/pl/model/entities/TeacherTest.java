package com.generation.pl.model.entities;

import org.junit.jupiter.api.Test;

class TeacherTest
{

    @Test
    void testIsValid()
    {

        // 1 - creare un Teacher vuoto
        Teacher t = new Teacher();

        // 2 - verificare che non sia valido
        assert(!t.isValid());

        // 2 - dargli nome, cognome, ssn, email e password
        t.setFirstName("Mario");
        t.setLastName("Rossi");
        t.setSsn("RSSMRA80A01H501U");
        t.setEmail("mario.rossi@example.com");
        t.setPassword("password123");

        // verificare che non è ancora valido
        assert(!t.isValid());

        // 3 - dargli la bio e il price per lesson a 30 euro
        t.setBio("Sono un insegnante con 10 anni di esperienza.");
        t.setPricePerLesson(30);

        assert(!t.isValid());

        // 4 - assegnargli come subjects java e sql
        t.setSubjects("JAVA,SQL");

        // verificare che non è ancora valido (manca lo status)
        assert(!t.isValid());

        // 5 - impostare lo status ad ACTIVE
        t.setStatus("ACTIVE");

        // ora è valido
        assert(t.isValid());
    }
}
