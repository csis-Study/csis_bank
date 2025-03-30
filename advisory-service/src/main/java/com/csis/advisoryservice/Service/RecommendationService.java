package com.csis.advisoryservice.Service;

import com.csis.advisoryservice.common.ResultCodeEnum;
import com.csis.advisoryservice.pojo.Recommendation;

import java.time.LocalDateTime;
import java.util.List;

public interface RecommendationService {

    // 保存推荐记录
    ResultCodeEnum saveRecommendationByMg(Recommendation recommendation);

    ResultCodeEnum saveRecommendationByCl(Recommendation recommendation);
    // 根据 client_id 查询推荐记录
    List<Recommendation> getRecommendationByClientId(String clientId);

    List<Recommendation> getRecommendationByAdvisorId(String advisorId);
    // 根据 advisor_id 查询推荐记录
//    Recommendation getRecommendationByAdvisorId(String advisorId);

    // 查询所有已采纳的推荐记录
    List<Recommendation> getAllAcceptedRecommendations();

    // 查询指定时间范围内的推荐记录
    List<Recommendation> getRecommendationsByDateRange(LocalDateTime start, LocalDateTime end);

    Recommendation getRecommendationById(String recommendationId);

    ResultCodeEnum ClUpdateRecommendation(String clientId, String recommendationId);

    ResultCodeEnum MgUpdateRecommendation(String advisorId, String recommendationId);

    ResultCodeEnum MgAcctedRecommendation(String clientId, String recommendationId);
}
