package com.web.ashoktractors;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "spare_parts")
public class SparesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "part_id")
    private int partId;

    @Column(name = "part_name", nullable = false)
    private String partName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "availability", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'Available'")
    private String availability = "Available";  

    @Column(name = "updated_at", columnDefinition = "DATETIME DEFAULT GETDATE()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Getters and Setters
    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
