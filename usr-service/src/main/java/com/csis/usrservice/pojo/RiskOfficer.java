package com.csis.usrservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.sql.Date;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/25
 */

@Entity
@Data
@Table(name = "risk_officer")
public class RiskOfficer {

    @Id
    @Column(name = "risk_officer_id", length = 18, nullable = false)
    private String riskOfficerId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "gender", length = 1, columnDefinition = "char(1)")
    private String gender;

    @Column(name = "birthday")
    private java.sql.Date birthday;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "id_type", length = 20)
    private String idType;

    @Column(name = "id_number", length = 30)
    private String idNumber;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "status", length = 10, nullable = false)
    private String status;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    @Column(name = "role", length = 20, nullable = false)
    private String role;


//    @PrePersist
//    public void onCreate(){
//        if(riskOfficerId == null)
//    }
// Getter 和 Setter 方法

    public String getRiskOfficerId() {
        return riskOfficerId;
    }

    public void setRiskOfficerId(String riskOfficerId) {
        this.riskOfficerId = riskOfficerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}