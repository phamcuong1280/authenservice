package com.example.accountservice.common.exception;


import com.example.accountservice.common.exception.constant.BusinessError;
import com.example.accountservice.common.exception.constant.ExceptionCustom;

public class ValidCodeIncorrectExceptionCustom extends ExceptionCustom {

    public ValidCodeIncorrectExceptionCustom() {
        super(MyErrors.VALID_CODE_INCORRECT);
    }

    public ValidCodeIncorrectExceptionCustom(String message) {
        super(MyErrors.VALID_CODE_INCORRECT, message);
    }

    public ValidCodeIncorrectExceptionCustom(BusinessError error, String message, Throwable cause) {
        super(error, message, cause);
    }
}
