package com.csis.advisoryservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thoughtworks.xstream.converters.time.LocalDateTimeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/27
 */

@Entity
@Table(name = "Recommendations")
@Data
public class Recommendation {

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(String recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public Creatper getCreatPer() {
        return creatPer;
    }

    public void setCreatPer(Creatper creatPer) {
        this.creatPer = creatPer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Id
    @Column(name = "recommendation_id", length = 40)
    private String recommendationId;

    @Column(name = "client_id", length = 18)
    private String clientId;

    @Column(name = "creat_per")
    private Creatper creatPer;

    @Column(name = "advisor_id", length = 12)
    private String advisorId;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    public enum Creatper {
        客户, 客户经理
    }
}
