package com.csis.gatewayservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    private String message;
    private Integer code;
    private T data;

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
