package com.csis.riskservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "riskassessment")  // 对应数据库中的 User 表
public class RiskAssessment {

	@Id
	@Column(name = "risk_id", length = 30, nullable = false)  // 主键风控评估编号，长度为30，非空，作为主键

	private String riskId;

	@Column(name = "client_id", length = 18, nullable = false, unique = true)  // 客户编号：长度为18，唯一，非空
	private String clientId;

	@Column(name = "evaluator_id", length = 12, nullable = false, unique = true)  // 风控人员编号：长度为12，非空，唯一
	private String evaluatorId;

	@Column(name = "score", nullable = false)  // 风险得分：非空
	private String score;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20, nullable = false)  // 风险等级：枚举值（保守型/稳健型/进取型），非空
	private Status status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_at_datetime")  // 评估时间：日期类型，非空
	private Date createdAtDatetime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "kyc_check_date")  // 下次 KYC 日期
	private Date kycCheckDate;

	@Lob
	@Column(name = "remarks")  // 备注：文本类型，附注说明，允许较长文本
	private String remarks;

	// 状态枚举
	public enum Status {
		保守型, 稳健型, 进取型
	}
}

