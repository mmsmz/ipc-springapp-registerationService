package com.ipc.registrationservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "otpmail")
public class OtpMailEntity {

    @Id
    @GenericGenerator(name = "sequence_otpMail_id", strategy = "com.ipc.registrationservice.util.OtpMailIdGenerator")
    @GeneratedValue(generator = "sequence_otpMail_id")
    @Column(name = "otpmailid")
    private String otpMailId;

    @Column(name = "userid")
    private String userId;

    @Column(name = "otppinnumber")
    private Integer otpPinNumber;

    @Column(name = "createddate")
    private LocalDate createdDate;

    public String getOtpMailId() {
        return otpMailId;
    }

    public void setOtpMailId(String otpMailId) {
        this.otpMailId = otpMailId;
    }

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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
