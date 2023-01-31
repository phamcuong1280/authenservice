package com.example.accountservice.common.exception;


import com.example.accountservice.common.exception.constant.BusinessError;
import com.example.accountservice.common.exception.constant.ExceptionCustom;

public class AuthenticationExceptionCustom extends ExceptionCustom {

    public AuthenticationExceptionCustom() {
        super(MyErrors.USER_NOT_UNAUTHORIZED);
    }

    public AuthenticationExceptionCustom(String message) {
        super(MyErrors.USER_NOT_UNAUTHORIZED, message);
    }

    public AuthenticationExceptionCustom(BusinessError error, String message, Throwable cause) {
        super(error, message, cause);
    }
}
