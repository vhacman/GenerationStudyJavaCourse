package com.generation.javaeat.service.dto;

import java.util.List;

public class CostumerDTO {

    private int id;
    private String email;
    private String pw;
    private String legalName;
    private String address;
    private CityDTO city;
    private List<DeliveryDTO> deliveries;
    private int riderId;

    public CostumerDTO() {}

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPw() { return pw; }
    public String getLegalName() { return legalName; }
    public String getAddress() { return address; }
    public CityDTO getCity() { return city; }
    public List<DeliveryDTO> getDeliveries() { return deliveries; }
    public int getRiderId() { return riderId; }

    public void setId(int id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPw(String pw) { this.pw = pw; }
    public void setLegalName(String legalName) { this.legalName = legalName; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(CityDTO city) { this.city = city; }
    public void setDeliveries(List<DeliveryDTO> deliveries) { this.deliveries = deliveries; }
    public void setRiderId(int riderId) { this.riderId = riderId; }
}
