package com.generation.javaeat.model.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void testValidCity() {
        City city = new City();
        city.setName("Roma");
        city.setProvince("RM");
        city.setRestaurants(new ArrayList<>());

        assertTrue(city.isValid());
        assertTrue(city.getErrors().isEmpty());
    }

    @Test
    void testNullName() {
        City city = new City();
        city.setName(null);
        city.setProvince("RM");
        city.setRestaurants(new ArrayList<>());

        assertFalse(city.isValid());
        assertTrue(city.getErrors().contains("Name cannot be null or empty"));
    }

    @Test
    void testEmptyName() {
        City city = new City();
        city.setName("");
        city.setProvince("RM");
        city.setRestaurants(new ArrayList<>());

        assertFalse(city.isValid());
        assertTrue(city.getErrors().contains("Name cannot be null or empty"));
    }

    @Test
    void testNullProvince() {
        City city = new City();
        city.setName("Roma");
        city.setProvince(null);
        city.setRestaurants(new ArrayList<>());

        assertFalse(city.isValid());
        assertTrue(city.getErrors().contains("Province cannot be null or empty"));
    }

    @Test
    void testNullRestaurants() {
        City city = new City();
        city.setName("Roma");
        city.setProvince("RM");
        city.setRestaurants(null);

        assertFalse(city.isValid());
        assertTrue(city.getErrors().contains("Restaurants cannot be null"));
    }
}
