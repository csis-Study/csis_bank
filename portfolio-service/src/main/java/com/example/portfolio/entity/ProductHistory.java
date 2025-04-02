package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;


// 历史数据实体
@Entity
@Table(name = "product_history")
@Data
public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(precision = 38, scale = 2)
    private BigDecimal netValue;

    @Column(precision = 8, scale = 4)
    private BigDecimal floatRate;

    @Column(nullable = false)
    private LocalDate recordDate;
}

