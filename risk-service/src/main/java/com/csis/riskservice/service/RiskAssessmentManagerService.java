package com.csis.riskservice.service;

import com.csis.riskservice.pojo.RiskAssessment;
import com.csis.riskservice.service.impl.RiskAssessmentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RiskAssessmentManagerService {



    // 创建或更新风控评估
    public RiskAssessment createOrUpdateRiskAssessment(RiskAssessment riskAssessment);

    // 获取客户的风控评估
    public Optional<RiskAssessment> getRiskAssessmentByClientId(String clientId) ;

    // 获取风控人员的所有风控评估
    public List<RiskAssessment> getRiskAssessmentsByEvaluatorId(String evaluatorId) ;

    // 根据风险等级查询风控评估
    public List<RiskAssessment> getRiskAssessmentsByStatus(RiskAssessment.Status status) ;

    // 删除风控评估
    public void removeRiskAssessment(String riskId);

    RiskAssessment saveRiskAssessment(RiskAssessment riskAssessment);
}
