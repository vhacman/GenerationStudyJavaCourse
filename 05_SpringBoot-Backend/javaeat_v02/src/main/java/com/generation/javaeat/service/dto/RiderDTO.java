package com.generation.javaeat.service.dto;

import java.util.List;

public class RiderDTO
{

    private int id;
    private String email;
    private String pw;
    private String legalName;
    private int serviceCost;
    // Aggiunto: status mancava nel DTO ma e' presente nell'entity Rider (AVAILABLE/NOTAVAILABLE)
    private String status;
    private int positionX;
    private int positionY;
    // Rider 1:M Delivery - storico delle consegne gestite dal rider
    private List<DeliveryDTO> deliveries;

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getLegalName() { return legalName; }
    public String getPw() { return pw; }
    public int getServiceCost() { return serviceCost; }
    public String getStatus() { return status; }
    public List<DeliveryDTO> getDeliveries() { return deliveries; }
    public int getPositionX() { return positionX; }
    public int getPositionY() { return positionY; }

    public void setId(int id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setLegalName(String legalName) { this.legalName = legalName; }
    public void setPw(String pw) { this.pw = pw; }
    public void setServiceCost(int cost) { this.serviceCost = cost; }
    public void setStatus(String status) { this.status = status; }
    public void setDeliveries(List<DeliveryDTO> deliveries) { this.deliveries = deliveries; }
    public void setPositionX(int positionX) { this.positionX = positionX; }
    public void setPositionY(int positionY) { this.positionY = positionY; }
}
