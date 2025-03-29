package com.csis.notificationservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notification") //对应数据库中的 User 表
public class Notification {
    @Id
    @Column(name = "notification_id", length = 32) // 通知主键，主键
    @GeneratedValue(generator = "redis_id_generator")
    @GenericGenerator(name = "redis_id_generator", strategy = "com.csis.notificationservice.generator.RedisIdGenerator")
    private String notificationId;

    @Column(name = "client_id", nullable = false, length = 18) //接收人，不能为空，长度为18
    private String clientId;

    @Column(nullable = false, length = 30) //通知类型，不能为空，长度为30
    private String type;

    @Column(nullable = false) //通知内容，不能为空
    @Lob
    private String content;

    @Column(name = "is_read", nullable = false) //是否已读，不能为空，默认为未读
    private Boolean isRead = false;

    //@CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false) //创建时间，不能为空，创建后不能修改
    private LocalDateTime createdAt;

    /*String getNotificationId() {
        return notificationId;
    }

    void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    String getClientId() {
        return clientId;
    }

    void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }*/

}

