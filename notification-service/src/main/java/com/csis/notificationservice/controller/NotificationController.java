package com.csis.notificationservice.controller;

import com.csis.notificationservice.pojo.Notification;
import com.csis.notificationservice.service.NotificationService;
import com.csis.notificationservice.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;

	@GetMapping
	public Result<List<Notification>> getAllNotifications(
			@RequestParam Boolean isRead,
			@RequestHeader("X-user-id") String clientId) {
		System.out.println("userId: " + clientId);
		System.out.println("isRead: " + isRead);
		return notificationService.getAllNotifications(isRead, clientId);
	}
	@GetMapping("/{id}")
	public Result<Notification> getNotificationById(@PathVariable String id) {
		return notificationService.getNotificationById(id);
	}
	@PostMapping()
	public Result<Notification> createNotification(@RequestBody Notification notification) {
		return notificationService.testCreateNotification(notification);
	}
}
