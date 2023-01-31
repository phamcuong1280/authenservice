package com.example.accountservice.common.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Setter
public class BusinessError<T> {
    private int code;
    private String message;
    private HttpStatus httpStatus;
    private T errorData;

    public BusinessError(String resource) {
        this.code = 404;
        this.message = resource + " is not found";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public BusinessError(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
