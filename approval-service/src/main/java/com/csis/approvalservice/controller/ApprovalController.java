package com.csis.approvalservice.controller;

import com.csis.approvalservice.fegin.cilent.tradeFeignClient;
import com.csis.approvalservice.pojo.Approval;
import com.csis.approvalservice.service.ApprovalService;
import com.csis.tradeservice.pojo.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/approvals")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private tradeFeignClient tradeFeignClient;

    @GetMapping("/trade/getAll")
    public ResponseEntity<List<Trade>> getAllTrades() {
        ResponseEntity<List<Trade>> Rs = tradeFeignClient.getAllTrades();
        return Rs;
    }

    // 获取所有审批记录
    @GetMapping
    public List<Approval> getAllApprovals() {
        return approvalService.getApprovalsByLevel(Approval.ApprovalLevel.客户经理);
    }

    // 根据审批ID获取审批记录
    @GetMapping("/{approvalId}")
    public Optional<Approval> getApprovalById(@PathVariable String approvalId) {
        return approvalService.getApprovalById(approvalId);
    }

    // 根据交易ID获取审批记录
    @GetMapping("/trade/{tradeId}")
    public List<Approval> getApprovalsByTradeId(@PathVariable String tradeId) {
        return approvalService.getApprovalsByTradeId(tradeId);
    }

    // 根据审批等级获取审批记录
    @GetMapping("/level/{level}")
    public List<Approval> getApprovalsByLevel(@PathVariable Approval.ApprovalLevel level) {
        return approvalService.getApprovalsByLevel(level);
    }

    // 创建新的审批记录
    @PostMapping
    public Approval createApproval(@RequestBody Approval approval) {
        return approvalService.saveApproval(approval);
    }

    // 删除审批记录
    @DeleteMapping("/{approvalId}")
    public void deleteApproval(@PathVariable String approvalId) {
        approvalService.deleteApproval(approvalId);
    }
}
