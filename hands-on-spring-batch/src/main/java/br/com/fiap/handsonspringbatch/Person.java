package br.com.fiap.handsonspringbatch;

import java.time.LocalDateTime;

public class Person {

	private String name;

	private String streetName;

	private String number;

	private String city;

	private String country;

	private String email;

	private String phoneNumber;

	private LocalDateTime createDateTime;


	public Person() {
		super();
	}

	public Person(String name, String streetName, String number, String city, String country, String email, String phoneNumber) {
		this.name = name;
		this.streetName = streetName;
		this.number = number;
		this.city = city;
		this.country = country;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
}