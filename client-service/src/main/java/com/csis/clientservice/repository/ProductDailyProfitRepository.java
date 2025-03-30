package com.csis.clientservice.repository;

import com.csis.clientservice.pojo.ProductDailyProfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 产品每日利润数据访问层
 */
public interface ProductDailyProfitRepository extends JpaRepository<ProductDailyProfit, Integer> {

    /**
     * 根据客户ID查询所有资产记录
     *
     * @param clientId 18位客户ID
     * @return 客户资产列表（可能为空）
     */
    List<ProductDailyProfit> findByClientId(String clientId);

    /**
     * 根据产品ID查询所有资产记录
     *
     * @param productId 产品ID
     * @return 产品资产列表（可能为空）
     */
    @Query("select q from ProductDailyProfit q where q.productId= :productId and q.clientId = :clientId")
    ProductDailyProfit findByProductId(Integer productId,String clientId);

    @Transactional
    @Modifying
    @Query("update ProductDailyProfit set purchaseRatio = :productAccount where clientId = :clientId and productId = :productProductId")
    int updateById(Integer productAccount, String clientId, Integer productProductId);

    // 自定义插入 SQL（支持数据库自增主键）
    @Modifying
    @Transactional  // 需要事务支持
    @Query("INSERT INTO ProductDailyProfit (id, recordDate, profitRate,productType,productId,purchaseRatio,clientId,dailyTotalValue) " +
            "VALUES (:id, :recordDate, :profitRate,:productType,:productId,:purchaseRatio,:clientId,:dailyTotalValue)")
    void insert(Integer id,LocalDate recordDate,BigDecimal profitRate,String productType,Integer productId,Integer purchaseRatio,String clientId,BigDecimal dailyTotalValue);



}