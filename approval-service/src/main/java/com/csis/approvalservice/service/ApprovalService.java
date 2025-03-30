package com.csis.approvalservice.service;

import com.csis.approvalservice.pojo.Approval;

import java.util.List;
import java.util.Optional;

public interface ApprovalService {
    public Approval saveApproval(Approval approval);
    public Optional<Approval> getApprovalById(String approvalId);
    public List<Approval> getApprovalsByTradeId(String tradeId);
    public List<Approval> getApprovalsByLevel(Approval.ApprovalLevel level);
    public void deleteApproval(String approvalId);
}
