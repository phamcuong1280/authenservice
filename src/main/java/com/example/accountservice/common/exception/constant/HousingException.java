package com.example.accountservice.common.exception.constant;

import lombok.Getter;

@Getter
public class HousingException extends RuntimeException {
    private HousingBusinessError errorCode;

    public HousingException(HousingBusinessError errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HousingException(HousingBusinessError errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public HousingException(HousingBusinessError error, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = error;
    }
}