package com.csis.tradeservice.Client;


import com.csis.tradeservice.pojo.Approval;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("approval-service")
public interface ApprovalFeignClient {
    @GetMapping("/api/approvals")
    List<Approval> getAllApprovals();
    @GetMapping("/{approvalId}")
    Optional<Approval> getApprovalById(@PathVariable String approvalId);
    @GetMapping("/trade/{tradeId}")
    List<Approval> getApprovalsByTradeId(@PathVariable String tradeId);
    @GetMapping("/level/{level}")
    List<Approval> getApprovalsByLevel(@PathVariable Approval.ApprovalLevel level);
    @PostMapping
    Approval createApproval(@RequestBody Approval approval);
    @DeleteMapping("/{approvalId}")
    void deleteApproval(@PathVariable String approvalId);
}
