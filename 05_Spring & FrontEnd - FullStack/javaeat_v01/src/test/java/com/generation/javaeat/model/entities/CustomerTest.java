package com.generation.javaeat.model.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testValidCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPw("password123");
        customer.setLegalName("Mario Rossi");
        customer.setAddress("Via Roma 1");
        customer.setCity(new City());
        customer.setDeliveries(new ArrayList<>());

        assertTrue(customer.isValid());
        assertTrue(customer.getErrors().isEmpty());
    }

    @Test
    void testInvalidEmail() {
        Customer customer = new Customer();
        customer.setEmail("invalid-email");
        customer.setPw("password123");
        customer.setLegalName("Mario Rossi");
        customer.setAddress("Via Roma 1");
        customer.setCity(new City());
        customer.setDeliveries(new ArrayList<>());

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("Email is not valid"));
    }

    @Test
    void testNullEmail() {
        Customer customer = new Customer();
        customer.setEmail(null);
        customer.setPw("password123");
        customer.setLegalName("Mario Rossi");
        customer.setAddress("Via Roma 1");
        customer.setCity(new City());
        customer.setDeliveries(new ArrayList<>());

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("Email is not valid"));
    }

    @Test
    void testNullPassword() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPw(null);
        customer.setLegalName("Mario Rossi");
        customer.setAddress("Via Roma 1");
        customer.setCity(new City());
        customer.setDeliveries(new ArrayList<>());

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("Password cannot be null or empty"));
    }

    @Test
    void testNullLegalName() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPw("password123");
        customer.setLegalName(null);
        customer.setAddress("Via Roma 1");
        customer.setCity(new City());
        customer.setDeliveries(new ArrayList<>());

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("LegalName cannot be null or empty"));
    }

    @Test
    void testNullAddress() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPw("password123");
        customer.setLegalName("Mario Rossi");
        customer.setAddress(null);
        customer.setCity(new City());
        customer.setDeliveries(new ArrayList<>());

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("Address cannot be null or empty"));
    }

    @Test
    void testNullCity() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPw("password123");
        customer.setLegalName("Mario Rossi");
        customer.setAddress("Via Roma 1");
        customer.setCity(null);
        customer.setDeliveries(new ArrayList<>());

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("City cannot be null"));
    }

    @Test
    void testNullDeliveries() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPw("password123");
        customer.setLegalName("Mario Rossi");
        customer.setAddress("Via Roma 1");
        customer.setCity(new City());
        customer.setDeliveries(null);

        assertFalse(customer.isValid());
        assertTrue(customer.getErrors().contains("Deliveries cannot be null"));
    }
}
