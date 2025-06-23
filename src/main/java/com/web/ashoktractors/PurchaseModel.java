package com.web.ashoktractors;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "purchases")
public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchaseId;  

    @Column(name = "part_id", nullable = false)
    private int partId;  

    @Column(name = "supplier_id", nullable = false)
    private int supplierId;  

    @Column(name = "quantity", nullable = false)
    private int quantity;  

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

	public PurchaseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

    // Getters and Setters
    
    
}

