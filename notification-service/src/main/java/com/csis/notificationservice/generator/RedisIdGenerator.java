package com.csis.notificationservice.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RedisIdGenerator implements IdentifierGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String KEY_PREFIX = "NOTIF:";
    private static final String ID_FORMAT = "NOTIF-%s-%06d";

    private StringRedisTemplate redisTemplate;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // 获取当前日期
        String date = LocalDate.now().format(DATE_FORMATTER);
        String redisKey = KEY_PREFIX + date;

        // 使用 INCR 获取自增值（原子性操作）
        Long sequence = redisTemplate.opsForValue().increment(redisKey);

        // 格式化主键
        return String.format(ID_FORMAT, date, sequence);
    }

    //通过 Bean 后处理器完成依赖注入
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}