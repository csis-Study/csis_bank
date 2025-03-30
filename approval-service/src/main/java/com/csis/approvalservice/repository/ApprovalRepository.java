package com.csis.approvalservice.repository;

import com.csis.approvalservice.pojo.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ApprovalRepository")
public interface ApprovalRepository extends JpaRepository<Approval, String> {
    // 这里可以根据需求添加自定义查询方法
    Approval findByApprovalId(String approvalId);
    List<Approval> findByTradeId(String tradeId);
    List<Approval> findByLevel(Approval.ApprovalLevel level);
}
