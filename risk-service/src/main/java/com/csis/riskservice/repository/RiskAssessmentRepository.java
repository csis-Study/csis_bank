package com.csis.riskservice.repository;

import com.csis.riskservice.pojo.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, String> {
    
    // 根据客户ID查询风控评估
    Optional<RiskAssessment> findByClientId(String clientId);
    
    // 根据风控人员ID查询风控评估
    List<RiskAssessment> findByEvaluatorId(String evaluatorId);
    
    // 根据风险等级查询风控评估
    List<RiskAssessment> findByStatus(RiskAssessment.Status status);
}
