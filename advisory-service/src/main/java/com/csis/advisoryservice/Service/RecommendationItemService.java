package com.csis.advisoryservice.Service;

import com.csis.advisoryservice.pojo.RecommendationItem;

import java.util.List;

public interface RecommendationItemService {
    RecommendationItem creatItems(RecommendationItem resultCodeEnum);

    List<RecommendationItem> getRecommendationById(String recommendationitem);

    Boolean deleteRecommendationItem(String recommendationId, String productItem);
}
