package com.web.ashoktractors;

import jakarta.persistence.*;

@Entity
@Table(name = "tractors")
public class TractorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tractor_id")
    private int tractor_id;

    @Column(name = "model_name")
    private String model_name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "engine_power")
    private String engine_power;

    @Column(name = "current_count")
    private int current_count;

    @Column(name = "availability")
    private String availability;

    @Column(name = "price")
    private double price;

    // Getters and Setters
    public int getTractor_id() {
        return tractor_id;
    }

    public void setTractor_id(int tractor_id) {
        this.tractor_id = tractor_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine_power() {
        return engine_power;
    }

    public void setEngine_power(String engine_power) {
        this.engine_power = engine_power;
    }

    public int getCurrent_count() {
        return current_count;
    }

    public void setCurrent_count(int current_count) {
        this.current_count = current_count;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
