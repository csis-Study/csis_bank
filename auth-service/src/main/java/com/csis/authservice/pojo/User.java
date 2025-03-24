package com.csis.authservice.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "User")  // 对应数据库中的 User 表
public class User {

    @Id
    @Column(name = "usr_id", length = 18, nullable = false)  // 主键：用户ID，长度为18，非空
    private String usrId;

    @Column(name = "usr_account", length = 10, nullable = false, unique = true)  // 用户账号：长度为10，唯一，非空
    private String usrAccount;

    @Column(name = "usr_passwd", length = 20, nullable = false)  // 用户密码：长度为20，非空
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

    @Column(name = "relationshipManagerId", length = 18)  // 用户经理ID：长度为18
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

    // 状态枚举
    public enum Status {
        NORMAL, FROZEN, CANCELLED
    }
}
