package com.example.accountservice.common.exception;


import com.example.accountservice.common.exception.constant.HousingBusinessError;
import com.example.accountservice.common.exception.constant.HousingException;

public class ValidCodeIncorrectException extends HousingException {

    public ValidCodeIncorrectException() {
        super(HousingErrors.VALID_CODE_INCORRECT);
    }

    public ValidCodeIncorrectException(String message) {
        super(HousingErrors.VALID_CODE_INCORRECT, message);
    }

    public ValidCodeIncorrectException(HousingBusinessError error, String message, Throwable cause) {
        super(error, message, cause);
    }
}
