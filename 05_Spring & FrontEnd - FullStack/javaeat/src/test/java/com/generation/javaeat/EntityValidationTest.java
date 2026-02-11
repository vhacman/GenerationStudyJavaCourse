package com.generation.javaeat;

import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.model.entities.Customer;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.model.entities.City;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityValidationTest
{
    @Test
    public void testRestaurantValidation_Valid()
    {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setEmail("test@test.com");
        restaurant.setPw("password123");
        restaurant.setAddress("123 Test St");
        restaurant.setCapacity(10);

        List<String> errors = restaurant.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testRestaurantValidation_NullName()
    {
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail("test@test.com");
        restaurant.setPw("password123");
        restaurant.setAddress("123 Test St");

        List<String> errors = restaurant.getErrors();

        assertTrue(errors.contains("Name cannot be null or empty"));
    }

    @Test
    public void testRestaurantValidation_NullEmail()
    {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setPw("password123");
        restaurant.setAddress("123 Test St");

        List<String> errors = restaurant.getErrors();

        assertTrue(errors.contains("Email cannot be null or empty"));
    }

    @Test
    public void testCustomerValidation_Valid()
    {
        Customer customer = new Customer();
        customer.setEmail("customer@test.com");
        customer.setPw("password123");
        customer.setLegalName("John Doe");
        customer.setAddress("123 Main St");

        List<String> errors = customer.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testCustomerValidation_NullLegalName()
    {
        Customer customer = new Customer();
        customer.setEmail("customer@test.com");
        customer.setPw("password123");
        customer.setAddress("123 Main St");

        List<String> errors = customer.getErrors();

        assertTrue(errors.contains("LegalName cannot be null or empty"));
    }

    @Test
    public void testRiderValidation_Valid()
    {
        Rider rider = new Rider();
        rider.setEmail("rider@test.com");
        rider.setPw("password123");
        rider.setLegalName("Jane Rider");
        rider.setServiceCost(5);

        List<String> errors = rider.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testRiderValidation_NegativeServiceCost()
    {
        Rider rider = new Rider();
        rider.setEmail("rider@test.com");
        rider.setPw("password123");
        rider.setLegalName("Jane Rider");
        rider.setServiceCost(-1);

        List<String> errors = rider.getErrors();

        assertTrue(errors.contains("ServiceCost cannot be null or negative"));
    }

    @Test
    public void testRServiceiderValidation_NullCost()
    {
        Rider rider = new Rider();
        rider.setEmail("rider@test.com");
        rider.setPw("password123");
        rider.setLegalName("Jane Rider");

        List<String> errors = rider.getErrors();

        assertTrue(errors.contains("ServiceCost cannot be null or negative"));
    }

    @Test
    public void testRiderValidation_NullLegalName()
    {
        Rider rider = new Rider();
        rider.setEmail("rider@test.com");
        rider.setPw("password123");
        rider.setServiceCost(5);

        List<String> errors = rider.getErrors();

        assertTrue(errors.contains("LegalName cannot be null or empty"));
    }

    @Test
    public void testCityValidation_Valid()
    {
        City city = new City();
        city.setName("Rome");
        city.setProvince("RM");

        List<String> errors = city.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testCityValidation_NullName()
    {
        City city = new City();
        city.setProvince("RM");

        List<String> errors = city.getErrors();

        assertTrue(errors.contains("Name cannot be null or empty"));
    }
}
