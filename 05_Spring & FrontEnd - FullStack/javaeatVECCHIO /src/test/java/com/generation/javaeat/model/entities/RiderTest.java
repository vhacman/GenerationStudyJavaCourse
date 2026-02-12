package com.generation.javaeat.model.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RiderTest {

    @Test
    void testValidRider() {
        Rider rider = new Rider();
        rider.setEmail("rider@example.com");
        rider.setPw("password123");
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(500);
        rider.setDeliveries(new ArrayList<>());

        assertTrue(rider.isValid());
        assertTrue(rider.getErrors().isEmpty());
    }

    @Test
    void testInvalidEmail() {
        Rider rider = new Rider();
        rider.setEmail("invalid-email");
        rider.setPw("password123");
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(500);
        rider.setDeliveries(new ArrayList<>());

        assertFalse(rider.isValid());
        assertTrue(rider.getErrors().contains("Email is not valid"));
    }

    @Test
    void testNullEmail() {
        Rider rider = new Rider();
        rider.setEmail(null);
        rider.setPw("password123");
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(500);
        rider.setDeliveries(new ArrayList<>());

        assertFalse(rider.isValid());
        assertTrue(rider.getErrors().contains("Email is not valid"));
    }

    @Test
    void testNullPassword() {
        Rider rider = new Rider();
        rider.setEmail("rider@example.com");
        rider.setPw(null);
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(500);
        rider.setDeliveries(new ArrayList<>());

        assertFalse(rider.isValid());
        assertTrue(rider.getErrors().contains("Password cannot be null or empty"));
    }

    @Test
    void testNullLegalName() {
        Rider rider = new Rider();
        rider.setEmail("rider@example.com");
        rider.setPw("password123");
        rider.setLegalName(null);
        rider.setServiceCost(500);
        rider.setDeliveries(new ArrayList<>());

        assertFalse(rider.isValid());
        assertTrue(rider.getErrors().contains("LegalName cannot be null or empty"));
    }

    @Test
    void testNegativeServiceCost() {
        Rider rider = new Rider();
        rider.setEmail("rider@example.com");
        rider.setPw("password123");
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(-100);
        rider.setDeliveries(new ArrayList<>());

        assertFalse(rider.isValid());
        assertTrue(rider.getErrors().contains("ServiceCost cannot be null or negative"));
    }

    @Test
    void testNullDeliveries() {
        Rider rider = new Rider();
        rider.setEmail("rider@example.com");
        rider.setPw("password123");
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(500);
        rider.setDeliveries(null);

        assertFalse(rider.isValid());
        assertTrue(rider.getErrors().contains("Deliveries cannot be null"));
    }

    @Test
    void testZeroServiceCost() {
        Rider rider = new Rider();
        rider.setEmail("rider@example.com");
        rider.setPw("password123");
        rider.setLegalName("Luigi Bianchi");
        rider.setServiceCost(0);
        rider.setDeliveries(new ArrayList<>());

        assertTrue(rider.isValid());
    }
}
