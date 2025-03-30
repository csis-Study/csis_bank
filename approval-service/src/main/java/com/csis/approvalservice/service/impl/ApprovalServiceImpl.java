package com.csis.approvalservice.service.impl;

import com.csis.approvalservice.repository.ApprovalRepository;
import com.csis.approvalservice.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.csis.approvalservice.pojo.Approval;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    @Qualifier("ApprovalRepository")
    private ApprovalRepository approvalRepository;

    // 保存一个审批记录
    public Approval saveApproval(Approval approval) {
        return approvalRepository.save(approval);
    }

    // 根据审批ID查询审批记录
    public Optional<Approval> getApprovalById(String approvalId) {
        return approvalRepository.findById(approvalId);
    }

    // 根据交易ID查询审批记录
    public List<Approval> getApprovalsByTradeId(String tradeId) {
        return approvalRepository.findByTradeId(tradeId);
    }

    // 根据审批等级查询审批记录
    public List<Approval> getApprovalsByLevel(Approval.ApprovalLevel level) {
        return approvalRepository.findByLevel(level);
    }

    // 删除审批记录
    public void deleteApproval(String approvalId) {
        approvalRepository.deleteById(approvalId);
    }
}
