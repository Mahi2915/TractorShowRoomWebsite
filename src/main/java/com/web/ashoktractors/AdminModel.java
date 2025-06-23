package com.web.ashoktractors;

import jakarta.persistence.*;

@Entity
@Table(name = "admintractor")
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id; // auto-generated ID

    private String name;
    private String username;
    private String password;
	public AdminModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    





}
