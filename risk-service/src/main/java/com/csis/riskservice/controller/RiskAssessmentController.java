package com.csis.riskservice.controller;

import com.csis.riskservice.pojo.RiskAssessment;
import com.csis.riskservice.service.RiskAssessmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/riskassessments")
public class RiskAssessmentController {

    @Autowired
    private RiskAssessmentManagerService riskAssessmentManagerService;

    // 创建或更新风控评估
    @PostMapping
    public ResponseEntity<RiskAssessment> createOrUpdateRiskAssessment(@RequestBody RiskAssessment riskAssessment) {
        RiskAssessment createdOrUpdatedRiskAssessment = riskAssessmentManagerService.saveRiskAssessment(riskAssessment);
        return new ResponseEntity<>(createdOrUpdatedRiskAssessment, HttpStatus.CREATED);
    }

    // 根据客户 ID 获取风控评估
    @GetMapping("/client/{clientId}")
    public ResponseEntity<RiskAssessment> getRiskAssessmentByClientId(@PathVariable String clientId) {
        Optional<RiskAssessment> riskAssessment = riskAssessmentManagerService.getRiskAssessmentByClientId(clientId);
        return riskAssessment.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 根据风控人员 ID 获取风控评估
    @GetMapping("/evaluator/{evaluatorId}")
    public ResponseEntity<List<RiskAssessment>> getRiskAssessmentsByEvaluatorId(@PathVariable String evaluatorId) {
        List<RiskAssessment> riskAssessments = riskAssessmentManagerService.getRiskAssessmentsByEvaluatorId(evaluatorId);
        return new ResponseEntity<>(riskAssessments, HttpStatus.OK);
    }

    // 根据风险等级获取风控评估
    @GetMapping("/status/{status}")
    public ResponseEntity<List<RiskAssessment>> getRiskAssessmentsByStatus(@PathVariable String status) {
        RiskAssessment.Status statusEnum = RiskAssessment.Status.valueOf(status);
        List<RiskAssessment> riskAssessments = riskAssessmentManagerService.getRiskAssessmentsByStatus(statusEnum);
        return new ResponseEntity<>(riskAssessments, HttpStatus.OK);
    }

    // 删除风控评估
    @DeleteMapping("/{riskId}")
    public ResponseEntity<Void> deleteRiskAssessment(@PathVariable String riskId) {
        riskAssessmentManagerService.removeRiskAssessment(riskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
