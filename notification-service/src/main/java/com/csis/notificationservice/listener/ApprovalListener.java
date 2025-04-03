package com.csis.notificationservice.listener;

import com.alibaba.fastjson2.JSON;
import com.csis.notificationservice.pojo.Notification;
import com.csis.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ApprovalListener {
    @Autowired
    private NotificationService notificationService;
    @KafkaListener(topics = "approval-topic")
    public void onMessage(String message, Acknowledgment ack) {
        System.out.println("approval-topic: " + message);
        Notification notification = JSON.parseObject(message, Notification.class);
        Boolean save = notificationService.createNotification(notification);
        if (save){
            ack.acknowledge();
        }
    }
}
