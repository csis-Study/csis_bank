package com.csis.advisoryservice.Controller;


import com.csis.advisoryservice.Service.RecommendationService;
import com.csis.advisoryservice.common.Result;
import com.csis.advisoryservice.common.ResultCodeEnum;
import com.csis.advisoryservice.pojo.Recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/27
 */
@CrossOrigin
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;


    // 客户经理发起推荐记录
    @PostMapping("/Mg")
    public ResultCodeEnum createRecommendationByMg(@RequestBody Recommendation recommendation ) {
        recommendationService.saveRecommendationByMg(recommendation);
        return ResultCodeEnum.SUCCESS;
    }
    //客户发起推荐记录
    @PostMapping("/Cl")
    public ResultCodeEnum createRecommendationByCl(@RequestBody Recommendation recommendation ) {
        recommendationService.saveRecommendationByCl(recommendation);
        return ResultCodeEnum.SUCCESS;
    }

    //通过推荐Id查询推荐单
    @GetMapping("/recommendationId/{recommendationId}")
    public Result getRecommendationById(@PathVariable String recommendationId){
        Recommendation recommendation = recommendationService.getRecommendationById(recommendationId);
        if (recommendation == null) {
            return Result.build(null,ResultCodeEnum.ERROR_CRET);
        }else {
            return Result.build(recommendation,ResultCodeEnum.SUCCESS);
        }
    }
    // 根据 client_id 查询推荐记录
    @GetMapping("/client/{clientId}")
    public Result<List<Recommendation>> getRecommendationByClientId(@PathVariable String clientId) {
        List<Recommendation> recommendation = recommendationService.getRecommendationByClientId(clientId);
        return Result.build(recommendation,ResultCodeEnum.SUCCESS);
//                recommendation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @CrossOrigin
    // 根据 advisor_id 查询推荐记录
    @GetMapping("/advisor/{advisorId}")
    public Result<List<Recommendation>> getRecommendationByAdvisorId(@PathVariable String advisorId) {
        List<Recommendation> recommendation = recommendationService.getRecommendationByAdvisorId(advisorId);
        return Result.build(recommendation,ResultCodeEnum.SUCCESS);
    }

    // 查询所有已采纳的推荐记录
    @GetMapping("/accepted")
    public Result<List<Recommendation>> getAllAcceptedRecommendations() {
        List<Recommendation> recommendations = recommendationService.getAllAcceptedRecommendations();
        return Result.build(recommendations,ResultCodeEnum.SUCCESS);
//                new ResponseEntity<>(recommendations, HttpStatus.OK);
    }

    // 查询指定时间范围内的推荐记录
    @GetMapping("/date-range")
    public Result<List<Recommendation>> getRecommendationsByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsByDateRange(start, end);
        return Result.build(recommendations,ResultCodeEnum.SUCCESS);
//                new ResponseEntity<>(recommendations, HttpStatus.OK);
    }
    //客户接受推荐单
    @PutMapping("/ClUpdate")
    public Result<Recommendation> ClUpdateRecommendation(String clientId,String recommendationId) {
        ResultCodeEnum recommendation = recommendationService.ClUpdateRecommendation(clientId,recommendationId);
        if (recommendation.getCode() == 220){
            return Result.build(recommendation,ResultCodeEnum.SUCCESS);
        }else {
            return Result.build(null,ResultCodeEnum.DATA_ERROR);
        }
    }
    //经理接手推荐单
    @PutMapping("/MgAccted")
    public Result<Recommendation> MgUpdateRecommendation(String advisor_id,String recommendationId) {
        ResultCodeEnum recommendation = recommendationService.MgUpdateRecommendation(advisor_id,recommendationId);
        if (recommendation.getCode() == 220){
            return Result.build(recommendation,ResultCodeEnum.SUCCESS);
        }else {
            return Result.build(null,ResultCodeEnum.DATA_ERROR);
        }
    }
    //客户接手推荐单
    @PutMapping("/ClAccted")
    public Result<Recommendation> MgAcctedRecommendation(String clientId,String recommendationId) {
        ResultCodeEnum recommendation = recommendationService.MgAcctedRecommendation(clientId,recommendationId);
        if (recommendation.getCode() == 220){
            return Result.build(recommendation,ResultCodeEnum.SUCCESS);
        }else {
            return Result.build(null,ResultCodeEnum.DATA_ERROR);
        }
    }
}
