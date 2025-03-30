package com.csis.clientservice.exception;

import com.csis.clientservice.common.ResultCodeEnum;

// 资源不存在异常
public class ResourceNotFoundException extends RuntimeException {
    private final ResultCodeEnum errorCode;

    public ResourceNotFoundException(ResultCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ResultCodeEnum getErrorCode() {
        return errorCode;
    }
}