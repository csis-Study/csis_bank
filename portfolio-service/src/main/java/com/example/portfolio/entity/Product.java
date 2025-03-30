package com.example.portfolio.entity;


import com.example.portfolio._enum.ProductStatus;
import com.example.portfolio._enum.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productName;

    @Column(precision = 38, scale = 2)
    private BigDecimal netValue;

    @Column(precision = 8, scale = 4)
    private BigDecimal floatRate;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(BigDecimal floatRate) {
        this.floatRate = floatRate;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}