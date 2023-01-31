package com.example.accountservice.common.exception;


import com.example.accountservice.common.exception.constant.BusinessError;
import com.example.accountservice.common.exception.constant.ExceptionCustom;

public class UserNotFound extends ExceptionCustom {

    public UserNotFound() {
        super(MyErrors.USER_NOT_FOUND);
    }

    public UserNotFound(String message) {
        super(MyErrors.USER_NOT_FOUND, message);
    }

    public UserNotFound(BusinessError error, String message, Throwable cause) {
        super(error, message, cause);
    }
}
