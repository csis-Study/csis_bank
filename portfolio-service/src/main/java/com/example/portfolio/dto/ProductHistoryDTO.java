package com.example.portfolio.dto;

import com.example.portfolio.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistoryDTO {
    private Integer productId;
    private String productName;
    private LocalDate recordDate;
    private BigDecimal floatRate;
    private BigDecimal netValue;


    public ProductHistoryDTO(Product product) {
        this.productId = product.getProductId();
        this.productName= product.getProductName();
        this.recordDate = product.getRecordDate();
        this.floatRate = product.getFloatRate();
        this.netValue = product.getNetValue();

    }
}
