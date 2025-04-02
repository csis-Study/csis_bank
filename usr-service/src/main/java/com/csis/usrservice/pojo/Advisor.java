package com.csis.usrservice.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.sql.Date;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/25
 */

@Entity
@Table(name = "advisor",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_id_number", columnNames = "id_number"),
                @UniqueConstraint(name = "uk_phone_number", columnNames = "phone_number"),
                @UniqueConstraint(name = "uk_email", columnNames = "email")
        })
@Data
public class Advisor {

    @Id
    @Column(name = "advisor_id", length = 20, nullable = false)
    private String advisorId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private java.sql.Date birthDate;

    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    @Column(name = "id_type", length = 10, nullable = false)
    private String idType;

    @Column(name = "id_number", length = 30, nullable = false, unique = true)
    private String idNumber;

    @Column(name = "phone_number", length = 20, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "account", length = 10, nullable = false)
    private String account;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "role", length = 10, nullable = false)
    private String role;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name = "last_update_time", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdateTime;

    // Getter 和 Setter 方法

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}