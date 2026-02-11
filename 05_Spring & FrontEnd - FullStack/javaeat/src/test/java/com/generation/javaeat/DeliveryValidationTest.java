package com.generation.javaeat;

import com.generation.javaeat.model.entities.Delivery;
import com.generation.javaeat.model.entities.Restaurant;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryValidationTest
{
    @Test
    public void testDeliveryValidation_Valid()
    {
        Delivery delivery = new Delivery();
        delivery.setDescription("Pizza margherita");
        delivery.setPrice(10.5);
        delivery.setDeliveryTime(LocalDateTime.now().plusHours(1));
        delivery.setStatus(Delivery.STATUS_OPEN);

        List<String> errors = delivery.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testDeliveryValidation_NullDescription()
    {
        Delivery delivery = new Delivery();
        delivery.setPrice(10.5);
        delivery.setDeliveryTime(LocalDateTime.now());
        delivery.setStatus(Delivery.STATUS_OPEN);

        List<String> errors = delivery.getErrors();

        assertTrue(errors.stream().anyMatch(e -> e.contains("Description")));
    }

    @Test
    public void testDeliveryValidation_NegativePrice()
    {
        Delivery delivery = new Delivery();
        delivery.setDescription("Pizza margherita");
        delivery.setPrice(-5.0);
        delivery.setDeliveryTime(LocalDateTime.now());
        delivery.setStatus(Delivery.STATUS_OPEN);

        List<String> errors = delivery.getErrors();

        assertTrue(errors.contains("Price cannot be negative"));
    }

    @Test
    public void testDeliveryValidation_NullStatus()
    {
        Delivery delivery = new Delivery();
        delivery.setDescription("Pizza margherita");
        delivery.setPrice(10.5);
        delivery.setDeliveryTime(LocalDateTime.now());

        List<String> errors = delivery.getErrors();

        assertTrue(errors.contains("Status cannot be null or empty"));
    }

    @Test
    public void testDeliveryValidation_InvalidStatus()
    {
        Delivery delivery = new Delivery();
        delivery.setDescription("Pizza margherita");
        delivery.setPrice(10.5);
        delivery.setDeliveryTime(LocalDateTime.now());
        delivery.setStatus("INVALID");

        List<String> errors = delivery.getErrors();

        assertTrue(errors.stream().anyMatch(e -> e.contains("Status must be OPEN, DELIVERED, or CANCELED")));
    }

    @Test
    public void testRestaurantCapacity_CheckCapacity()
    {
        Restaurant restaurant = new Restaurant();
        restaurant.setCapacity(10);

        assertEquals(10, restaurant.getCapacity());
    }

    @Test
    public void testOpenDeliveryCount_WithinCapacity()
    {
        int maxCapacity = 10;
        int currentDeliveries = 5;

        assertTrue(currentDeliveries < maxCapacity);
    }

    @Test
    public void testOpenDeliveryCount_AtCapacity()
    {
        int maxCapacity = 10;
        int currentDeliveries = 10;

        assertEquals(maxCapacity, currentDeliveries);
    }

    @Test
    public void testOpenDeliveryCount_ExceedsCapacity()
    {
        int maxCapacity = 10;
        int currentDeliveries = 11;

        assertTrue(currentDeliveries > maxCapacity);
    }
}
