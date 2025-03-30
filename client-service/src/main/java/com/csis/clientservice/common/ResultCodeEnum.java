package com.csis.clientservice.common;


import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    ACCOUNT_CONFLICT(409, "账号已存在"), // 新增冲突状态码
    ACCOUNT_STOP(216, "账号已停用"),
    ACCOUNT_NOTFOUND(217, "账号不存在"),
    STOCK_LESS(219, "余额不足"),
    DATA_ERROR(204, "数据异常"),
    PARAM_ERROR(400, "参数错误"),
    NOT_FOUND(404, "资源不存在");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}