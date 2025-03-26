package com.example.portfolio.entity;

import com.example.portfolio._enum.ProductStatus;
import com.example.portfolio._enum.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id", length = 36)
    @GenericGenerator(name = "product_id", strategy = "com/example/portfolio/util/CustomOrderedIdGenerator")
    private String productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "net_value", nullable = false, precision = 20, scale = 4)
    private BigDecimal netValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;


//    // Lombok注解生成构造器/getter/setter（需添加lombok依赖）
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Data
//    public static class LombokAnnotations {}

    public Product() {
    }

    public Product(String productId, ProductType productType, String productName, BigDecimal netValue, ProductStatus status) {
        this.productId = productId;
        this.productType = productType;
        this.productName = productName;
        this.netValue = netValue;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
