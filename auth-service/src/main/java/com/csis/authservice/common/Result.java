package com.csis.authservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Result <T>{
    private String message;
    private Integer code;
    private T data;
    public Result(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>("success", 200, data);
    }
    public static <T> Result<T> success(String message, T data){
        return new Result<T>(message, 200, data);
    }
    public static <T> Result<T> error(String message, Integer code, T data){
        return new Result<T>(message, code, data);
    }
    public static <T> Result<T> error(String message, Integer code){
        return new Result<T>(message, code, null);
    }
    public static <T> Result<T> error(String message){
        return new Result<T>(message, 500, null);
    }
}
