package com.csis.notificationservice.service;

import com.csis.notificationservice.pojo.Notification;
import com.csis.notificationservice.utils.Result;

import java.util.List;

/**
 * @Author: Dorothy
 * @Date: 2025/3/27
 * @Return:
 **/
public interface NotificationService {
	Result<List<Notification>> getAllNotifications(Boolean isRead, String clientId);
	Boolean createNotification(Notification notification);

	Result<Notification> getNotificationById(String id);

	Result<Notification> testCreateNotification(Notification notification);
}