package com.csis.clientservice.dto;

import com.csis.clientservice.pojo.Client;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 修改基本信息的请求参数封装
 */
@Data
public class BasicInfoDTO {
    @Size(max = 50, message = "姓名长度不能超过50")
    private String name;

    private Client.Gender gender;  // 性别枚举

    @Size(max = 11, message = "电话长度不能超过11")
    private String phoneNumber;

    @Size(max = 50, message = "国籍长度不能超过50")
    private String nationality;

    @Size(max = 25, message = "收入等级长度不能超过25")
    private String incomeLevel;

    private Client.Status status;  // 状态枚举

    private String remarks;        // 备注（无长度限制，但数据库为Lob类型）

    public @Size(max = 50, message = "姓名长度不能超过50") String getName() {
        return name;
    }

    public void setName(@Size(max = 50, message = "姓名长度不能超过50") String name) {
        this.name = name;
    }

    public Client.Gender getGender() {
        return gender;
    }

    public void setGender(Client.Gender gender) {
        this.gender = gender;
    }

    public @Size(max = 11, message = "电话长度不能超过11") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Size(max = 11, message = "电话长度不能超过11") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Size(max = 50, message = "国籍长度不能超过50") String getNationality() {
        return nationality;
    }

    public void setNationality(@Size(max = 50, message = "国籍长度不能超过50") String nationality) {
        this.nationality = nationality;
    }

    public @Size(max = 25, message = "收入等级长度不能超过25") String getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(@Size(max = 25, message = "收入等级长度不能超过25") String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    public Client.Status getStatus() {
        return status;
    }

    public void setStatus(Client.Status status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}