package com.csis.riskservice.service.impl;


import com.csis.riskservice.pojo.RiskAssessment;
import com.csis.riskservice.repository.RiskAssessmentRepository;
import com.csis.riskservice.service.RiskAssessmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RiskAssessmentServiceimpl implements RiskAssessmentManagerService {
    @Autowired
    private RiskAssessmentRepository riskAssessmentRepository;

    // 创建或更新风控评估
    public RiskAssessment saveRiskAssessment(RiskAssessment riskAssessment) {
        return riskAssessmentRepository.save(riskAssessment);
    }

    @Override
    public RiskAssessment createOrUpdateRiskAssessment(RiskAssessment riskAssessment) {
        return null;
    }

    // 根据客户 ID 获取风控评估
    public Optional<RiskAssessment> getRiskAssessmentByClientId(String clientId) {
        return riskAssessmentRepository.findByClientId(clientId);
    }

    // 根据风控人员 ID 获取所有风控评估
    public List<RiskAssessment> getRiskAssessmentsByEvaluatorId(String evaluatorId) {
        return riskAssessmentRepository.findByEvaluatorId(evaluatorId);
    }

    // 根据风险等级查询风控评估
    public List<RiskAssessment> getRiskAssessmentsByStatus(RiskAssessment.Status status) {
        return riskAssessmentRepository.findByStatus(status);
    }

    @Override
    public void removeRiskAssessment(String riskId) {

    }

    // 删除风控评估
    public void deleteRiskAssessment(String riskId) {
        riskAssessmentRepository.deleteById(riskId);
    }
}
