package com.example.portfolio.entity;


import com.example.portfolio._enum.ProductStatus;
import com.example.portfolio._enum.ProductType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

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

    @CreationTimestamp
    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}