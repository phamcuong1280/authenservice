package com.example.accountservice.exception;


import com.example.accountservice.exception.constant.HousingBusinessError;
import com.example.accountservice.exception.constant.HousingException;

public class AuthenticationException extends HousingException {

    public AuthenticationException() {
        super(HousingErrors.USER_NOT_UNAUTHORIZED);
    }

    public AuthenticationException(String message) {
        super(HousingErrors.USER_NOT_UNAUTHORIZED, message);
    }

    public AuthenticationException(HousingBusinessError error, String message, Throwable cause) {
        super(error, message, cause);
    }
}
