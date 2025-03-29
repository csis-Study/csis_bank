package com.csis.notificationservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Dorothy
 * @Date: 2025/3/27
 * @Return:
 **/
@Data
@AllArgsConstructor
public class Result <T>{
    private Integer code;
    private String message;
    private T data;
    public static <T> Result<T> success(T data){
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }
    public static <T> Result<T> success(){
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }
    public static <T> Result<T> success(Integer code, String message, T data){
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }
    public static <T> Result<T> error(Integer code, String message){
        return new Result<>(code, message, null);
    }
    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum){
        return new Result<>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }
    public static <T> Result<T> error(String message){
        return new Result<>(ResultCodeEnum.INTERNAL_ERROR.getCode(), message, null);
    }
}
