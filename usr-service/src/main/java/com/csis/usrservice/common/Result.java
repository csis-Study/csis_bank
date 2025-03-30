package com.csis.usrservice.common;

import lombok.*;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/15
 */
@Data
public class Result<T> {

    //返回码
    private Integer code;
    //返回消息
    private String message;
    //返回数据
    private T data;


    // 私有化构造
    private Result() {
    }

    // 返回数据
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 通过枚举构造Result对象
    public static <T> Result build(T body, ResultCodeEnum resultCodeEnum) {
        return build(body, resultCodeEnum.getCode(), resultCodeEnum.getMessage());
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}