
## 项目概述

本模块是基于Spring Boot实现的认证服务核心组件，提供用户身份验证和会话管理功能，主要职责包括：

-   用户登录凭证验证（用户名/密码+角色）
-   JWT令牌生成与失效管理
-   用户会话生命周期控制

## 技术栈

```
- Spring Boot 2.7+
- Spring Web MVC
- JWT (Java JWT Library)
- Lombok
- OpenFeign (通过RoleBasedFeignRouter集成)
```

## 功能特性

### 核心功能

-   🔐 ​**基于角色的认证系统**
    -   支持多角色联合验证
    -   动态权限校验
-   🛡️ ​**JWT令牌管理**
    -   HS256签名算法
    -   可配置的令牌有效期
    -   主动令牌失效机制
-   🔄 ​**会话生命周期管理**
    -   登录状态跟踪
    -   分布式会话支持

## 目录结构
─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─csis
│  │  │          └─authservice
│  │  │              ├─common       公共类
│  │  │              ├─controller      控制器类
│  │  │              ├─feign              远程调用客户端
│  │  │              ├─service           业务逻辑
│  │  │              │  └─impl
│  │  │              └─utils                工具包

## API文档

### 认证接口

#### 1. 用户登录

```http
POST /auth/login
```

**请求参数**:

json

```json
{
  "username": "string",
  "password": "string",
  "role": "string"
}
```

**成功响应**:

json

```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "expiresIn": 3600
  }
}
```

#### 2. 用户登出

http

```http
POST /auth/logout
```

**请求头**:

```
Authorization: Bearer <token>
```

**成功响应**:

json

```json
{
  "code": 200,
  "message": "Logout successful"
}
```