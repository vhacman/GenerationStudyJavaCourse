package com.generation.javaeat.model.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void testValidDelivery() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertTrue(delivery.isValid());
        assertTrue(delivery.getErrors().isEmpty());
    }

    @Test
    void testNullDeliveryTimeOpen() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(null);
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("DeliveryTimeOpen cannot be null"));
    }

    @Test
    void testNullStatus() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(null);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("Status cannot be null or empty"));
    }

    @Test
    void testInvalidStatus() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus("INVALID");
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("Status must be OPEN, DELIVERED, or CANCELED"));
    }

    @Test
    void testNullRestaurant() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(null);
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("Restaurant cannot be null"));
    }

    @Test
    void testNullCustomer() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(null);
        delivery.setRider(new Rider());

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("Customer cannot be null"));
    }

    @Test
    void testNullRider() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(null);

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("Rider cannot be null"));
    }

    @Test
    void testNegativePrice() {
        Delivery delivery = new Delivery();
        delivery.setDescription("Test delivery");
        delivery.setStatus(Delivery.STATUS_OPEN);
        delivery.setPrice(-5.0);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertFalse(delivery.isValid());
        assertTrue(delivery.getErrors().contains("Price cannot be negative"));
    }

    @Test
    void testStatusDelivered() {
        Delivery delivery = new Delivery();
        delivery.setStatus(Delivery.STATUS_DELIVERED);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertTrue(delivery.isValid());
    }

    @Test
    void testStatusCanceled() {
        Delivery delivery = new Delivery();
        delivery.setStatus(Delivery.STATUS_CANCELED);
        delivery.setPrice(10.50);
        delivery.setDeliveryTimeOpen(LocalDateTime.now());
        delivery.setRestaurant(new Restaurant());
        delivery.setCustomer(new Customer());
        delivery.setRider(new Rider());

        assertTrue(delivery.isValid());
    }
}
