package com.csis.notificationservice.listener;

import com.alibaba.fastjson2.JSON;
import com.csis.notificationservice.pojo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String message) {
        //message是Notification对象的json字符串
        //直接引入通知服务依赖，就可以直接使用Notification实体类 不用创建
        System.out.println("send message:" + message);
        kafkaTemplate.send("approval-topic", message);
    }
}
