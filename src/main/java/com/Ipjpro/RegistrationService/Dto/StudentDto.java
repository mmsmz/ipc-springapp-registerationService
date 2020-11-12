package com.Ipjpro.RegistrationService.Dto;

public class StudentDto {

	private String firstName;
	private String lastName;
	private String nicNr;
	private String email;
	private String mobile;
	private String password;
	private Character gender;

	@Override
	public String toString() {
		return "StudentDto{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", nicNr='" + nicNr + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", password='" + password + '\'' +
				", gender=" + gender +
				'}';
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
