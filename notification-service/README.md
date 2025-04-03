# 通知服务 (Notification Service)

## 项目概述
基于Spring Boot构建的通知服务系统，提供通知管理、消息队列消费、分布式ID生成等功能。主要特性包括：
• RESTful API管理通知
• Kafka消息队列消费（approval-topic/trade-topic）
• Redis驱动的分布式ID生成
• JPA + MySQL数据持久化
• 用户上下文管理

---

## 技术栈
| 组件             | 技术实现                     |
|------------------|----------------------------|
| 开发框架         | Spring Boot                |
| 数据持久化       | Spring Data JPA + Hibernate|
| 数据库           | MySQL                      |
| 消息队列         | Kafka                      |
| 分布式ID生成     | Redis                      |
| JSON序列化       | FastJSON2                 |
| 依赖注入         | Lombok `@RequiredArgsConstructor` |

---

## 数据库设计
### 表名：`notification`
| 字段名            | 类型          | 约束                         | 说明                         |
|-------------------|--------------|-----------------------------|----------------------------|
| notification_id   | VARCHAR(32)  | PRIMARY KEY                 | 格式：`NOTIF-{yyyymmdd}-{6位序号}` |
| client_id         | VARCHAR(18)  | NOT NULL                    | 接收用户ID                   |
| type              | VARCHAR(30)  | NOT NULL                    | 通知类型（如交易/审批）       |
| content           | TEXT         | NOT NULL                    | 通知内容（长文本）            |
| is_read           | BOOLEAN      | NOT NULL DEFAULT FALSE      | 是否已读状态                 |
| created_at        | DATETIME     | NOT NULL, updatable=false   | 创建时间（格式：yyyy-MM-dd HH:mm:ss） |

---

## API文档
### 1. 获取通知列表
• **URL**: `/notifications`
• **Method**: GET
• **Headers**:
  • `X-user-id`: 用户ID（必需）
• **Query Params**:
  • `isRead`: Boolean（必需）
• **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": [
      {
        "notificationId": "NOTIF-20240515-000001",
        "clientId": "user123",
        "type": "TRADE",
        "content": "您的订单已发货",
        "isRead": false,
        "createdAt": "2024-05-15 14:30:00"
      }
    ]
  }
  ```

### 2. 获取通知详情
• **URL**: `/notifications/{id}`
• **Method**: GET
• **行为**：自动标记为已读
• **响应字段**：同列表接口

### 3. 创建通知（测试用）
• **URL**: `/notifications`
• **Method**: POST
• **Body**:
  ```json
  {
    "clientId": "user123",
    "type": "TEST",
    "content": "测试通知"
  }
  ```

---

## 消息队列消费
### Kafka消费者配置
| 主题             | 监听类            | 处理逻辑                           |
|------------------|-------------------|----------------------------------|
| approval-topic  | ApprovalListener  | 1. 解析JSON为Notification对象<br>2. 保存到数据库<br>3. 手动ACK确认 |
| trade-topic     | TradeListener     | 处理逻辑同上                      |

**ACK机制**：仅当数据库保存成功时调用`acknowledge()`

---

## ID生成策略
### RedisIdGenerator
• **Key格式**：`NOTIF:{yyyymmdd}`（示例：`NOTIF:20240515`）
• **ID结构**：`NOTIF-{yyyymmdd}-{6位自增序号}`（示例：`NOTIF-20240515-000123`）
• **核心逻辑**：
  1. 每日生成新的Redis键
  2. 使用`INCR`原子操作生成序号
  3. 序号每日重置

### 依赖注入
• **BeanPostProcessor**：通过`RedisIdGeneratorInjector`完成RedisTemplate注入

---

## 注意事项
1. **用户上下文**：
   • 必须通过请求头`X-user-id`传递用户ID
   • 通过`UserContext`类实现线程级存储

2. **事务处理**：
   • 获取通知详情时自动触发`isRead`状态更新
   • 使用JPA的`save()`方法实现自动更新

3. **异常场景**：
   • 未找到通知时返回`code: 404`
   • 参数缺失时返回`code: 400`

4. **生产建议**：
   • 为Redis键设置TTL（建议7天）
   • 添加Kafka消费失败的重试机制
   • 补充Swagger API文档

---

## 实体类映射说明
### Notification 实体
• **Lombok**：自动生成getter/setter（`@Data`）
• **审计字段**：`createdAt`通过`@CreatedDate`自动填充（需启用JPA审计）
• **LOB字段**：`content`使用`@Lob`注解映射为TEXT类型
• **JSON格式**：`createdAt`字段序列化为`yyyy-MM-dd HH:mm:ss`

---

> 注意：实际部署时需要配置`application.properties`中的数据库连接、Redis地址、Kafka brokers等参数。