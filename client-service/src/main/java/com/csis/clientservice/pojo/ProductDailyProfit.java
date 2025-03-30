package com.csis.clientservice.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "product_daily_profit")
public class ProductDailyProfit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT COMMENT '自增主键'")
    private Integer id;

    @Column(name = "record_date", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE COMMENT '当天时间'")
    private LocalDate recordDate;

    @Column(name = "profit_rate", nullable = false, precision = 5, scale = 2, columnDefinition = "DECIMAL(5,2) COMMENT '产品当天利润率（百分比值，如15.25表示15.25%）'")
    @Digits(integer = 3, fraction = 2, message = "利润率格式无效")
    private BigDecimal profitRate;


    @Column(name = "product_type", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '产品类型（基金/理财/股票）'")
    private String productType;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "purchase_ratio", nullable = false, columnDefinition = "INT COMMENT '购入数量'")
    @Min(value = 0, message = "购入数量不能为负数")
    private Integer purchaseRatio;

    @Column(name = "client_id", nullable = false, length = 18, columnDefinition = "VARCHAR(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '18位纯数字客户标识'")
    @Pattern(regexp = "^\\d{18}$", message = "客户ID必须为18位纯数字")
    private String clientId;

    @Column(name = "daily_total_value", nullable = false, precision = 15, scale = 2, columnDefinition = "DECIMAL(15,2) COMMENT '当日总价值（单位：元）'")
    @Digits(integer = 13, fraction = 2, message = "总价值格式无效")
    private BigDecimal dailyTotalValue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public @Digits(integer = 3, fraction = 2, message = "利润率格式无效") BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(@Digits(integer = 3, fraction = 2, message = "利润率格式无效") BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public @Size(max = 20, message = "产品代码长度不能超过20") Integer getProductId() {
        return productId;
    }

    public void setProductId(@Size(max = 20, message = "产品代码长度不能超过20") Integer productId) {
        this.productId = productId;
    }

    public @Min(value = 0, message = "购入数量不能为负数") Integer getPurchaseRatio() {
        return purchaseRatio;
    }

    public void setPurchaseRatio(@Min(value = 0, message = "购入数量不能为负数") Integer purchaseRatio) {
        this.purchaseRatio = purchaseRatio;
    }

    public @Pattern(regexp = "^\\d{18}$", message = "客户ID必须为18位纯数字") String getClientId() {
        return clientId;
    }

    public void setClientId(@Pattern(regexp = "^\\d{18}$", message = "客户ID必须为18位纯数字") String clientId) {
        this.clientId = clientId;
    }

    public @Digits(integer = 13, fraction = 2, message = "总价值格式无效") BigDecimal getDailyTotalValue() {
        return dailyTotalValue;
    }

    public void setDailyTotalValue(@Digits(integer = 13, fraction = 2, message = "总价值格式无效") BigDecimal dailyTotalValue) {
        this.dailyTotalValue = dailyTotalValue;
    }
}