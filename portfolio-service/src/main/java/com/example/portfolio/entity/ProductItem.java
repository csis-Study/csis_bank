package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_item")
@Data
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_item_id")
    private Integer productItemId;

    @Column(name = "product_group_id", nullable = false,length = 14)
    private String productGroupId;

    @Column(name = "product_group_name", nullable = false, length = 100)
    private String productGroupName;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "amount")
    private Integer amount;
}