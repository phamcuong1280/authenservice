package com.example.accountservice.common.exception;


import com.example.accountservice.common.exception.constant.HousingBusinessError;
import com.example.accountservice.common.exception.constant.HousingException;

public class UserNotFound extends HousingException {

    public UserNotFound() {
        super(HousingErrors.USER_NOT_FOUND);
    }

    public UserNotFound(String message) {
        super(HousingErrors.USER_NOT_FOUND, message);
    }

    public UserNotFound(HousingBusinessError error, String message, Throwable cause) {
        super(error, message, cause);
    }
}
