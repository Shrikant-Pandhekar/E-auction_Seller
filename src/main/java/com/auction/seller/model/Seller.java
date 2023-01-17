package com.auction.seller.model;

import javax.persistence.*;
import javax.validation.constraints.*;

 
 

@Entity
@Table(name = "seller")
public class Seller {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "first_name", nullable = false)
	@Size(min=3,max=25)
	@NotNull
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	@Size(min=3,max=30)
	@NotNull
	private String lastName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="pin")
	private String pin;
	
	@Column(name="phone")
	@NotEmpty
	@Size(min=10,max=10)
	@Pattern(regexp="(^\\d{10})")
	private String phone;
	
	@Column(name="email")
	@NotNull
	@Email
	private String email;

	public Seller() {
		super();
	}

	public Seller(int id, @Size(min = 3, max = 25) @NotNull String firstName,
			@Size(min = 3, max = 30) @NotNull String lastName, String address, String city, String state, String pin,
			@NotEmpty @Size(min = 10, max = 10) @Pattern(regexp = "(^\\d{10})") String phone,
			@NotNull @Email String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 
	
	
}
