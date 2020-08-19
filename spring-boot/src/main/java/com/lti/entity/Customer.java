package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer_table")
@NamedQuery(name= "fetch-all", query = "select c from Customer c")
public class Customer {

	@Id
	private int id;
	private String name;
	private LocalDate dateOfBirth;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.MERGE)
	private List<Order> orders;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.MERGE)
	private List<Xyz> xyz;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
