package com.csis.riskservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 核心调度逻辑（示例代码）
@Component
@EnableScheduling
public class DynamicScheduler {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 使用延迟队列存储任务
    private final DelayQueue<CustomerTask> taskQueue = new DelayQueue<>();

    // 初始化加载任务
    @Scheduled(cron = "0 0 0 * * ?") // 每天凌晨加载
    public void loadDailyTasks() {
        int page = 0;
        int size = 1000;

        while (true) {
            String sql = "SELECT id, execute_time, data FROM client "
                    + "WHERE execute_time BETWEEN ? AND ? "
                    + "ORDER BY execute_time LIMIT ?,?";

            // 分页查询次日任务
            List<CustomerTask> tasks = jdbcTemplate.query(
                    sql,
                    (rs, rowNum) -> new CustomerTask(
                            rs.getLong("id"),
                            rs.getTimestamp("execute_time").toInstant(),
                            rs.getString("data")
                    ),
                    LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.DAYS),
                    LocalDateTime.now().plusDays(2).truncatedTo(ChronoUnit.DAYS),
                    page * size,
                    size
            );

            if (tasks.isEmpty()) break;

            taskQueue.addAll(tasks);
            page++;
        }
    }

    // 任务执行线程
    @Scheduled(fixedDelay = 1000)
    public void processTasks() {
        try {
            CustomerTask task = taskQueue.poll(1, TimeUnit.SECONDS);
            if (task != null) {
                // 执行具体业务逻辑
                handleTask(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleTask(CustomerTask task) {
        // 实现具体业务处理
        System.out.println("Processing task: " + task.getId());
    }

    // 延迟任务对象
    private static class CustomerTask implements Delayed {
        private final Long id;

        public Instant getExecuteTime() {
            return executeTime;
        }

        public String getData() {
            return data;
        }

        public Long getId() {
            return id;
        }

        private final Instant executeTime;
        private final String data;

        private CustomerTask(Long id, Instant executeTime, String data) {
            this.id = id;
            this.executeTime = executeTime;
            this.data = data;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }

        // ... 实现getDelay()和compareTo()方法 ...
    }
}