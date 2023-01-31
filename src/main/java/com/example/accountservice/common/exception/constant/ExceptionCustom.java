package com.example.accountservice.common.exception.constant;

import lombok.Getter;

@Getter
public class ExceptionCustom extends RuntimeException {
    private final BusinessError errorCode;

    public ExceptionCustom(BusinessError errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ExceptionCustom(BusinessError errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorCode.setMessage(message);
    }

    public ExceptionCustom(BusinessError error, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = error;
    }
}
