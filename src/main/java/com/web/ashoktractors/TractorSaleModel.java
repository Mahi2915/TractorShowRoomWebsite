package com.web.ashoktractors;

import java.sql.Date;


import jakarta.persistence.*;

@Entity
@Table(name = "tractor_sales")
public class TractorSaleModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private int saleId;

    @Column(name = "tractor_id", nullable = false)
    private int tractorId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "sale_quantity", nullable = false)
    private int saleQuantity;

    @Column(name = "sale_price", nullable = false)
    private double salePrice;

    
    @Column(name = "total_price", insertable = false, updatable = false)
    private double totalPrice;

    @Column(name = "sale_date")
    private Date saleDate;

	public TractorSaleModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public int getTractorId() {
		return tractorId;
	}

	public void setTractorId(int tractorId) {
		this.tractorId = tractorId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

    


}