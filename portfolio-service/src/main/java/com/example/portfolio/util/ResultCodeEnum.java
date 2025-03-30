package com.example.portfolio.util;


import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {

    SUCCESS(200 , "操作成功") ,
    ACCOUNT_STOP( 216, "账号已停用"),
    ACCOUNT_NOTFOUND( 217, "账号不存在"),
    STOCK_LESS( 219, "余额不足"),
    DATA_ERROR(204, "数据异常");




    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
