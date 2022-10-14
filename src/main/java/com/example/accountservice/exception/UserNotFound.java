package com.example.applicationgateway.exception;


import com.example.applicationgateway.exception.constant.HousingBusinessError;
import com.example.applicationgateway.exception.constant.HousingException;

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
