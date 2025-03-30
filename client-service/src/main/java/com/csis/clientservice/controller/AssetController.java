package com.csis.clientservice.controller;

import com.csis.clientservice.common.Result;
import com.csis.clientservice.common.ResultCodeEnum;
import com.csis.clientservice.exception.DuplicateAccountException;
import com.csis.clientservice.pojo.ProductDailyProfit;
import com.csis.clientservice.service.AssetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户资产查询控制器
 */
@Validated
@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    /**
     * 根据客户ID查询所有资产信息
     * @param clientId 18位数字客户ID
     * @return 统一响应结果
     */
    @GetMapping("/clients/{clientId}")
    public Result<List<ProductDailyProfit>> getAssetsByClientId(
            @PathVariable
            @Pattern(regexp = "^\\d{18}$", message = "客户ID格式无效")
            String clientId) {
        List<ProductDailyProfit> assets = assetService.getAssetsByClientId(clientId);
        return Result.build(assets, ResultCodeEnum.SUCCESS);
    }

    /*
    * 通过产品id和客户id 查询客户资产信息
    * */
    @GetMapping("/productDailyProfit/{productId}")
    public Result<ProductDailyProfit> getAssetsByProductId(
            @PathVariable
            Integer productId,
            @RequestParam
            String clientId) {
        ProductDailyProfit assets = assetService.getAssetsByProductId(productId,clientId);
        return Result.build(assets, ResultCodeEnum.SUCCESS);
    }


    @PostMapping("/addDailyProfit")
    public Result<ProductDailyProfit> addDailyProfit( @RequestBody ProductDailyProfit profit) {
        ProductDailyProfit savedProfit = assetService.addProductDailyProfit(profit).getData();
        return Result.build(savedProfit, ResultCodeEnum.SUCCESS);
    }
    @PutMapping("/{productAccount}/updateById")
    public Result<ProductDailyProfit> updateProductDailyProfit(@PathVariable Integer productAccount,
                                                               @RequestParam String clientId,
                                                               @RequestParam Integer productProductId){
        assetService.updateProductDailyProfit(productAccount,clientId,productProductId);
        return Result.build(01, ResultCodeEnum.SUCCESS);
    }
}