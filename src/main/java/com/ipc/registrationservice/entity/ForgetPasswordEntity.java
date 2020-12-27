package com.ipc.registrationservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forgetpassword")
public class ForgetPasswordEntity {

	@Id
	@Column(name = "userid")
	private String userId;

	@Column(name = "pin")
	private Integer otpPinNumber;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getOtpPinNumber() {
		return otpPinNumber;
	}

	public void setOtpPinNumber(Integer otpPinNumber) {
		this.otpPinNumber = otpPinNumber;
	}

}
