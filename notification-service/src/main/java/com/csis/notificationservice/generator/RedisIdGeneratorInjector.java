package com.csis.notificationservice.generator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisIdGeneratorInjector implements BeanPostProcessor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RedisIdGenerator) {
            ((RedisIdGenerator) bean).setRedisTemplate(redisTemplate);
        }
        return bean;
    }
}