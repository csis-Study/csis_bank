package com.csis.advisoryservice.Repository;

import com.csis.advisoryservice.pojo.RecommendationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RecommendationItemRepository extends JpaRepository<RecommendationItem, String> {
    @Transactional
    @Modifying
    @Query("INSERT INTO RecommendationItem (itemId, recommendationId, productItem,amount,type) VALUES (:itemId,:recommendationId,:productItem,:amount,:type)")
    void insert(RecommendationItem recommendationItem);
    @Query("SELECT p from RecommendationItem p where p.recommendationId = :recommendationitem")
    List<RecommendationItem> findByrecommendationitem(String recommendationitem);
    @Transactional
    @Modifying
    @Query("delete from RecommendationItem where recommendationId = :recommendationId and productItem = :productItem")
    int deleteRecommendationItem(String recommendationId, String productItem);
}
