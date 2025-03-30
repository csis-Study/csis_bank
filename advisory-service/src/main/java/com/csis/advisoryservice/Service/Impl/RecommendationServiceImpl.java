package com.csis.advisoryservice.Service.Impl;

import com.csis.advisoryservice.Repository.RecommendationRepository;
import com.csis.advisoryservice.Service.RecommendationService;
import com.csis.advisoryservice.common.Result;
import com.csis.advisoryservice.common.ResultCodeEnum;
import com.csis.advisoryservice.pojo.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/27
 */

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public ResultCodeEnum saveRecommendationByMg(Recommendation recommendation) {
        String clientId = recommendation.getClientId();
        String ID ="RE-" +clientId +"-"+UUID.randomUUID().toString().substring(0,4);
        if (checkClientId(ID)){
            return ResultCodeEnum.ERROR_INSE;
        }else {
        recommendation.setRecommendationId(ID);
        recommendation.setCreatPer(Recommendation.Creatper.valueOf("客户经理"));
        recommendationRepository.save(recommendation);
        return ResultCodeEnum.SUCCESS;
        }
    }

    @Override
    public ResultCodeEnum saveRecommendationByCl(Recommendation recommendation) {
        String clientId = recommendation.getClientId();
        String ID ="RE-" +clientId +"-"+UUID.randomUUID().toString().substring(0,4);
        if (checkClientId(ID)){
            return ResultCodeEnum.ERROR_INSE;
        }else {
        recommendation.setRecommendationId(ID);
        recommendation.setCreatPer(Recommendation.Creatper.valueOf("客户"));
        recommendationRepository.save(recommendation);
        return ResultCodeEnum.SUCCESS;
        }
    }

    @Override
    public List<Recommendation> getRecommendationByClientId(String clientId) {
        return recommendationRepository.findByClientId(clientId);
    }

    @Override
    public List<Recommendation> getRecommendationByAdvisorId(String advisorId) {
        return recommendationRepository.findByAdvisorId(advisorId);
    }

    @Override
    public List<Recommendation> getAllAcceptedRecommendations() {
        return (List<Recommendation>) recommendationRepository.findByAccepted(true);
    }

    @Override
    public List<Recommendation> getRecommendationsByDateRange(LocalDateTime start, LocalDateTime end) {
        return (List<Recommendation>)recommendationRepository.findByCreatedAtBetween(start, end);
    }

    @Override
    public Recommendation getRecommendationById(String recommendationId) {
        return recommendationRepository.getRecommendationByRecommendationId(recommendationId);
    }

    @Override
    public ResultCodeEnum ClUpdateRecommendation(String clientId, String recommendationId) {
        int i = recommendationRepository.ClUpdateRecommendation(clientId,recommendationId);
        if (i == 0){
            return null;
        }else {
            return ResultCodeEnum.AGREE_RECOMMEN;
        }
    }

    @Override
    public ResultCodeEnum MgUpdateRecommendation(String advisorId, String recommendationId) {
        int i = recommendationRepository.MgUpdateRecommendation(advisorId,recommendationId);
        if (i == 0){
            return null;
        }else {
            return ResultCodeEnum.AGREE_RECOMMEN;
        }
    }

    @Override
    public ResultCodeEnum MgAcctedRecommendation(String clientId, String recommendationId) {
        int i = recommendationRepository.MgAcctedRecommendation(clientId,recommendationId);
        if (i == 0){
            return null;
        }else {
            return ResultCodeEnum.AGREE_RECOMMEN;
        }
    }

    public Boolean checkClientId(String clientId) {
        List r  = recommendationRepository.findByrecommendationId(clientId);
        if (r.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
