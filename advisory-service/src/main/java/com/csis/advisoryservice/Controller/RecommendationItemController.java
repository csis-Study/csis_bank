package com.csis.advisoryservice.Controller;

import com.csis.advisoryservice.Service.RecommendationItemService;
import com.csis.advisoryservice.common.Result;
import com.csis.advisoryservice.common.ResultCodeEnum;
import com.csis.advisoryservice.pojo.Recommendation;
import com.csis.advisoryservice.pojo.RecommendationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendationsItem")
public class RecommendationItemController {
        @Autowired
        private RecommendationItemService recommendationItemService;

        @Autowired
        private RecommendationController recommendationController;

        //新建推荐单项目
        @PutMapping("/creatItem/{recommendationId}")
        public Result<List<RecommendationItem>> creatItems (@RequestBody RecommendationItem recommendationitem , @PathVariable String recommendationId){
            Recommendation recommendation = (Recommendation) recommendationController.getRecommendationById(recommendationId).getData();
            if (recommendation == null) {
                return Result.build(null, ResultCodeEnum.ERROR_CRET);
            }else {
                RecommendationItem resultCodeEnum1  = recommendationItemService.creatItems(recommendationitem);
                return Result.build(resultCodeEnum1,ResultCodeEnum.SUCCESS);
            }
        }
        //通过推荐单查询推荐单项
        @GetMapping("/getRecommendation/{recommendationitem}")
        public Result<RecommendationItem> getRecommendationById(@PathVariable String recommendationitem){
            List<RecommendationItem> item = recommendationItemService.getRecommendationById(recommendationitem);
            if (item == null) {
                return Result.build(null, ResultCodeEnum.ERROR_CRET);
            }else {
                return Result.build(item,ResultCodeEnum.SUCCESS);
            }
        }
        //删除多余的推荐项目
        @DeleteMapping("/deleteRecommendationItem")
        public Result<RecommendationItem> deleteRecommendationItem(@RequestBody String recommendation_id,String product_item){
            Boolean b = recommendationItemService.deleteRecommendationItem(recommendation_id,product_item);
            if (b){
                return Result.build(recommendation_id,ResultCodeEnum.SUCCESS);
            }else {
                return Result.build(recommendation_id,ResultCodeEnum.ERROR_CRET);
            }

        }
}
