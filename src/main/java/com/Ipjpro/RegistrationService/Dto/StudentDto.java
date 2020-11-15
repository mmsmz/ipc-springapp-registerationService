package com.Ipjpro.RegistrationService.Dto;

import java.time.Instant;

public class StudentDto {

	private String firstName;
	private String lastName;
	private String nicNr;
	private String email;
	private String mobile;
	private Character gender;
	private Instant date;
	private String userType;
	private String password;
	private Byte loginStatus;

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

	public String getNicNr() {
		return nicNr;
	}

	public void setNicNr(String nicNr) {
		this.nicNr = nicNr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(byte loginStatus) {
		this.loginStatus = loginStatus;
	}
}
