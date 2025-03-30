package com.csis.tradeservice.pojo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Approval")
public class Approval {

    @Id
    @Column(name = "approval_id", length = 20, nullable = false)
    private String approvalId;  // 审批记录ID

    @Column(name = "trade_id", length = 20, nullable = false)
    private String tradeId;  // 交易流水号

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private ApprovalLevel level;  // 审批等级

    @Column(name = "approver_id", length = 12, nullable = false)
    private String approverId;  // 审批人编号

    @Column(name = "decision", length = 10, nullable = false)
    private String decision;  // 审批决策

    @Column(name = "comment")
    private String comment;  // 审批意见

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;  // 审批时间

    // Getters and Setters
    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public ApprovalLevel getLevel() {
        return level;
    }

    public void setLevel(ApprovalLevel level) {
        this.level = level;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Enum for approval level
    public enum ApprovalLevel {
        客户经理, 风控, 合规
    }
}
