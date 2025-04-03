package com.csis.notificationservice.utils;

import java.util.Arrays;
import java.util.Optional;

public enum ResultCodeEnum {
    // 2xx 成功类
    SUCCESS(200, "成功"),
    CREATED(201, "资源已创建"),
    ACCEPTED(202, "请求已接受"),
    NO_CONTENT(204, "无返回内容"),

    // 4xx 客户端错误
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权访问"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),

    // 5xx 服务端错误
    INTERNAL_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    // 业务自定义状态码（从 1000 开始）
    BUSINESS_ERROR(1000, "业务异常"),
    PARAM_VALIDATION_FAILED(1001, "参数校验失败"),
    DATA_NOT_EXISTS(1002, "数据不存在"),
    USER_NOT_FOUND(1003, "用户不存在"),
    DUPLICATE_OPERATION(1004, "重复操作");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // 根据 code 查找枚举
    public static Optional<ResultCodeEnum> getByCode(int code) {
        return Arrays.stream(values())
                .filter(e -> e.code == code)
                .findFirst();
    }

    // 获取消息（带默认值）
    public static String getMessage(int code) {
        return getByCode(code)
                .map(ResultCodeEnum::getMessage)
                .orElse("未知状态码");
    }
}