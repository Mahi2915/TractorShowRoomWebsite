package com.web.ashoktractors;

import java.sql.Date;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.*;
@Entity
@Table(name = "ServiceFeedback")
public class ServiceFeedBack {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;
	
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "tractor_number", length = 50)
    private String tractorNumber;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "feedback_date")
	private Date feedBackDate;

	public ServiceFeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getFeedBackDate() {
		return feedBackDate;
	}

	public void setFeedBackDate(Date feedBackDate) {
		this.feedBackDate = feedBackDate;
	}
    
    
    

}
