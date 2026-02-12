package com.generation.javaeat.model.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void testValidRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertTrue(restaurant.isValid());
        assertTrue(restaurant.getErrors().isEmpty());
    }

    @Test
    void testNullName() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(null);
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Name cannot be null or empty"));
    }

    @Test
    void testEmptyName() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Name cannot be null or empty"));
    }

    @Test
    void testInvalidEmail() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("invalid-email");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Email is not valid"));
    }

    @Test
    void testNullEmail() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail(null);
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Email is not valid"));
    }

    @Test
    void testNullPassword() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw(null);
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Password cannot be null or empty"));
    }

    @Test
    void testNullAddress() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress(null);
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Address cannot be null or empty"));
    }

    @Test
    void testNegativeCapacity() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(-10);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Capacity cannot be negative"));
    }

    @Test
    void testZeroCapacity() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(0);
        restaurant.setCity(new City());
        restaurant.setDeliveries(new ArrayList<>());

        assertTrue(restaurant.isValid());
    }

    @Test
    void testNullCity() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(null);
        restaurant.setDeliveries(new ArrayList<>());

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("City cannot be null"));
    }

    @Test
    void testNullDeliveries() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Il Gusto");
        restaurant.setEmail("info@ilgusto.com");
        restaurant.setPw("password123");
        restaurant.setAddress("Via Milano 10");
        restaurant.setCapacity(50);
        restaurant.setCity(new City());
        restaurant.setDeliveries(null);

        assertFalse(restaurant.isValid());
        assertTrue(restaurant.getErrors().contains("Deliveries cannot be null"));
    }
}
