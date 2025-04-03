package com.csis.notificationservice.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.csis.notificationservice.listener.Producer;
import com.csis.notificationservice.pojo.Notification;
import com.csis.notificationservice.repository.NotificationRepository;
import com.csis.notificationservice.service.NotificationService;
import com.csis.notificationservice.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Dorothy
 * @Date: 2025/3/27
 * @Return:
 **/
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private NotificationRepository repository;
	@Autowired
	private Producer producer;

	@Override
	public Result<Notification> testCreateNotification(Notification notification) {
		notification.setCreatedAt(LocalDateTime.now());
		producer.sendMessage(JSON.toJSONString(notification));
		return Result.success();
	}

	@Override
	public Result<List<Notification>> getAllNotifications(Boolean isRead, String clientId) {
		if (isRead == null){
			return Result.error("参数错误");
		}
		List<Notification> notifications = repository.getByIsReadAndClientId(isRead, clientId);
		if (notifications.isEmpty()){
			return Result.error("无通知");
		}
		return Result.success(notifications);
	}

	@Override
	public Boolean createNotification(Notification notification) {
		if (notification == null){
			return false;
		}
		System.out.println("notification = " + notification);
		Notification save = repository.save(notification);
		return save.getNotificationId() != null;
	}

	@Override
	public Result<Notification> getNotificationById(String id) {
		Optional<Notification> optional = repository.findById(id);
		Notification notification = optional.orElse(null);
		if (notification == null){
			return Result.error("通知不存在");
		}
		if (!notification.getIsRead()){
			notification.setIsRead(true);
		}
		repository.save(notification);
		return Result.success(notification);
	}
}