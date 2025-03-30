package com.csis.clientservice.service;

import com.csis.clientservice.common.Result;
import com.csis.clientservice.pojo.ProductDailyProfit;

import java.math.BigDecimal;
import java.util.List;

/**
 * 客户资产查询服务接口
 */
public interface AssetService {

    /**
     * 根据客户ID获取所有资产信息
     * @param clientId 18位客户ID
     * @return 资产记录列表
     */
    List<ProductDailyProfit> getAssetsByClientId(String clientId);

    //修改客户余额并且向资产表中插入数据
    void updateClientBalance(String clientId, String type, BigDecimal amount, String productItem);

    //根据产品ID获取所有资产信息
    ProductDailyProfit getAssetsByProductId(Integer productId,String clientId);

    /**
     * 新增产品每日利润记录
     * @param profit 待新增的记录
     * @return 保存后的记录（包含生成的ID）
     * @throws IllegalArgumentException 参数校验失败时抛出
     */
    Result<ProductDailyProfit> addProductDailyProfit(ProductDailyProfit profit);

    Result<ProductDailyProfit> updateProductDailyProfit(Integer productAccount, String clientId, Integer productProductId);
}