package com.csis.clientservice.exception;

import com.csis.clientservice.common.Result;
import com.csis.clientservice.common.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理资源不存在异常
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleResourceNotFound(ResourceNotFoundException e) {
        return Result.build(null, e.getErrorCode()); // 直接使用异常中的错误码
    }

    // 处理账号冲突异常
    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409 Conflict
    public Result<Void> handleDuplicateAccount(DuplicateAccountException ex) {
        return Result.build(null, ResultCodeEnum.ACCOUNT_CONFLICT);
    }

    // 处理参数校验失败异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleValidationException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse("参数错误");
        return Result.build(null, ResultCodeEnum.PARAM_ERROR.getCode(), errorMsg);
    }

    /*// 处理其他未捕获异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleGenericException(Exception e) {
        return Result.build(null, ResultCodeEnum.STOCK_LESS);
    }*/
}