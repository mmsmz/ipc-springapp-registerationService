package com.ipc.registrationservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "studentpurchase")
public class StudentPurchaseEntity {

    @Id
    @GenericGenerator(name = "sequence_studpurid_id", strategy = "com.ipc.registrationservice.util.StudentPurchaseIdGenerator")
    @GeneratedValue(generator = "sequence_studpurid_id")
    @Column(name = "studpurid")
    private String studentPurchaseId;

    @Column(name = "userid")
    private String userId;

    @Column(name = "crsprid")
    private String coursePriceId;

    @Column(name = "crsschedid")
    private String courseScheduleId;

    @Column(name = "apvlstatid")
    private String approvalStatusId;

    public StudentPurchaseEntity(){}

    public StudentPurchaseEntity(String userId, String coursePriceId, String courseScheduleId, String approvalStatusId) {
        this.userId = userId;
        this.coursePriceId = coursePriceId;
        this.courseScheduleId = courseScheduleId;
        this.approvalStatusId = approvalStatusId;
    }

    public StudentPurchaseEntity(String userId, String coursePriceId, String courseScheduleId) {
        this.userId = userId;
        this.coursePriceId = coursePriceId;
        this.courseScheduleId = courseScheduleId;
    }

    public String getStudentPurchaseId() {
        return studentPurchaseId;
    }

    public void setStudentPurchaseId(String studentPurchaseId) {
        this.studentPurchaseId = studentPurchaseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoursePriceId() {
        return coursePriceId;
    }

    public void setCoursePriceId(String coursePriceId) {
        this.coursePriceId = coursePriceId;
    }

    public String getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(String courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    public String getApprovalStatusId() {
        return approvalStatusId;
    }

    public void setApprovalStatusId(String approvalStatusId) {
        this.approvalStatusId = approvalStatusId;
    }
}
