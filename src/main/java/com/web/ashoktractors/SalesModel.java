package com.web.ashoktractors;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "spare_part_sales")
public class SalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private int saleId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "part_id", nullable = false)
    private int partId;

    @Column(name = "sale_quantity", nullable = false)
    private int saleQuantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    
    @Transient
    private double totalPrice;

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalPrice() {
        return price * saleQuantity;
    }
}
