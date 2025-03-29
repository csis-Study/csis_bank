package com.csis.notificationservice.repository;

import com.csis.notificationservice.pojo.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Dorothy
 * @Date: 2025/3/27
 * @Return:
 **/
@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> getByIsReadAndClientId(Boolean isRead, String clientId);
}