package com.example.accountservice.common.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class HousingBusinessError<T> {
    private int code;
    private String message;
    private HttpStatus httpStatus;
    private T errorData;

    public HousingBusinessError(String resource) {
        this.code = 404;
        this.message = resource + " is not found";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HousingBusinessError(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
