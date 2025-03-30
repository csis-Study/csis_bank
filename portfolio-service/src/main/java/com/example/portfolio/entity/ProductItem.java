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

    public Integer getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(Integer productItemId) {
        this.productItemId = productItemId;
    }

    public String getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(String productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}