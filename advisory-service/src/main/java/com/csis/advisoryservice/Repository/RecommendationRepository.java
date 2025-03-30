package com.csis.advisoryservice.Repository;


import com.csis.advisoryservice.pojo.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, String> {
    // 在这里可以添加自定义的查询方法
    // 例如，根据 client_id 查询推荐记录
    List<Recommendation> findByClientId(String clientId);
    List<Recommendation> findByAdvisorId(String advisorId);
    List<Recommendation> findByrecommendationId(String recommendationId);
    // 根据 advisor_id 查询推荐记录
//    Recommendation findByAdvisorId(String advisorId);

    // 根据 accepted 状态查询推荐记录
    Iterable<Recommendation> findByAccepted(boolean accepted);

    // 根据 created_at 查询推荐记录（范围查询）
    Iterable<Recommendation> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    Recommendation getRecommendationByRecommendationId(String recommendationId);
    @Transactional
    @Modifying
    @Query("UPDATE Recommendation set accepted = true where clientId = :clientId and recommendationId = :recommendationId")
    int ClUpdateRecommendation(String clientId, String recommendationId);
    @Transactional
    @Modifying
    @Query("UPDATE Recommendation set advisorId = :advisorId where recommendationId = :recommendationId")
    int MgUpdateRecommendation(String advisorId, String recommendationId);
    @Transactional
    @Modifying
    @Query("UPDATE Recommendation set clientId = :clientId where recommendationId = :recommendationId")
    int MgAcctedRecommendation(String clientId, String recommendationId);
}