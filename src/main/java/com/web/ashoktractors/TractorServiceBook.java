package com.web.ashoktractors;

import java.sql.Date;


import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "tractor_service_book")
public class TractorServiceBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "tractor_number", nullable = false)
    private String tractorNumber;

    @Column(name = "service_date")
    private Date serviceDate ;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_details", columnDefinition = "TEXT")
    private String serviceDetails;

    
    @Column(name = "next_service_date", insertable = false, updatable = false)
    private Date nextServiceDate;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "mechanic_name")
    private String mechanicName;

    @Column(name = "status")
    private String status = "processing";

	public TractorServiceBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTractorNumber() {
		return tractorNumber;
	}

	public void setTractorNumber(String tractorNumber) {
		this.tractorNumber = tractorNumber;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public Date getNextServiceDate() {
		return nextServiceDate;
	}

	public void setNextServiceDate(Date nextServiceDate) {
		this.nextServiceDate = nextServiceDate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getMechanicName() {
		return mechanicName;
	}

	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
    
    
    
    
    
    
}