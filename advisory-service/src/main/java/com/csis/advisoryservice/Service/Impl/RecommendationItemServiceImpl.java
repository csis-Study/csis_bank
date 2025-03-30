package com.csis.advisoryservice.Service.Impl;

import com.csis.advisoryservice.Repository.RecommendationItemRepository;
import com.csis.advisoryservice.Service.RecommendationItemService;
import com.csis.advisoryservice.pojo.RecommendationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("recommendationItemServiceImpl")
public class RecommendationItemServiceImpl  implements RecommendationItemService {
    @Autowired
    private RecommendationItemRepository recommendationItemRepository;
    @Override
    public RecommendationItem creatItems(RecommendationItem resultCodeEnum) {
        recommendationItemRepository.save(resultCodeEnum);
        return resultCodeEnum;
    }

    @Override
    public List<RecommendationItem> getRecommendationById(String recommendationitem){
        return recommendationItemRepository.findByrecommendationitem(recommendationitem);
    }

    @Override
    public Boolean deleteRecommendationItem(String recommendationId, String productItem) {
        int i = recommendationItemRepository.deleteRecommendationItem(recommendationId,productItem);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
}
 