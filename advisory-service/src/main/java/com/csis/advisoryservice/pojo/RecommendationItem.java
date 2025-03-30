package com.csis.advisoryservice.pojo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Recommendationitems")
public class RecommendationItem {

    @Id
    @Column(name = "item_id", length = 36)
    private String itemId; // 推荐项ID

    @Column(name = "recommendation_id")
    private String recommendationId; // 外键，指向 Recommendations 表

    @Column(name = "product_item", length = 20)
    private String productItem; // 产品项编码

    @Column(name = "amount", precision = 20, scale = 2)
    private BigDecimal amount; // 推荐金额

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    @Column(name = "type")
    private TradeType type;
    // Getters and Setters

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(String recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getProductItem() {
        return productItem;
    }

    public void setProductItem(String productItem) {
        this.productItem = productItem;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public enum TradeType {
        申购, 赎回
    }
}
