package com.generation.javaeat;

import com.generation.javaeat.model.entities.*;
import com.generation.javaeat.model.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            CityRepository cityRepo,
            CustomerRepository customerRepo,
            RestaurantRepository restaurantRepo,
            RiderRepository riderRepo,
            DeliveryRepository deliveryRepo) {
        return args -> {
            List<City> cities = Arrays.asList(
                createCity("Milano", "MI"),
                createCity("Roma", "RM"),
                createCity("Napoli", "NA"),
                createCity("Torino", "TO"),
                createCity("Firenze", "FI")
            );
            cityRepo.saveAll(cities);

            List<Customer> customers = Arrays.asList(
                createCustomer("mario.rossi@email.com", "password123", "Mario Rossi", "Via Roma 1", cities.get(0)),
                createCustomer("anna.bianchi@email.com", "password123", "Anna Bianchi", "Via Milano 2", cities.get(1)),
                createCustomer("luigi.verdi@email.com", "password123", "Luigi Verdi", "Via Napoli 3", cities.get(2)),
                createCustomer("giulia.gialli@email.com", "password123", "Giulia Gialli", "Via Torino 4", cities.get(3)),
                createCustomer("paolo.blu@email.com", "password123", "Paolo Blu", "Via Firenze 5", cities.get(4))
            );
            customerRepo.saveAll(customers);

            List<Restaurant> restaurants = Arrays.asList(
                createRestaurant("pizzeria.uno@email.com", "password123", "Pizzeria Uno", "Via Pizza 1", 50, cities.get(0)),
                createRestaurant("trattoria.due@email.com", "password123", "Trattoria Due", "Via Pasta 2", 40, cities.get(1)),
                createRestaurant("ristorante.tre@email.com", "password123", "Ristorante Tre", "Via Gourmet 3", 60, cities.get(2)),
                createRestaurant("sushi.quattro@email.com", "password123", "Sushi Quattro", "Via Tokyo 4", 35, cities.get(3)),
                createRestaurant("burger.cinque@email.com", "password123", "Burger Cinque", "Via America 5", 45, cities.get(4))
            );
            restaurantRepo.saveAll(restaurants);

            List<Rider> riders = Arrays.asList(
                createRider("rider.uno@email.com", "password123", "Marco Neri", 5),
                createRider("rider.due@email.com", "password123", "Laura Arancioni", 6),
                createRider("rider.tre@email.com", "password123", "Giuseppe Bianchi", 4),
                createRider("rider.quattro@email.com", "password123", "Sofia Verdi", 7),
                createRider("rider.cinque@email.com", "password123", "Luca Blu", 5)
            );
            riderRepo.saveAll(riders);

            List<Delivery> deliveries = Arrays.asList(
                createDelivery("Pizza margherita", Delivery.STATUS_OPEN, 15.50, LocalDateTime.now().minusHours(2), restaurants.get(0), customers.get(0), riders.get(0)),
                createDelivery("Pasta carbonara", Delivery.STATUS_DELIVERED, 18.00, LocalDateTime.now().minusDays(1), restaurants.get(1), customers.get(1), riders.get(1)),
                createDelivery("Sushi platter", Delivery.STATUS_OPEN, 35.00, LocalDateTime.now().minusHours(1), restaurants.get(2), customers.get(2), riders.get(2)),
                createDelivery("Hamburger XL", Delivery.STATUS_CANCELED, 12.00, LocalDateTime.now().minusDays(2), restaurants.get(3), customers.get(3), riders.get(3)),
                createDelivery("Bistecca fiorentina", Delivery.STATUS_OPEN, 45.00, LocalDateTime.now().minusMinutes(30), restaurants.get(4), customers.get(4), riders.get(4))
            );
            deliveryRepo.saveAll(deliveries);
        };
    }

    private City createCity(String name, String province) {
        City city = new City();
        city.setName(name);
        city.setProvince(province);
        return city;
    }

    private Customer createCustomer(String email, String pw, String legalName, String address, City city) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPw(pw);
        customer.setLegalName(legalName);
        customer.setAddress(address);
        customer.setCity(city);
        return customer;
    }

    private Restaurant createRestaurant(String email, String pw, String name, String address, int capacity, City city) {
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail(email);
        restaurant.setPw(pw);
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setCapacity(capacity);
        restaurant.setCity(city);
        return restaurant;
    }

    private Rider createRider(String email, String pw, String legalName, int serviceCost) {
        Rider rider = new Rider();
        rider.setEmail(email);
        rider.setPw(pw);
        rider.setLegalName(legalName);
        rider.setServiceCost(serviceCost);
        return rider;
    }

    private Delivery createDelivery(String description, String status, double price, LocalDateTime time, Restaurant restaurant, Customer customer, Rider rider) {
        Delivery delivery = new Delivery();
        delivery.setDescription(description);
        delivery.setStatus(status);
        delivery.setPrice(price);
        delivery.setDeliveryTimeOpen(time);
        delivery.setRestaurant(restaurant);
        delivery.setCustomer(customer);
        delivery.setRider(rider);
        return delivery;
    }
}
