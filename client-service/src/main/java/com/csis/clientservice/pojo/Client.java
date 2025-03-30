package com.csis.clientservice.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "Client")  // 对应数据库中的 Client 表
public class Client {

    // 客户ID（18位纯数字，示例：100010000000123456）
    @Id
    @GeneratedValue(generator = "client_id_generator") // 指定自定义生成器
    @Column(name = "usr_id", length = 18, nullable = false)
    @Pattern(regexp = "^\\d{18}$", message = "客户ID必须为18位纯数字") // 保留校验
    private String usrId;


    @NotBlank(message = "账号不能为空")
    @Size(min = 5, max = 10, message = "账号长度需在5-10之间")
    @Column(name = "usr_account", length = 10, nullable = false, unique = true)  // 用户账号：长度为10，唯一，非空
    private String usrAccount;

    @NotBlank(message = "密码不能为空")
    @Column(name = "usr_passwd", length = 32, nullable = false)  // 用户密码：长度为32，非空
    private String usrPasswd;

    @Column(name = "name", length = 50)  // 客户名称：长度为50
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)  // 性别：枚举值（M/F）
    private Gender gender;

    @Column(name = "birthday")  // 出生日期：日期类型
    private Date birthday;

    @Column(name = "phone_Number", length = 11)  // 电话：长度为11
    private String phoneNumber;

    @Column(name = "nationality", length = 50)  // 国家：长度为50
    private String nationality;

    // 用户经理ID（12位，前缀U + 11位数字，示例：U00012345678）
    @Column(name = "relationshipManagerId", length = 12)
    @Pattern(regexp = "^U\\d{11}$", message = "用户经理ID必须以U开头且后跟11位数字")  // 新增正则校验
    @Size(min = 12, max = 12, message = "用户经理ID必须为12位")  // 确保长度严格为12
    private String relationshipManagerId;


    @Column(name = "id_type", length = 20)  // 证件类型：长度为20
    private String idType;

    @Column(name = "id_number", length = 30)  // 证件号：长度为30
    private String idNumber;

    @Column(name = "income_level", length = 25)  // 收入等级：长度为25
    private String incomeLevel;

    @Column(name = "register_date", nullable = false)  // 注册时间：不可为空
    private Date registerDate;

    @Column(name = "kyc_check_date")  // 下次 KYC 日期
    private Date kycCheckDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 2, nullable = false)  // 状态：枚举值（正常/冻结/注销），非空
    private Status status;

    @Lob
    @Column(name = "remarks")  // 备注：文本类型
    private String remarks;

    @Column(name = "risk_level", length = 20)  // 风险等级：长度为20
    private String riskLevel;

    @Column(name = "total_assets", precision = 20, scale = 2)  // 总资产：精度为20，小数位数为2
    private BigDecimal totalAssets;


    // 性别枚举
    public enum Gender {
        M, F
    }

    // 状态枚举（中文）
    public enum Status {
        正常, 冻结, 注销
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public @NotBlank(message = "账号不能为空") @Size(min = 5, max = 10, message = "账号长度需在5-10之间") String getUsrAccount() {
        return usrAccount;
    }

    public void setUsrAccount(@NotBlank(message = "账号不能为空") @Size(min = 5, max = 10, message = "账号长度需在5-10之间") String usrAccount) {
        this.usrAccount = usrAccount;
    }

    public @NotBlank(message = "密码不能为空") String getUsrPasswd() {
        return usrPasswd;
    }

    public void setUsrPasswd(@NotBlank(message = "密码不能为空") String usrPasswd) {
        this.usrPasswd = usrPasswd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRelationshipManagerId() {
        return relationshipManagerId;
    }

    public void setRelationshipManagerId(String relationshipManagerId) {
        this.relationshipManagerId = relationshipManagerId;
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

    public String getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getKycCheckDate() {
        return kycCheckDate;
    }

    public void setKycCheckDate(Date kycCheckDate) {
        this.kycCheckDate = kycCheckDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }
}
